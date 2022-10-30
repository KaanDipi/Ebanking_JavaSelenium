package com.e_banking.testCases;

import java.io.IOException;

//import org.openqa.selenium.WebDriver;

//import org.junit.Assert;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.e_banking.pageObject.LoginPage;

public class TC_LoginTest_001 extends BaseClass
{
	
	@Test // this is common for every test cases-- 2. olarak burasi run eder (1 ve 3 : BaseClass)
	public void loginTest() throws IOException  
	{
		logger.info("URL is opened");
		
		LoginPage lp=new LoginPage(driver); // creating object of login page
		//lp.elements(driver);
		
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		System.out.println(driver.getTitle());
		logger.info("Login is successful");

		
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true); // return true result of test method..
			logger.info("Test is passed");

		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false); // return failed result of test method..
			logger.info("Test is failed");

		}
		
		
	}

}
