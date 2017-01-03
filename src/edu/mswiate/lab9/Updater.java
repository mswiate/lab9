package edu.mswiate.lab9;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

public class Updater {
	
	private final String sejm8kadencjaURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8&limit=600";
	private final String sejm7kadencjaURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=7&limit=600";
	private final String poselPreURL = "https://api-v3.mojepanstwo.pl/dane/poslowie/";
	private final String poselPostURL = ".json?layers[]=wyjazdy&layers[]=wydatki";
	
	public Updater(){
		
	}
	
	public String update() throws IOException, InterruptedException{
		Sejm sejm7 = makeSejm(sejm7kadencjaURL, 7);
		Sejm sejm8 = makeSejm(sejm8kadencjaURL, 8);
		
		getPoselsData(sejm7);
		getPoselsData(sejm8);
		
		boolean isUpdated = true;		
		StringBuilder sb = new StringBuilder();
		
		for(Posel posel : sejm7.getPosels() ){
			if(!posel.isUpdated()){
				sb.append(posel.getName() + "\n");
				isUpdated = false;
			}
		}
		
		for(Posel posel : sejm8.getPosels() )
			if(!posel.isUpdated())
				isUpdated = false;
		if(!isUpdated)
			return "Niepomyœlnie zaktualizowano bazê";
		
		
		JSONArray sejms = new JSONArray();
		
		sejms.put(sejm7.toJson());
		sejms.put(sejm8.toJson());
		
		JSONObject sejmometr= new JSONObject();
		sejmometr.put("kadencjas",sejms);
		
		try (FileWriter file = new FileWriter("sejmometr.json")) {
			file.write(sejmometr.toString(1));
		}

		return "Pomyœlnie zaktualizowano bazê";
		
	}
	
	private Sejm makeSejm(String url, int kadencja ) throws IOException{
		
		JSONObject jsonSejm;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream(), Charset.forName("UTF-8")));){
			jsonSejm = new JSONObject( new DataReader().readAll(br) );	
		}
		
		Sejm sejm = new Sejm(kadencja);
		JSONArray jsonPosels = jsonSejm.getJSONArray("Dataobject");
		
		for(int i = 0 ; i < jsonPosels.length(); ++i){
			JSONObject jsonPosel = jsonPosels.getJSONObject(i);
			
			sejm.addPosel(new Posel( jsonPosel.getInt("id"), jsonPosel.getJSONObject("data").getString("poslowie.nazwa") ) );
			
		}
		return sejm;
		
	}
	
	private void getPoselsData(Sejm sejm) throws IOException, InterruptedException{
		
		ExecutorService pool = Executors.newFixedThreadPool(100);
		for(Posel posel : sejm.getPosels() ){
			pool.submit(new DownloadTask(poselPreURL + posel.getId() + poselPostURL, posel));	
		}
		pool.shutdown();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
	}
	
}
