package com.qa.opencart.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVUtil {
	public static final String CSV_PATH = "./src/test/resources/testdata/";
	static List<String[]> row ;
	public static Object[][] getCSVData(String fileName)
	
	{
		String file=CSV_PATH+fileName+".csv";
		
		try {
			CSVReader reader=new CSVReader(new FileReader(file));
			row = reader.readAll();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object[][] testdata=new Object[row.size()][];
		for(int i=0;i<row.size();i++)
		{
			testdata[i]=row.get(i);
		}
		return testdata;
	}

}
