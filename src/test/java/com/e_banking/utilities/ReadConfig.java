package com.e_banking.utilities;

// reading variables and its value of config.properties file... and pass it to baseclass


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro; // pro is an object
	
	
//Create constructor
	public ReadConfig()
	{
		File src = new File ("./Congfiguration/config.properties");
		//src variable which is refering to config.properties
		
		try 
		{
			FileInputStream fis= new FileInputStream(src); // to read the data from config.properties 
			pro = new Properties();
			pro.load(fis);
			
		}
		catch(Exception e) 
		{
			System.out.println("Exception is " + e.getMessage());
		}
		
		}
		public String getApplicationURL()
		{
			String url=pro.getProperty("baseURL");
			return url;
		}
		
		public String getUsername()
		{
			String username=pro.getProperty("username");
			return username;
		}
		public String getPassword()
		{
			String password=pro.getProperty("password");
			return password;
		}
		public String getChromePath()
		{
			String chromepath=pro.getProperty("chromepath");
			return chromepath;
		}
		
		

}
