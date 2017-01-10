package edu.mswiate.lab9;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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
		
		//gets first posel
		List<Posel> posels = sejm.getPosels();
		int trips = posels.get(0).getNumberOfAbroadTrips();
		String name = posels.get(0).getName();
		
		for(Posel posel: posels)
			if(posel.getNumberOfAbroadTrips() >= trips){
				trips = posel.getNumberOfAbroadTrips();
				name = posel.getName();
			}
	
		return "Pose³/Pos³anka " + name + " wykona³/a " + trips + " podró¿y zagranicznych.";
	}
	
	public String poselWithDaysAbroad(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		
		//gets first posel
		List<Posel> posels = sejm.getPosels();
		int days = posels.get(0).getDaysAbroad();
		String name = posels.get(0).getName();
		
		for(Posel posel: posels)
			if(posel.getDaysAbroad() >= days){
				days = posel.getDaysAbroad();
				name = posel.getName();
			}

		
		return "Pose³/Pos³anka " + name + " przebywa³/a " + days + " za granic¹";
	}
	
	public String mostExpensiveTrip(int kadencja) throws IOException{
		Sejm sejm = new JsonParser().parse(kadencja);
		
		//gets first posel
		List<Posel> posels = sejm.getPosels();
		double trip = posels.get(0).getMostExpensiveTrip();
		String name = posels.get(0).getName();
		
		for(Posel posel: posels)
			if(posel.getMostExpensiveTrip() > trip){
				trip = posel.getMostExpensiveTrip();
				name = posel.getName();
			}
		
		
		
		return "Pose³/Pos³anka " + name + " odby³/a podró¿ za " + df.format(trip) + ", która by³a najdro¿sz¹";
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
