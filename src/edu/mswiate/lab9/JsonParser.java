package edu.mswiate.lab9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonParser {
	
	private final String filePath = "sejmometr.json";
	
	public JsonParser(){
		
	}
	
	public Sejm parse(int kadencja) throws IOException{
		String sejmometr;
		try(BufferedReader br = new BufferedReader( new FileReader( new File( this.filePath ) ) ); ){
			sejmometr = new DataReader().readAll(br);
		}	
		
		JSONObject jsonSejmometr = new JSONObject(sejmometr);
		JSONObject jsonSejm = null;
		JSONArray jsonKadencjas = jsonSejmometr.getJSONArray("kadencjas");
		
		for(int i = 0; i < jsonKadencjas.length(); ++i)
			if(jsonKadencjas.getJSONObject(i).getInt("kadencja") == kadencja)
				jsonSejm = jsonKadencjas.getJSONObject(i);
		
		Sejm sejm = new Sejm(jsonSejm.getInt("kadencja"));
		JSONArray jsonPosels = jsonSejm.getJSONArray("posels");
		
		for(int i = 0 ; i < jsonPosels.length(); ++i){
			JSONObject jsonPosel = jsonPosels.getJSONObject(i); 
			sejm.addPosel(new Posel(jsonPosel.getInt("id"), jsonPosel.getString("name"), jsonPosel.getDouble("expenseSum"),
					jsonPosel.getDouble("smallExpenses"), jsonPosel.getInt("numberOfAbroadTrips"), jsonPosel.getInt("daysAbroad"),
					jsonPosel.getBoolean("beenInItaly"), jsonPosel.getDouble("mostExpensiveTrip")));
		}
		return sejm;
	}
}
