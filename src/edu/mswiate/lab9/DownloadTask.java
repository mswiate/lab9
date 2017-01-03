package edu.mswiate.lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class DownloadTask implements Runnable {
	private String url;
	private Posel posel;
	
	public DownloadTask(String url, Posel posel){
		this.url = url;
		this.posel = posel;
		
	}
	
	@Override
    public void run() {
		
		JSONObject jsonPosel;
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(new URL(url).openStream(),
						Charset.forName("UTF-8")));){
				jsonPosel = new JSONObject( new DataReader().readAll(br) );		
		} catch (IOException ex) {
			posel.setUpdated(false);
			return;
		}
		if(jsonPosel.has("name") && jsonPosel.getString("name").equals("Not Found") ){
			posel.setUpdated(false);
			return;
		}
		posel.setBeenInItaly(jsonPosel);
		posel.setDaysAbroad(jsonPosel);
		posel.setExpenseSum(jsonPosel);
		posel.setMostExpensiveTrip(jsonPosel);
		posel.setNumberOfAbroadTrips(jsonPosel);
		posel.setSmallExpenses(jsonPosel);
		posel.setUpdated(true);
    }
	
}
