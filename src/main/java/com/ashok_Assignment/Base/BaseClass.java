package com.ashok_Assignment.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ashok_Assignment.utils.UtilClass;



public class BaseClass {

	public static WebDriver driver=null;
	public static Properties prop=null;
	
	//Initialize the property file
	public BaseClass(){
		prop=new Properties();
		
		//location of the property file
		String filePath="C:\\Users\\ASHOK\\workspace\\Selenium_Coding_Assignment_21-04-2019\\src\\main\\"
				+ "java\\com\\ashok_Assignment\\configuration\\config.properties";
		try {
			FileInputStream fis=new FileInputStream(new File(filePath));
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println("Properties File not found at given location");
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	
	//Initialize the browser
	public void initialization(){
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeDriverPath"));
			driver=new ChromeDriver();
		}else if(browserName.equals("firefox")){
			System.setProperty("webdriver.chrome.driver", prop.getProperty("firefoxDriverPath"));
			driver=new FirefoxDriver();

		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(UtilClass.pageloadTimeout, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
	public void takeScreenShot(String methodName) {
	   	 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        //The below method will save the screen shot in the drive with test method name 
	           try {
					FileUtils.copyFile(scrFile, new File(UtilClass.screenShotFilePath+methodName+".jpg"));
					System.out.println("***Placed screen shot in "+UtilClass.screenShotFilePath+" ***");
				} catch (IOException e) {
					e.printStackTrace();
				}
	   }

}
