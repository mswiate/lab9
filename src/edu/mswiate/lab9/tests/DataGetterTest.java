package edu.mswiate.lab9.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.mswiate.lab9.DataGetter;

public class DataGetterTest {

	@Test
	public void testSumOfExpenses() {
		try{
			assertEquals(new DataGetter().sumOfExpenses(7,"Andrzej Duda"), "153345,54" );
		}
		catch(Exception ex){
			
		}
	}

	@Test
	public void testSmallExpenses() {
		try{
			assertEquals(new DataGetter().smallExpenses(7,"Andrzej Duda"), "0,00" );
		}
		catch(Exception ex){
			
		}
		try{
			assertEquals(new DataGetter().smallExpenses(7,"Adam Abramowicz"), "413,50" );
		}
		catch(Exception ex){
			
		}
	}

	@Test
	public void testAverageExpenses() {
		try{
			assertEquals(new DataGetter().averageExpenses(7), "272577,22" );
		}
		catch(Exception ex){
			
		}
		
	}

	@Test
	public void testPoselWithMostTrips() {
		try{
			assertEquals(new DataGetter().poselWithMostTrips(7), "Pose³/Pos³anka Tadeusz Iwiñski wykona³/a 72 podró¿y zagranicznych." );
		}
		catch(Exception ex){
			
		}
	}

	@Test
	public void testPoselWithDaysAbroad() {
		try{
			assertEquals(new DataGetter().poselWithDaysAbroad(8), "Pose³/Pos³anka Jan Dziedziczak przebywa³/a 165 za granic¹" );
		}
		catch(Exception ex){
			
		}
	}

	@Test
	public void testMostExpensiveTrip() {
		try{
			assertEquals(new DataGetter().mostExpensiveTrip(8),
					"Pose³/Pos³anka Witold Waszczykowski odby³/a podró¿ za 27305,58, która by³a najdro¿sz¹" );
		}
		catch(Exception ex){
			
		}
	}

	@Test
	public void testPoselsInItaly() {
		try{
			String posels = new DataGetter().poselsInItaly(7);
			assertTrue( posels.contains("Ryszard Kalisz") );
			assertFalse( posels.contains("Andrzej Duda") );
		}
		catch(Exception ex){
			
		}
	}

}
