package edu.mswiate.lab9.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.mswiate.lab9.JsonParser;

public class JsonParserTest {

	@Test
	public void testParse() {
		try{
			assertEquals(new JsonParser().parse(7).getPosel("Andrzej Duda").getId(), 77);
		}
		catch(Exception ex){
			
		}
		try{
			assertEquals(new JsonParser().parse(7).getPosel("Andrzej Bu³a").isBeenInItaly(), true);
		}
		catch(Exception ex){
				
		}
	}

}
