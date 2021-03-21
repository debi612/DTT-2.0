package com.dtt.utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Excel {

    public static XSSFSheet ExcelWSheet;
    public static XSSFWorkbook ExcelWBook;
    public static XSSFCell Cell;
    public static XSSFRow Row;
    public static String path;
    private static FileInputStream ExcelFile;

    public static void setExcelFile(String Path, String SheetName) {

        try {
            path = Path;
            ExcelFile = new FileInputStream(Path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCellData(int RowNum, int ColNum)  {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public static void setCellData(String Result, int RowNum, int ColNum) {
        try {
            Row = ExcelWSheet.getRow(RowNum);
            Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
            if (Cell == null) {
                Cell = Row.createCell(ColNum);
                Cell.setCellValue(Result);
            } else {
                Cell.setCellValue(Result);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            ExcelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static synchronized String writeExcel() {
        String cellData = "";
        int lastRow = ExcelWSheet.getLastRowNum();
        for (int i = 1; i < lastRow; i++) {
            if (getCellData(i, 1).equalsIgnoreCase("")) {
                cellData = getCellData(i, 0);
                System.out.println(cellData + " " + i);
                setCellData("Yes", i, 1);
                break;
            }
        }
        return cellData;
    }

}
