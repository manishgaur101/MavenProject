package commonutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constants.FilePath;

public class ExcelUtil {

	private static XSSFWorkbook wb;
	private static XSSFSheet ws;
	private static XSSFRow row;
	static XSSFCell cell;

	/** Method to set workbook and sheet object */
	public static void setExcelFileSheet(String sheetName) {
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath.Excel_File_Path);
			wb = new XSSFWorkbook(ExcelFile);
			ws = wb.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/** Method to get Excel cell Value */
	public static String getCellData(int rowNum, int colNum) {
		String cellValue = "";
		try {
			XSSFCell cell = ws.getRow(rowNum).getCell(colNum);
			cellValue = cell.getStringCellValue();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return cellValue;
	}

	/** Method to return row values */
	public static Object[][] getRowData(String sheetName, int rowNum) {
		
		
		Object[][] rowData = new Object[1][2];
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath.Excel_File_Path);
			wb = new XSSFWorkbook(ExcelFile);
			ws = wb.getSheet(sheetName);
			XSSFRow rowValue = ws.getRow(rowNum);
			int cellCount = rowValue.getLastCellNum();
			int counter = 0;
			while(cellCount>0){
				rowData[0][counter] = rowValue.getCell(counter).getStringCellValue();
				cellCount--;
				counter++;
			}

		} catch (Exception e) {
			e.printStackTrace();

			
		}
		return rowData;

	}
/** Method to return all rows data*/
public static Object[][] getAllRows(String sheetName) {
	Object[][] rowData = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath.Excel_File_Path);
			wb = new XSSFWorkbook(ExcelFile);
			ws = wb.getSheet(sheetName);
			int totalRow = ws.getLastRowNum();
			System.out.println(totalRow);
			int rowCounter = 0;
			while(totalRow>0){
			XSSFRow rowValue = ws.getRow(rowCounter);
			int cellCount = rowValue.getLastCellNum();
			int cellCounter = 0;
			rowData = new Object[totalRow+1][cellCount];
			while(cellCount>0){
				rowData[rowCounter][cellCounter] = rowValue.getCell(cellCounter).getStringCellValue();
				cellCount--;
				cellCounter++;
			}

		} 
		rowCounter++;	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rowData;

	}

	
	
	/** Method to set Excel cell Value */
	public static void setCellData(String sheetName, String data, int rowNum, int colNum) {
		createWorkbook();
		createSheet(sheetName);
		FileOutputStream fos = null;
		row = ws.getRow(rowNum);
		cell = row.getCell(colNum);
		if (cell == null) {
			cell = row.createCell(colNum);
			cell.setCellValue(data);
		} else {
			cell.setCellValue(data);
		}

		try {
			fos = new FileOutputStream(FilePath.Excel_File_Path);
			wb.write(fos);
			fos.flush();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/** Method which shows total active number of rows in a sheet */
	public static int getRowCount(String sheetName) {
		setExcelFileSheet(sheetName);
		int total_rows = ws.getLastRowNum();
		if (total_rows == -1) {
			total_rows = 0;
		} else {
			return total_rows;
		}
		return total_rows;

	}

	public static void createWorkbook() {
		wb = new XSSFWorkbook();
	}

	public static void createSheet(String sheetName) {
		ws = wb.createSheet(sheetName);
	}
	/*
	 * public static String getSheetName(){
	 * 
	 * }
	 */
}
