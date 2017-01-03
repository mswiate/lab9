package edu.mswiate.lab9.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.mswiate.lab9.ArgumentsParser;

public class ArgumentsParserTest {

	@Test
	public void testParseOption() {
		String[] args1 = {"1","2","zyz"};
		assertEquals(new ArgumentsParser().parseOption(args1), "1");
		String[] args2 = {"update"};
		assertEquals(new ArgumentsParser().parseOption(args2), "update");
		String[] args3 = {"5","7"};
		assertEquals(new ArgumentsParser().parseOption(args3), "5");
		String[] args4 = {"help"};
		assertEquals(new ArgumentsParser().parseOption(args4), ""
				+ "Argumenty:\n"
				+ "1- suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku\n"
				+ "2- wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki\n"
				+ "3- œredniej wartoœci sumy wydatków wszystkich pos³ów\n"
				+ "4- pos³a/pos³anki, który wykona³ najwiêcej podró¿y zagranicznych\n"
				+ "5- pos³a/pos³anki, który najd³u¿ej przebywa³ za granic¹\n"
				+ "6- pos³a/pos³anki, który odby³ najdro¿sz¹ podró¿ zagraniczn¹\n"
				+ "7- listê wszystkich pos³ów, którzy odwiedzili W³ochy\n"
				+ "update- aby zaktualizowaæ listê pos³ów");
	}

	@Test
	public void testParseKadencja() {
		String[] args1 = {"5","7"};
		assertEquals(new ArgumentsParser().parseKadencja(args1), 7);
		String[] args2 = {"5","8"};
		assertEquals(new ArgumentsParser().parseKadencja(args2), 8);
	}

	@Test
	public void testParsePoselName() {
		String[] args1 = {"1","2","zyz"};
		assertEquals(new ArgumentsParser().parsePoselName(args1), "zyz");
	}

}
