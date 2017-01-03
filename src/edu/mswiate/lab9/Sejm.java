package edu.mswiate.lab9;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.*;

public class Sejm {
	private int kadencja;
	private final Map<String,Posel> posels = new HashMap<>();  
	
	public Sejm(int kadencja){
		this.kadencja = kadencja;
	}
	
	public void addPosel(Posel posel){
		this.posels.put(posel.getName(), posel);
	}
	
	public Posel getPosel(String name){
		
		if(!posels.containsKey(name))
			throw new IllegalArgumentException("nie ma w danej kadencji sejmu takiego pos³a jak: " + name);
		
		return posels.get(name);
	}
	
	public int getNumberOfPosels(){
		return posels.size();
	}
	

	public int getKadencja() {
		return kadencja;
	}

	public void setKadencja(int kadencja) {
		this.kadencja = kadencja;
	}

	public Collection<Posel> getPosels() {
		return posels.values();
	}

	public JSONObject toJson(){
		JSONObject sejm = new JSONObject().put("kadencja", kadencja);
		
		JSONArray jsonPosels = new JSONArray();
		
		for(Posel posel:posels.values()){
			jsonPosels.put(posel.toJson());
		}
		
		sejm.put("posels", jsonPosels);
		
		return sejm;	
	}
}
