package com.tutorialninja.qa.utilities;

import java.io.*;

import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	public static final int implicit_wait_time =20;
	public static final int page_load_time =20;
	 public static String generateEmailByTimestamp() {
		 
		Date date = new Date();
	    String timeStamp= date.toString().replace(" ", "_").replace(":","_");
	    return "aksh"+timeStamp+"@gmail.com";
	}
	 public static Object[][] getTestDataFromExcel(String sheetName) {
		 File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\com\\qa\\testdata\\TutorialNinja1.xlsx");
		 XSSFWorkbook workbook=null;
		 try {
		 FileInputStream fis = new FileInputStream(file);
	     workbook = new XSSFWorkbook(fis);
		 }catch(Throwable e) {
			 e.printStackTrace();
		 }
		 XSSFSheet sheet = workbook.getSheet(sheetName);
		 int rows = sheet.getLastRowNum();
		 short cells = sheet.getRow(0).getLastCellNum();
		 
		 Object[][] data = new Object[rows][cells];
		 for(int i=0;i<rows;i++) {
			 XSSFRow row = sheet.getRow(i+1);
			 for(int j=0;j<cells;j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType) {
				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]= Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]= cell.getBooleanCellValue();
					break;
					
				}
			 }
		 }
		 return data;
	 }
	 public static String catureScreenshot(WebDriver driver , String testName) {
		 File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destinationPath= System.getProperty("user.dir")+"\\Screenshot\\+"+testName+".png";
			try {
				FileHandler.copy(srcScreenshot, new File(destinationPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return destinationPath;
	 }
}

