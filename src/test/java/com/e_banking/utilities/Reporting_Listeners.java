package com.e_banking.utilities;

// listener class used to generate Extent reports
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class Reporting_Listeners extends TestListenerAdapter
{
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart (ITestContext testContext)
	{
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repName);//specify location
		//htmlReporter=new ExtentHtmlReporter("G:\\My Drive\\OtherFolders\\Courses\\SoftwareTestEngineering\\Java\\eclipse\\e-Banking\\e_banking_V1\\test-output\\Test_Report11.html");//specify location
		//htmlReporter.loadXMLConfig("G:\\My Drive\\OtherFolders\\Courses\\SoftwareTestEngineering\\Java\\eclipse\\e-Banking\\e_banking_V1\\extent-config.xml");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");

		extent =new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Enviroment","QA");
		extent.setSystemInfo("user","kaan");
		
		
		htmlReporter.config().setDocumentTitle("e_banking_V1 Test Report"); // Title of report
		htmlReporter.config().setReportName("Functional Test Report");// name of report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);//location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
	}
	public void onTestSuccess(ITestResult tr) // ITestResult tr = status of the test method which is in TC_LoginTest_001 
	{
		logger=extent.createTest(tr.getName()); //create new test/entry in the report -- tr.getName()= loginTest which is in TC_LoginTest_001 
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); //send the passed information 
		System.out.println(tr.getName()+"Deneme");
	}
	
	
	public void onTestFailure(ITestResult tr) // ITestResult tr = status of the test method which is in TC_LoginTest_001
	{
		logger=extent.createTest(tr.getName()); //create new entry in the report
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); //send the failed information
		logger.log(Status.FAIL,"Test case failed:"+ tr.getThrowable());
		
	
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+".png"; 
// taking screenshot is common for every test case.. therefore it's setup belongs to baseclass
		
		File f = new File(screenshotPath);
		
		if(f.exists())
		{
			try 
			{
				logger.fail("Screenshot is below: "+ logger.addScreenCaptureFromPath(screenshotPath));
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
			
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		logger=extent.createTest(tr.getName());//create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush(); // clear the memory
	}
	
}
