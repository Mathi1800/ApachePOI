package com.google.ApachPOI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDriven {

	// create a list

	static List<String> userNameList = new ArrayList<String>();
	static List<String> passwordLsit = new ArrayList<String>();

	// Create a object for get excel
	public void getExcel() throws IOException {

		// getting file location we use fileInputStream

		FileInputStream excel = new FileInputStream("E:\\value.xlsx");

		// getting the workbook from the excel
		// XSSF is for higher version of the excel file
		Workbook workbook = new XSSFWorkbook(excel);

		// Getting the sheet from the excel workbook

		Sheet sheet = workbook.getSheet("Sheet1");

		// row iteration we use Iterator

		Iterator<Row> row = sheet.iterator();

		while (row.hasNext()) {
			Row rowvalue = row.next();
//column iteration
			Iterator<Cell> column = rowvalue.iterator();
			int i = 2;
			while (column.hasNext()) {
				if (i % 2 == 0) {
					userNameList.add(column.next().getStringCellValue());

				} else {
					passwordLsit.add(column.next().getStringCellValue());

				}
				i++;

			}
		}
	}

	public void test(String uName, String pWord) {
		System.setProperty("webdriver.driver.chromedriver", "D:\\\\Selenium_training\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys(uName);

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pWord);

		WebElement button = driver.findElement(By.id("submit"));
		button.click();

	}

	public void execute() {

		for (int i = 0; i < userNameList.size(); i++) {
			test(userNameList.get(i), passwordLsit.get(i));
		}
	}

	public static void main(String[] args) throws IOException {
//calling the datadriven method
		DataDriven result = new DataDriven();
		result.getExcel();
		System.out.println("Username:" + userNameList);
		System.out.println("Password" + passwordLsit);
		result.execute();
	}

}
