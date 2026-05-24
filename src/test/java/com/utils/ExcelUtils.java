package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static final String EXCEL_PATH = System.getProperty("user.dir") + "/src/test/resources/QuestionData.xlsx";

	public static Map<String, String> getQuestionData() {

		Map<String, String> data = new HashMap<>();
		DataFormatter formatter = new DataFormatter();

		try (FileInputStream file = new FileInputStream(EXCEL_PATH); Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(1);
			if (headerRow == null || dataRow == null) {
				throw new RuntimeException("Excel header or data row is missing");
			}
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				String key = formatter.formatCellValue(headerRow.getCell(i));
				String value = formatter.formatCellValue(dataRow.getCell(i));
				data.put(key.trim(), value.trim());
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading Excel file: " + EXCEL_PATH, e);
		}
		return data;
	}
}