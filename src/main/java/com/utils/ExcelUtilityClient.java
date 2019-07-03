package com.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Class contains the excel related methods and variables.
 */
public class ExcelUtilityClient {

    private static String separator = File.separator;

    private static String returnRow(XSSFRow r, int i) {
        if (r.getCell(i) == null) {
            return "";
        } else if (r.getCell(i).getCellType() == CellType.NUMERIC) {
            return removeSpaces(String.valueOf(r.getCell(i).getNumericCellValue()));
        } else if (r.getCell(i).getCellType() == CellType.STRING) {
            return removeSpaces(r.getCell(i).getStringCellValue());
        } else {
            return "";
        }
    }

    private static String removeSpaces(String s) {
        s = s.replace("\u00a0", "");
        s = s.trim();
        return s;
    }

    /**
     * Read and store the data in excel making testcase name as primary key.
     */
    public static Map<String, HashMap<String, String>> readTestScriptData(String sheetName)
            throws Exception {
        Map<String, HashMap<String, String>> map = new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                        + "resources" + separator + "testdata" + separator + "TaxCalculations.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        int rowCount = worksheet.getPhysicalNumberOfRows();
        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = worksheet.getRow(i);
            String state = returnRow(row, 0);
            int cellCount = row.getLastCellNum();
            for (int j = 1; j < cellCount; j++) {
                data.put(returnRow(worksheet.getRow(0), j), returnRow(row, j));
            }
            map.put(state, data);
            data = new HashMap<>();
        }
        workbook.close();
        return map;
    }

    public static void writeToExcel(String userName, String password, String sheetName)
            throws Exception {
        String filePath = System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                + "resources" + separator + "testdata" + separator + "UserAccountDetails.xlsx";
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        int rowCount = worksheet.getPhysicalNumberOfRows();
        int rowNum = rowCount + 1;
        XSSFRow row = worksheet.getRow(rowNum) == null ? worksheet.createRow(rowNum) : worksheet.getRow(rowNum);
        XSSFCell email = row.getCell(0) == null ? row.createCell(0) : row.getCell(0);
        email.setCellValue(userName);
        XSSFCell pass = row.getCell(1) == null ? row.createCell(1) : row.getCell(1);
        pass.setCellValue(password);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
    }

    public static void writeToExcel(String fileName, String sheetName, String valueToFindRowIndex, int columnIndex, String valueToSet) throws Exception {
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                        + "resources" + separator + "testdata" + separator + fileName + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        int totalRows = worksheet.getLastRowNum();
        int rowIndex = -1;
        for (int i = 1; i <= totalRows; i++) {
            String cellValue = returnRow(worksheet.getRow(i), 0);
            if (valueToFindRowIndex.equalsIgnoreCase(cellValue)) {
                rowIndex = i;
                break;
            }
        }

        XSSFRow xssfRow = worksheet.getRow(rowIndex);
        XSSFCell xssfCell = xssfRow.getCell(columnIndex);
        if (xssfCell == null) {
            xssfCell = xssfRow.createCell(columnIndex);
        }
        xssfCell.setCellValue(valueToSet);
        FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                + "resources" + separator + "testdata" + separator + fileName + ".xlsx");
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static String[][] readSnoAndSkusFromExcel(String fileName, String sheetName) throws Exception {
        String[][] objectArray;
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + separator + "src" + separator + "main" + separator
                        + "resources" + separator + "testdata" + separator + fileName + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet worksheet = workbook.getSheet(sheetName);
        int totalRows = worksheet.getLastRowNum();
        int totalColumns = 2;
        objectArray = new String[totalRows][totalColumns];
        for (int i = 1; i <= totalRows; i++) {
            XSSFRow xssfRow = worksheet.getRow(i);
            objectArray[i - 1][0] = returnRow(xssfRow, 0);
            objectArray[i - 1][1] = returnRow(xssfRow, 1);
        }

        return objectArray;
    }
}
