package com.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CsvDataProvider {

    /**
     * Reads CSV rows matching the given scenarioKey from the "scenario" column.
     * Pass null for scenarioKey to return all rows.
     *
     * @param filePath    path relative to project root e.g. "src/test/resources/wishlist_data.csv"
     * @param scenarioKey value in "scenario" column to filter by, or null for all rows
     */
    public static List<Map<String, String>> getData(String filePath, String scenarioKey) {

        List<Map<String, String>> result = new ArrayList<>();

        // Resolve path relative to project root — works regardless of working directory
        File csvFile = new File(System.getProperty("user.dir"), filePath);

        if (!csvFile.exists()) {
            throw new RuntimeException(
                    "CSV file not found at: " + csvFile.getAbsolutePath() + "\n" +
                            "Confirm the file exists at: src/test/resources/wishlist_data.csv"
            );
        }

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String headerLine = br.readLine();
            if (headerLine == null) return result;

            String[] headers = headerLine.split(",");
            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), i < values.length ? values[i].trim() : "");
                }

                if (scenarioKey == null || scenarioKey.isEmpty()
                        || row.getOrDefault("scenario", "").equalsIgnoreCase(scenarioKey)) {
                    result.add(row);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read CSV: " + csvFile.getAbsolutePath() + ". Cause: " + e.getMessage()
            );
        }

        return result;
    }

    /**
     * Returns the first matching row for a scenario key.
     */
    public static Map<String, String> getFirstRow(String filePath, String scenarioKey) {
        List<Map<String, String>> data = getData(filePath, scenarioKey);
        if (data.isEmpty()) {
            throw new RuntimeException(
                    "No data found in [" + filePath + "] for scenario key: [" +
                            (scenarioKey == null ? "ALL" : scenarioKey) + "]"
            );
        }
        return data.get(0);
    }
}