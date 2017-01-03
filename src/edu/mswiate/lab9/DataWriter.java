package edu.mswiate.lab9;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataWriter {
	
	private final String filePath = "sejmometr.json";
	
	public DataWriter(){
		
	}
	
	//writing in form  of sejmometr which has array of sejms - kadencjas
	public void write(JSONArray sejms) throws IOException{
		JSONObject sejmometr= new JSONObject();
		sejmometr.put("kadencjas",sejms);
		
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(sejmometr.toString(1));
		}
	}
	
}
