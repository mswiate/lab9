package edu.mswiate.lab9;

public class ArgumentsParser {
	private final String helpString = ""
			+ "Argumenty:\n"
			+ "1- suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku\n"
			+ "2- wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki\n"
			+ "3- �redniej warto�ci sumy wydatk�w wszystkich pos��w\n"
			+ "4- pos�a/pos�anki, kt�ry wykona� najwi�cej podr�y zagranicznych\n"
			+ "5- pos�a/pos�anki, kt�ry najd�u�ej przebywa� za granic�\n"
			+ "6- pos�a/pos�anki, kt�ry odby� najdro�sz� podr� zagraniczn�\n"
			+ "7- list� wszystkich pos��w, kt�rzy odwiedzili W�ochy\n"
			+ "update- aby zaktualizowa� list� pos��w";

	public ArgumentsParser(){
		
	}
	
	public String parseOption(String[] args){
		if(args.length < 1)
			throw new IllegalArgumentException("U�yj argumentu help aby dowiedzie� si� o mo�liwych argumentach");
		String option = args[0];
		switch(option){
		case "1": case "2" :
			if(args.length != 3)
				throw new IllegalArgumentException("w opcji " + option + " argumenty powinny miec postac: "
						+ option + " kadencja \"imi�/imiona i nazwisko pos�a\" ");
			return option;
			
		case "3": case "4": case "5": case "6": case "7": 
			if(args.length != 2)
				throw new IllegalArgumentException("w opcji " + option + " argumenty powinny miec postac: "
						+ option + " kadencja  ");
			return option;
			
		case "help": 
			if(args.length != 1)
				throw new IllegalArgumentException("niepoprawna liczba argumentow");
			return helpString;
		case "update":
			if(args.length != 1)
				throw new IllegalArgumentException("niepoprawna liczba argumentow");
			return option;
			
		default:
			throw new IllegalArgumentException("Niepoprawny argument:" + args[0]);
			
		}
	}
	
	public int parseKadencja(String[] args){
		if( !args[1].equals("7") && !args[1].equals("8") )
			throw new IllegalArgumentException("kadencja powinna byc cyfra: 7 lub 8");
		return Integer.parseInt(args[1]);
	}
	
	public String parsePoselName(String[] args){
		return args[2];
	}
	
}
