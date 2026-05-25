package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static final String SEARCH_EXCEL_PATH =
			System.getProperty("user.dir") + "/src/test/resources/testdata/searchData.xlsx";

	private static final String REGISTER_EXCEL_PATH =
			System.getProperty("user.dir") + "/src/test/resources/register_data.xlsx";

	private static final String ADD_REVIEW_EXCEL_PATH =
			System.getProperty("user.dir") + "/src/test/resources/AddReviewdata.xlsx";

	private static final String QUESTION_EXCEL_PATH =
			System.getProperty("user.dir") + "/src/test/resources/QuestionData.xlsx";

	private static final DataFormatter formatter = new DataFormatter();

	public static Map<String, String> getSearchData(String sheetName, int rowNumber) {

		Map<String, String> data = new HashMap<>();

		try (FileInputStream fis = new FileInputStream(SEARCH_EXCEL_PATH);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new RuntimeException("Sheet not found: " + sheetName);
			}

			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(rowNumber);

			if (dataRow == null) {
				throw new RuntimeException(
						"Row not found: " + rowNumber + " in sheet: " + sheetName);
			}

			for (int i = 0; i < headerRow.getLastCellNum(); i++) {

				String key =
						formatter.formatCellValue(headerRow.getCell(i)).trim();

				String value =
						formatter.formatCellValue(dataRow.getCell(i)).trim();

				data.put(key, value);
			}

		} catch (IOException e) {

			throw new RuntimeException(
					"Error reading Excel file: " + SEARCH_EXCEL_PATH, e);
		}

		return data;
	}

	public static Map<String, String> getRegisterData() {

		Map<String, String> data = new HashMap<>();

		try (FileInputStream fis = new FileInputStream(REGISTER_EXCEL_PATH);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);

			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(1);

			if (headerRow == null || dataRow == null) {
				throw new RuntimeException(
						"Excel header or data row is missing");
			}

			for (int i = 0; i < headerRow.getLastCellNum(); i++) {

				String key =
						formatter.formatCellValue(headerRow.getCell(i)).trim();

				String value =
						formatter.formatCellValue(dataRow.getCell(i)).trim();

				data.put(key, value);
			}

		} catch (IOException e) {

			throw new RuntimeException(
					"Error reading Excel file: " + REGISTER_EXCEL_PATH, e);
		}

		return data;
	}

	public static String getAddReviewData(int rowNumber, int cellNumber) {

		String data = "";

		try (FileInputStream fis =
					 new FileInputStream(ADD_REVIEW_EXCEL_PATH);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);

			if (sheet.getRow(rowNumber) != null &&
					sheet.getRow(rowNumber).getCell(cellNumber) != null) {

				data = formatter.formatCellValue(
						sheet.getRow(rowNumber).getCell(cellNumber));
			}

		} catch (IOException e) {

			throw new RuntimeException(
					"Error reading Excel file: " + ADD_REVIEW_EXCEL_PATH, e);
		}

		return data;
	}

	public static Map<String, String> getQuestionData() {

		Map<String, String> data = new HashMap<>();

		try (FileInputStream fis = new FileInputStream(QUESTION_EXCEL_PATH);
			 Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);

			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(1);

			if (headerRow == null || dataRow == null) {
				throw new RuntimeException(
						"Excel header or data row is missing");
			}

			for (int i = 0; i < headerRow.getLastCellNum(); i++) {

				String key =
						formatter.formatCellValue(headerRow.getCell(i)).trim();

				String value =
						formatter.formatCellValue(dataRow.getCell(i)).trim();

				data.put(key, value);
			}

		} catch (IOException e) {

			throw new RuntimeException(
					"Error reading Excel file: " + QUESTION_EXCEL_PATH, e);
		}

		return data;
	}
}