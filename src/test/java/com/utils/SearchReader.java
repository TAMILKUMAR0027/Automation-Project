package com.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SearchReader {

    public static final String EXCEL_PATH = "src/test/resources/testdata/searchData.xlsx";

    public static Map<String, String> getRowData(String sheetName, int rowNumber) {

        Map<String, String> data = new HashMap<>();
        DataFormatter formatter = new DataFormatter();

        try (FileInputStream fis = new FileInputStream(EXCEL_PATH);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            XSSFRow headerRow = sheet.getRow(0);
            XSSFRow dataRow = sheet.getRow(rowNumber);

            if (dataRow == null) {
                throw new RuntimeException("Row not found: " + rowNumber + " in sheet: " + sheetName);
            }

            int totalCells = headerRow.getLastCellNum();

            for (int i = 0; i < totalCells; i++) {

                String key = formatter.formatCellValue(headerRow.getCell(i)).trim();
                String value = formatter.formatCellValue(dataRow.getCell(i)).trim();

                data.put(key, value);
            }

        } catch (IOException e) {
            throw new RuntimeException("Excel file not found or unable to read: " + EXCEL_PATH);
        }

        return data;
    }
}