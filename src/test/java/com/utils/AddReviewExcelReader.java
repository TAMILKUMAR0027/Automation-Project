package com.utils;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class AddReviewExcelReader {

    public static String getData(int row, int cell) {

        String data = "";

        try {

            FileInputStream fis =
                    new FileInputStream("src/test/resources/AddReviewdata.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            XSSFSheet sheet = workbook.getSheetAt(0);

            DataFormatter formatter = new DataFormatter();

            if (sheet.getRow(row) != null &&
                sheet.getRow(row).getCell(cell) != null) {

                data = formatter.formatCellValue(
                        sheet.getRow(row).getCell(cell));
            }

            workbook.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return data;
    }
}