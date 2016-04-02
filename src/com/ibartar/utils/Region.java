package com.ibartar.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Region {
	
	public List<String> getRegion() throws IOException
	{
		List<String> list = new ArrayList<>();

		// New BufferedReader.
		BufferedReader reader = new BufferedReader(new FileReader(
			"c:\\city.txt"));

		// Add all lines from file to ArrayList.
		while (true) {
		    String line = reader.readLine();
		    if (line == null) {
			break;
		    }
		    list.add(line);
		}

		// Close it.
		reader.close();
		
		return list;
		
	}

}
