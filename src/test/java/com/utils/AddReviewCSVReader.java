package com.utils;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class AddReviewCSVReader {
		

public static String getData(  int row, int column) {

 try {

		     CSVReader reader = new CSVReader(  new FileReader("src/test/resources/testdata/AddReview.csv"));

		            List<String[]> data =reader.readAll();

		            return data.get(row)[column];

		        } catch (Exception e) {

		            e.printStackTrace();
		        }

		        return null;
		    }
		}