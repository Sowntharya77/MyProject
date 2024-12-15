package org.stepdefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {
public static WebDriver driver;
	
	public  static void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}
	
	public static void launchUrl(String url) {
		driver.get(url);
	}
	
	public static String pageTitle() { //we returning some value
		String title = driver.getTitle();
		return title;
	}
	
	public static String pageUrl() {   //we returning some value
		String url = driver.getCurrentUrl();
		return url;
	}
	
	public static void passText(String txt, WebElement ele) {
		ele.sendKeys(txt);
	}
	
	public static void closeEntireBrowser() {
		driver.quit();
	}
	
	public static void clickBtn(WebElement ele) {
		ele.click();
	}
	
	public static void screenshot(String imgName)throws IOException{
		TakesScreenshot ts= (TakesScreenshot) driver;
		File image = ts.getScreenshotAs(OutputType.FILE);
		File f = new File("locatio+ imgName.png");
		FileUtils.copyFile(image,  f);
	}
	
	public static Actions a;
	
	public static void moveTheCursor(WebElement targetWebElement) {
		a = new Actions(driver);
		a.moveToElement(targetWebElement).perform();
	}
	
	public static void dragDrop(WebElement dragWebElement, WebElement dropElement) {
		a = new Actions(driver);
		a.dragAndDrop(dragWebElement, dragWebElement).perform();
	}
	
	public static JavascriptExecutor js;
	
	public static void scrollThePage(WebElement tarWebEle) {
		js = (JavascriptExecutor)driver;
		js.executeScript("argument[0].scrollIntoView(true)", tarWebEle);
	}
	
	public static void scroll(WebElement element) {
		js = (JavascriptExecutor)driver;
		js.executeScript("argument[0].scrollIntoView(false)", element);
	}
	
	public static void excelRead(String sheetName, int rowNum, int cellNum)throws IOException{
	File f = new File("Excel location.xlsx");
	FileInputStream fil = new FileInputStream(f);
	Workbook wb= new XSSFWorkbook(fil);
	Sheet mySheet = wb.getSheet("Data");
	Row r = mySheet.getRow(rowNum);
	Cell c = r.getCell(cellNum);
	int cellType = c.getCellType();
	
	String value = " ";
	System.out.println(value);
	
	if(cellType==1) {
		String value2 = c.getStringCellValue();
		System.out.println(value2);
	}
	
	else if(DateUtil.isCellDateFormatted(c)) {
		Date dt = c.getDateCellValue();
		SimpleDateFormat fmt = new SimpleDateFormat("value");//date format(we can change )
		String value1 = fmt.format(dt); //format method	
		System.out.println(value1);
	}
	
	else {
		double d = c.getNumericCellValue();
		long l = (long)d; //downcasting
		String valueof = String.valueOf(l);
		System.out.println(valueof);
	}}
	
	public static void createNewExcelFile(int rowNum, int cellNum, String writeData)throws IOException{
		File f = new File("Excel location.xlsx");
		Workbook w= new XSSFWorkbook();
		Sheet newSheet = w.createSheet("Data");
		Row newRow = newSheet.createRow(rowNum);
		Cell newCell = newRow.createCell(cellNum);
		newCell.setCellValue(writeData);
		FileOutputStream fos = new FileOutputStream(f);
		w.write(fos);
	}
	
	public static void createCell(int getRow, int creCell, String newData)throws IOException{
		File f = new File("Excel location.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook wb= new XSSFWorkbook(fil);
		Sheet s = wb.getSheet("Data");
		Row r = s.getRow(getRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newData);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}
	
	public static void createRow(int creRow, int creCell, String newData)throws IOException{
		File f = new File("Excel location.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook wb= new XSSFWorkbook(fil);
		Sheet s = wb.getSheet("Data");
		Row r = s.createRow(creRow);
		Cell c = r.createCell(creCell);
		c.setCellValue(newData);
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}
	
	public static void updateDataToParticularCell(int getTheRow, int getTheCell, String existingData, String writeNewData)throws IOException{
		File f = new File("Excel location.xlsx");
		FileInputStream fil = new FileInputStream(f);
		Workbook wb= new XSSFWorkbook(fil);
		Sheet s = wb.getSheet("Data");
		Row r = s.getRow(getTheRow);
		Cell c = r.getCell(getTheCell);
		String str = c.getStringCellValue();
		if(str.equals(existingData)) {
			c.setCellValue(writeNewData);
		}
		FileOutputStream fos = new FileOutputStream(f);
		wb.write(fos);
	}

}

	


