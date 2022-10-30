package com.e_banking.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.e_banking.utilities.ReadConfig;

public class BaseClass {
	
//---------
	
/*
//-- here was valid before creating the config.properties and ReadConfig.java files
//--  instead of use this out of comment part ReadConfig readconfig = new ReadConfig();

// this is common for every test cases-- 2. olarak burasi run eder 
	public String baseURL="https://demo.guru99.com/v4/index.php";
	public String username="mngr445170";
	public String password="ydubeja";
*/



// this is common for every test cases-- 2. olarak burasi run eder 

// this become more flexible for general code method
	ReadConfig readconfig = new ReadConfig();
	
	
	public String baseURL=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();


	
// ----------
	public static WebDriver driver;
	public static Logger logger;
		
	@Parameters("browser") // this should take the parameter from xml file for Run test case on desired browser
	@BeforeClass // ilk once burasi run eder--1
	public void setup(String br) // getting parameter br from  @Parameters("browser") 
	{
			
		//Adding logs
		BasicConfigurator.configure();
		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "/Drivers/chromedriver.exe");
		driver=new ChromeDriver();
		System.out.println("chrome is running");

		}
		else if (br.equals("firefox"))
		{
			System.out.println("firefox driver needed");
		}
		else if (br.equals("ie"))
		{
			System.out.println("internet explorer driver needed");

		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.get(baseURL);

	}
	
	@AfterClass // en son burasi run eder .-3
	public void tearDown() 
	{
		driver.quit();
	}

	
// taking screenshot is common for every test case.. therefore it's setup belongs to baseclass
	public void captureScreen(WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		System.out.println("Screenshot taken");
		FileUtils.copyFile(source,target);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
