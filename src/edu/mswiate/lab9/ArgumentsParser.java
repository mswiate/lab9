package edu.mswiate.lab9;

public class ArgumentsParser {
	private final String helpString = ""
			+ "Argumenty:\n"
			+ "1- suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku\n"
			+ "2- wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki\n"
			+ "3- œredniej wartoœci sumy wydatków wszystkich pos³ów\n"
			+ "4- pos³a/pos³anki, który wykona³ najwiêcej podró¿y zagranicznych\n"
			+ "5- pos³a/pos³anki, który najd³u¿ej przebywa³ za granic¹\n"
			+ "6- pos³a/pos³anki, który odby³ najdro¿sz¹ podró¿ zagraniczn¹\n"
			+ "7- listê wszystkich pos³ów, którzy odwiedzili W³ochy\n"
			+ "update- aby zaktualizowaæ listê pos³ów";

	public ArgumentsParser(){
		
	}
	
	public String parseOption(String[] args){
		if(args.length < 1)
			throw new IllegalArgumentException("U¿yj argumentu help aby dowiedzieæ siê o mo¿liwych argumentach");
		String option = args[0];
		switch(option){
		case "1": case "2" :
			if(args.length != 3)
				throw new IllegalArgumentException("w opcji " + option + " argumenty powinny miec postac: "
						+ option + " kadencja \"imiê/imiona i nazwisko pos³a\" ");
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
