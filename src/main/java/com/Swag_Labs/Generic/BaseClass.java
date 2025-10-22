package com.Swag_Labs.Generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import com.Swag_Labs.POM.LoginPage;


public class BaseClass {
	
	public static WebDriver driver;
	FileUtils f = new FileUtils(); 
	
	@BeforeSuite
	public void databaseConnection() {
		Reporter.log("database is connected");

	}
	
	@BeforeTest
	public void launchBrowser(@Optional("chrome") String browser) throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.saucedemo.com/");
		Reporter.log("browser launched",true);
		
	}
	
	@BeforeMethod
	public void loginToSauceDemo() throws IOException {
		LoginPage lp = new LoginPage(driver);
		String un = f.readDataFromTestdataExcel("1",1,0);
		String pw = f.readDataFromTestdataExcel("1",1,1);
		lp.login(driver, un, pw);
		
	}
	
	@AfterMethod
	public void LogoutToSauceDemo() {
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.id("logout_sidebar_link")).click();
		Reporter.log("loged out",true);
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();;
		Reporter.log("Browser closed",true);
	}
	@AfterSuite
	public void disconnectDatabase() {
		Reporter.log("database disconnected",true);
	}

}
