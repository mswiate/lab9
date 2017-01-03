package edu.mswiate.lab9;

import java.io.BufferedReader;
import java.io.IOException;

public class DataReader {

	public DataReader(){
		
	}
	//reads all from the buffered reader
	public String readAll(BufferedReader br) throws IOException {
		
		StringBuilder text = new StringBuilder(); 
		
		for(String line ; ( line = br.readLine() ) != null ; ){
			text.append(line);
		}
		
		return text.toString();
	}
}
