package com.utils;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddReviewExcelReader {

	public static String getData(int row, int cell) {

		String data = null;

		try {

			FileInputStream fis = new FileInputStream("src/test/resources/testdata/AddReviewData.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fis);

			XSSFSheet sheet = workbook.getSheet("Sheet1");

			data = sheet.getRow(row).getCell(cell).getStringCellValue();

			workbook.close();
			fis.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return data;
	}
}