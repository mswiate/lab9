 package edu.mswiate.lab9;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args)  {
	
		try{
			ArgumentsParser ap = new ArgumentsParser();
			String option = ap.parseOption(args);
			String info;
			switch(option){
			case "1":
				info = new DataGetter().sumOfExpenses(ap.parseKadencja(args), ap.parsePoselName(args));
				break;
			case "2":
				info = new DataGetter().smallExpenses(ap.parseKadencja(args), ap.parsePoselName(args));
				break;
			case "3":
				info = new DataGetter().averageExpenses(ap.parseKadencja(args));
				break;
			case "4":
				info = new DataGetter().poselWithMostTrips(ap.parseKadencja(args));
				break;
			case "5":
				info = new DataGetter().poselWithDaysAbroad(ap.parseKadencja(args));
				break;
			case "6":
				info = new DataGetter().mostExpensiveTrip(ap.parseKadencja(args));
				break;
			case "7":
				info = new DataGetter().poselsInItaly(ap.parseKadencja(args));
				break;
			case "update":
				info = new Updater().update();
				break;
			default: // help option
				info = option;
			}
			System.out.println(info);
		}
		catch(FileNotFoundException ex){
			System.out.println(ex + "Spróbuj najpierw uaktualniæ listê pos³ów za pomoc¹ argumentu update");
			System.exit(0);
		}
		catch(IllegalArgumentException | IOException  ex){
			System.out.println(ex);
			System.exit(0);
		}
		catch( InterruptedException   ex){
			System.out.println(ex);
			System.exit(0);
		}
	}

}
