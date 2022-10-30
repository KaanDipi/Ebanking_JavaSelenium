package com.e_banking.pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
	


	WebDriver ldriver;


// this is a constructor
	public LoginPage(WebDriver rdriver) // make it as a public to access it outside of the package
	{
		ldriver=rdriver;
		
		PageFactory.initElements(rdriver, this);
			
	}
	
		
	// ldriver.findElement yapmak icin bir public void un icine koymak gerekiyor. 
	// Aksi halde asagidaki gibi yapmamiz gerek
		@FindBy(name="uid")	
		@CacheLookup
		WebElement textUserName;
		
		@FindBy(name="password")
		@CacheLookup
		WebElement textPassword;
		
		@FindBy(name="btnLogin")
		@CacheLookup
		WebElement btnLogin;
		
		@FindBy(xpath="//a[normalize-space()='Log out']")
		@CacheLookup
		WebElement lnkLogout;
	
		
		public void setUserName(String uname)
		{
			textUserName.sendKeys(uname);
			
		}
		
		
		public void setPassword(String pwd)
		{
			textPassword.sendKeys(pwd);
		}
		
		public void clickSubmit()
		{
			btnLogin.click();
		}
		
		public void clickLogout()
		{
			lnkLogout.click();
		}


}

