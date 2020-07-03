package testcases;

import java.util.HashMap;
import java.util.Map;

import commonutil.ExcelReader;
import constants.FilePath;

public class Temp2 {

	public static void main(String[] args) {
		
		ExcelReader excel = new ExcelReader(FilePath.Excel_File_Path, "Sheet1");
		Map<String, String> map = excel.getRowData(2);
		System.out.println(map.get("UserName"));
		System.out.println(map.get("Password"));

	}

}
