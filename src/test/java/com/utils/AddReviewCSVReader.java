package com.utils;

import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;

public class AddReviewCSVReader {

<<<<<<< HEAD
    public static String getData(int row, int column) {
=======
public static String getData1(  int row, int column) {
>>>>>>> dc3a25591a5d6556787c0735be080802b4922963

        try {

            CSVReader reader =
                    new CSVReader(
                            new FileReader("src/test/resources/AddReviewData.csv"));

            List<String[]> data = reader.readAll();

            // Check row exists
            if (row >= data.size()) {
                return "";
            }

            String[] rowData = data.get(row);

            // Check column exists
            if (column >= rowData.length) {
                return "";
            }

            // Return value safely
            return rowData[column].trim();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return "";
    }
}