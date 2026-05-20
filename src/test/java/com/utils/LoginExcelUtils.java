package com.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoginExcelUtils {
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;

    // Excel File Path and Sheet Name
    private static final String Excel_Path ="src/test/resources/testdata/LoginData.xlsx";
    private static final String Sheet_name = "Sheet1";

    // Load Excel File
    public static void setExcelFile() {

    	try {
    		String excelPath = "D:\\Automation-Project\\src\\test\\resources\\testdata\\LoginData.xlsx";
    		FileInputStream fis = new FileInputStream(excelPath);
    		workbook = new XSSFWorkbook(fis);
    		sheet = workbook.getSheet("Sheet1");

    	} catch (Exception e) {
    		System.out.println("Excel file not found");
    		e.printStackTrace();
    	}
    }

    // Read Cell Data
    public static String getCellData(int rowNum, int colNum) {
        try {
            DataFormatter formatter = new DataFormatter();
            Cell cell = sheet.getRow(rowNum).getCell(colNum);
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            return "";
        }
    }

    // Get Total Rows
    public static int getRowCount() {
        return sheet.getLastRowNum();
    }

    // Get Total Columns
    public static int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // Close Workbook
    public static void closeWorkbook() {
        try {

            if (workbook != null) {
                workbook.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}