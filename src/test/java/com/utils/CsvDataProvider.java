package com.utils;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class CsvDataProvider {

	public static List<Map<String, String>> getData(String filePath, String scenarioKey) {

		List<Map<String, String>> result = new ArrayList<>();

		File csvFile = new File(System.getProperty("user.dir"), filePath);

		if (!csvFile.exists()) {
			throw new RuntimeException("CSV file not found at: " + csvFile.getAbsolutePath());
		}

		try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {

			List<String[]> allData = reader.readAll();

			if (allData.isEmpty()) {
				return result;
			}

			String[] headers = allData.get(0);

			for (int i = 1; i < allData.size(); i++) {

				String[] values = allData.get(i);

				if (values.length == 0) {
					continue;
				}

				Map<String, String> row = new LinkedHashMap<>();

				for (int j = 0; j < headers.length; j++) {

					row.put(
							headers[j].trim(),
							j < values.length ? values[j].trim() : ""
					);
				}

				if (scenarioKey == null
						|| scenarioKey.isEmpty()
						|| row.getOrDefault("scenario", "")
						.equalsIgnoreCase(scenarioKey)) {

					result.add(row);
				}
			}

		} catch (Exception e) {

			throw new RuntimeException(
					"Failed to read CSV: "
							+ csvFile.getAbsolutePath()
							+ ". Cause: "
							+ e.getMessage()
			);
		}

		return result;
	}

	public static Map<String, String> getFirstRow(
			String filePath,
			String scenarioKey) {

		List<Map<String, String>> data =
				getData(filePath, scenarioKey);

		if (data.isEmpty()) {

			throw new RuntimeException(
					"No data found in ["
							+ filePath
							+ "] for scenario key: ["
							+ (scenarioKey == null
							? "ALL"
							: scenarioKey)
							+ "]"
			);
		}

		return data.get(0);
	}

	public static String getData1(int row, int column) {

		try {

			File csvFile = new File(
					System.getProperty("user.dir"),
					"src/test/resources/AddReviewData.csv"
			);

			if (!csvFile.exists()) {

				throw new RuntimeException(
						"CSV file not found: "
								+ csvFile.getAbsolutePath()
				);
			}

			CSVReader reader =
					new CSVReader(new FileReader(csvFile));

			List<String[]> data =
					reader.readAll();

			reader.close();

			return data.get(row)[column];

		} catch (Exception e) {

			throw new RuntimeException(
					"Error reading CSV: "
							+ e.getMessage()
			);
		}
	}

	public static List<Map<String, String>> getProductInformationData(
			String filePath,
			String scenarioKey) {

		List<Map<String, String>> result =
				new ArrayList<>();

		File csvFile =
				new File(System.getProperty("user.dir"), filePath);

		if (!csvFile.exists()) {

			throw new RuntimeException(
					"CSV file not found at: "
							+ csvFile.getAbsolutePath()
			);
		}

		try (BufferedReader br =
					 new BufferedReader(
							 new FileReader(csvFile))) {

			String headerLine =
					br.readLine();

			if (headerLine == null) {
				return result;
			}

			String[] headers =
					headerLine.split(",");

			String line;

			while ((line = br.readLine()) != null) {

				if (line.trim().isEmpty()) {
					continue;
				}

				String[] values =
						line.split(",");

				Map<String, String> row =
						new LinkedHashMap<>();

				for (int i = 0; i < headers.length; i++) {

					row.put(
							headers[i].trim(),
							i < values.length
									? values[i].trim()
									: ""
					);
				}

				if (scenarioKey == null
						|| scenarioKey.isEmpty()
						|| row.getOrDefault(
						"scenario",
						""
				).equalsIgnoreCase(scenarioKey)) {

					result.add(row);
				}
			}

		} catch (Exception e) {

			throw new RuntimeException(
					"Failed to read CSV: "
							+ csvFile.getAbsolutePath()
							+ ". Cause: "
							+ e.getMessage()
			);
		}

		return result;
	}

	public static Map<String, String> getProductInformationFirstRow(
			String filePath,
			String scenarioKey) {

		List<Map<String, String>> data =
				getProductInformationData(
						filePath,
						scenarioKey);

		if (data.isEmpty()) {

			throw new RuntimeException(
					"No data found in ["
							+ filePath
							+ "] for scenario key: ["
							+ (scenarioKey == null
							? "ALL"
							: scenarioKey)
							+ "]"
			);
		}

		return data.get(0);
	}
}