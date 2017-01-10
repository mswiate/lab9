package edu.mswiate.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class Updater {
	
	private final String sejm8kadencjaURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8&limit=100";
	private final String sejm7kadencjaURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7&limit=100";
	private final String poselPreURL = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
	private final String poselPostURL = ".json?layers[]=wyjazdy&layers[]=wydatki";
	
	public Updater(){
		
	}
	
	public String update() throws IOException, InterruptedException{
		//makes sejms with posels - (id, name)
		Sejm sejm7 = makeSejm(sejm7kadencjaURL, 7);
		Sejm sejm8 = makeSejm(sejm8kadencjaURL, 8);
		
		//get rest of data about posels
		getPoselsData(sejm7);
		getPoselsData(sejm8);
		
		//finding out if every posel is updated correctly
		if(!arePoselsUpdated(sejm7) || !arePoselsUpdated(sejm8))
			return "Niepomyœlnie zaktualizowano bazê";
		
		//writing down to file
		JSONArray sejms = new JSONArray();
		sejms.put(sejm7.toJson());
		sejms.put(sejm8.toJson());
		new DataWriter().write(sejms);

		return "Pomyœlnie zaktualizowano bazê";
		
	}
	
	private Sejm makeSejm(String url, int kadencja ) throws IOException{
		
		List<JSONObject> jsonSejmPages = new ArrayList<JSONObject>();
		
		String currUrl = url;
		Boolean lastPage = false;
	
		//downloading info about sejm
		while(!lastPage){
			try(BufferedReader br = new BufferedReader(new InputStreamReader(new URL(currUrl).openStream(), Charset.forName("UTF-8")));){
				jsonSejmPages.add( new JSONObject( new DataReader().readAll(br) ) );	
			}
			
			//checks if all posels are under currUrl
			if(!jsonSejmPages.get(jsonSejmPages.size() - 1 ).getJSONObject("Links").has("next") )
				lastPage = true;
			else
				currUrl = jsonSejmPages.get(jsonSejmPages.size() - 1 ).getJSONObject("Links").getString("next");
			
		}
		//making sejm
		Sejm sejm = new Sejm(kadencja);
		
		//getting info about posels
		for(JSONObject jsonSejmPage:jsonSejmPages){
			JSONArray jsonPosels = jsonSejmPage.getJSONArray("Dataobject");
		
			for(int i = 0 ; i < jsonPosels.length(); ++i){
				JSONObject jsonPosel = jsonPosels.getJSONObject(i);
				sejm.addPosel(new Posel( jsonPosel.getInt("id"), jsonPosel.getJSONObject("data").getString("poslowie.nazwa") ) );
			}
		}
		
		return sejm;
		
	}
	
	private void getPoselsData(Sejm sejm) throws IOException, InterruptedException{
		//it depends on computer unit and net connection - argument 1 - 1000 (1 for potato) 
		ExecutorService pool = Executors.newFixedThreadPool(50);
		//getting info about every posel
		for(Posel posel : sejm.getPosels() ){
			pool.submit(new DownloadTask(poselPreURL + posel.getId() + poselPostURL, posel));	
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}
	
	private boolean arePoselsUpdated(Sejm sejm){
		boolean areUpdated = true;		
		
		for(Posel posel : sejm.getPosels() )
			if(!posel.isUpdated())		
				areUpdated = false;
		
		return areUpdated;
	}
}
