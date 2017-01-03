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
				+ "1- suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku\n"
				+ "2- wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki\n"
				+ "3- �redniej warto�ci sumy wydatk�w wszystkich pos��w\n"
				+ "4- pos�a/pos�anki, kt�ry wykona� najwi�cej podr�y zagranicznych\n"
				+ "5- pos�a/pos�anki, kt�ry najd�u�ej przebywa� za granic�\n"
				+ "6- pos�a/pos�anki, kt�ry odby� najdro�sz� podr� zagraniczn�\n"
				+ "7- list� wszystkich pos��w, kt�rzy odwiedzili W�ochy\n"
				+ "update- aby zaktualizowa� list� pos��w");
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
