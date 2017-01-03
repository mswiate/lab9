package edu.mswiate.lab9.tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import edu.mswiate.lab9.DataReader;

public class DataReaderTest {

	@Test
	public void testReadAll() {
		BufferedReader br = new BufferedReader(new StringReader("xyxzjsias\nasd asdasd \n"));
		try{
		assertEquals("xyxzjsiasasd asdasd ", new DataReader().readAll(br));
		}
		catch(Exception ex){
			
		}
	}

}
