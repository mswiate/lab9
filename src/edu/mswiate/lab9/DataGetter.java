package edu.mswiate.lab9;

import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONException;

public class DataGetter {
	private final DecimalFormat df = new DecimalFormat("#0.00");
	
	
	public DataGetter(){
		
	}
	
	public String sumOfExpenses(int kadencja, String name) throws IOException, IllegalArgumentException{
		Sejm sejm = new JsonParser().parse(kadencja);
		return String.valueOf( df.format(sejm.getPosel(name).getExpenseSum()) );
	}
	
	public String smallExpenses(int kadencja, String name) throws IOException, IllegalArgumentException{
		Sejm sejm = new JsonParser().parse(kadencja);
		return String.valueOf( df.format(sejm.getPosel(name).getSmallExpenses() ) );
	}
	
	public String averageExpenses(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		int numberOfPosels = sejm.getNumberOfPosels();
		double sum = 0.0;
		for(Posel posel: sejm.getPosels()){
			sum += posel.getExpenseSum();
		}
		return String.valueOf( df.format( sum / (double) numberOfPosels ) );
	}
	
	public String poselWithMostTrips(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		int trips = 0;
		String name = null;
		for(Posel posel: sejm.getPosels())
			if(posel.getNumberOfAbroadTrips() >= trips){
				trips = posel.getNumberOfAbroadTrips();
				name = posel.getName();
			}
		
		if(name == null)
			throw new JSONException("W pliku sejmometr.json s� b��dne dane");//!!!!!
	
		return "Pose�/Pos�anka " + name + " wykona�/a " + trips + " podr�y zagranicznych.";
	}
	
	public String poselWithDaysAbroad(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		int days = 0;
		String name = null;
		for(Posel posel: sejm.getPosels())
			if(posel.getDaysAbroad() >= days){
				days = posel.getDaysAbroad();
				name = posel.getName();
			}
		if(name == null)
			throw new JSONException("W pliku sejmometr.json s� b��dne dane");//!!!!!
		
		return "Pose�/Pos�anka " + name + " przebywa�/a " + days + " za granic�";
	}
	
	public String mostExpensiveTrip(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		double trip = 0.0;
		String name = null;
		for(Posel posel: sejm.getPosels())
			if(posel.getMostExpensiveTrip() > trip){
				trip = posel.getMostExpensiveTrip();
				name = posel.getName();
			}
		
		if(name == null)
			throw new JSONException("W pliku sejmometr.json s� b��dne dane");//!!!!!
		
		return "Pose�/Pos�anka " + name + " odby�/a podr� za " + df.format(trip) + ", kt�ra by�a najdro�sz�";
	}
	
	public String poselsInItaly(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		StringBuilder sb = new StringBuilder();
		for(Posel posel: sejm.getPosels())
			if(posel.isBeenInItaly())
				sb.append(posel.getName() + '\n');
		return sb.toString();
	}
	
}
