package edu.mswiate.lab9;

import org.json.*;

public class Posel {
	private static final String smallExpensesString = "Koszty drobnych napraw i remontów lokalu biura poselskiego";
	private static final String italyString = "IT";
	
	private int id;
	private String name;
	private double expenseSum;
	private double smallExpenses;
	private int numberOfAbroadTrips;
	private int daysAbroad;
	private boolean beenInItaly;
	private double mostExpensiveTrip;
	
	private boolean updated;
	
	//constructor for downloading sejm
	public Posel(int id, String name) {
		this.id = id;
		this.name = name;
	}
	//constructor for reading object from file
	public Posel(int id, String name, double expenseSum, double smallExpenses, int numberOfAbroadTrips, int daysAbroad,
			boolean beenInItaly, double mostExpensiveTrip) {
		this.id = id;
		this.name = name;
		this.expenseSum = expenseSum;
		this.smallExpenses = smallExpenses;
		this.numberOfAbroadTrips = numberOfAbroadTrips;
		this.daysAbroad = daysAbroad;
		this.beenInItaly = beenInItaly;
		this.mostExpensiveTrip = mostExpensiveTrip;
	}
	
	public JSONObject toJson(){
		JSONObject obj = new JSONObject()
				.put("id", id)
				.put("name", name)
				.put("expenseSum", expenseSum)
				.put("smallExpenses", smallExpenses)
				.put("numberOfAbroadTrips", numberOfAbroadTrips)
				.put("daysAbroad", daysAbroad)
				.put("beenInItaly", beenInItaly)
				.put("mostExpensiveTrip", mostExpensiveTrip);
		return obj;
	}
	
	/*
	 * setters are used to download info about separate posel
	 */

	public void setExpenseSum(JSONObject jsonPosel) {
		double sum = 0.0; 
		
		JSONArray years = jsonPosel.getJSONObject("layers")
								  .getJSONObject("wydatki")
								  .getJSONArray("roczniki");
		
		//if there is no expense layer
		if(years.length() < 1){
			this.expenseSum = 0.0;
			return;
		}
		
		//summing each expense separately
		for(int i = 0 ; i < years.length()  ; ++i){
			JSONArray expenses = years.getJSONObject(i).getJSONArray("pola");
			
			for(int j = 0 ; j < expenses.length(); ++j){
				sum += expenses.getDouble(j);
			}
			
		}
		
		this.expenseSum = sum;
	}

	public void setSmallExpenses(JSONObject jsonPosel) {
		double sum = 0.0; 
		
		//finding point for small expenses
		JSONArray points = jsonPosel.getJSONObject("layers")
				  					.getJSONObject("wydatki")
				  					.getJSONArray("punkty");
		//if there is no expense layer
		if(points.length() < 1){
			this.smallExpenses = 0.0;
			return;
		}
		
		//neutral value
		int point = -1;
		for(int i = 0; i < points.length() ; ++i)
			if( points.getJSONObject(i).getString( "tytul" ).equals( smallExpensesString ) )
				point = points.getJSONObject(i).getInt("numer") - 1 ;
		
		//if it cannot find a point for small expenses
		if(point == -1){
			this.smallExpenses = 0.0;
			return;
		}
		
		JSONArray years = jsonPosel.getJSONObject("layers")
								  .getJSONObject("wydatki")
								  .getJSONArray("roczniki");
		
		// summing expenses for each year
		for(int i = 0 ; i < years.length() ; ++i)
			sum += years.getJSONObject(i).getJSONArray("pola").getDouble(point);
		
		this.smallExpenses = sum;
	}

	public void setNumberOfAbroadTrips(JSONObject jsonPosel) {
		this.numberOfAbroadTrips = jsonPosel.getJSONObject("data").getInt("poslowie.liczba_wyjazdow");
	}

	public void setDaysAbroad(JSONObject jsonPosel) {
		
		try{
			JSONArray trips = jsonPosel.getJSONObject("layers")
									   .getJSONArray("wyjazdy");
		
			int days = 0;
		
			for(int i = 0; i < trips.length() ;++i )
				days += trips.getJSONObject(i).getInt("liczba_dni");
		
			this.daysAbroad = days;
		}
		catch(JSONException ex){//if there is no trips layer
			this.daysAbroad = 0;
		}
	}

	public void setBeenInItaly(JSONObject jsonPosel) {
		
		try{
			JSONArray trips = jsonPosel.getJSONObject("layers")
				   				   .getJSONArray("wyjazdy");
		
			boolean been = false;
			
			for(int i = 0; i < trips.length() ;++i )
				if( trips.getJSONObject(i).getString("country_code").equals( italyString ) )
					been = true;
		
			this.beenInItaly = been;
		}
		catch(JSONException ex){//if there is no trips layer
			this.beenInItaly = false;
		}
	}

	public void setMostExpensiveTrip(JSONObject jsonPosel) {
		try{
			JSONArray trips = jsonPosel.getJSONObject("layers")
										.getJSONArray("wyjazdy");
		
			double maxCost = 0.0;
			for(int i = 0; i < trips.length() ;++i )
				if( maxCost < trips.getJSONObject(i).getDouble("koszt_suma") )
					maxCost = trips.getJSONObject(i).getDouble("koszt_suma");
		
			this.mostExpensiveTrip = maxCost;
		}
		catch(JSONException ex){//if there is no trips layer
			this.mostExpensiveTrip = 0.0;
		}
	}
	
	//used to find out if there was any problem updating posel
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getExpenseSum() {
		return expenseSum;
	}
	
	public double getSmallExpenses() {
		return smallExpenses;
	}
	
	public int getNumberOfAbroadTrips() {
		return numberOfAbroadTrips;
	}
	
	public int getDaysAbroad() {
		return daysAbroad;
	}
	
	public boolean isBeenInItaly() {
		return beenInItaly;
	}
	
	public double getMostExpensiveTrip() {
		return mostExpensiveTrip;
	}

	public boolean isUpdated() {
		return updated;
	}

}
