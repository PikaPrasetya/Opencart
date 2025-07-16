package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods
	public String repName;
	
	public void onStart(ITestContext context) {
		//make timestamp so that report name will be flexible
		
		//method1
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String timeStamp = dateFormat.format(dt);*/
		
		//method2
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-report"+timeStamp+".html";
	    sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\reports\\"+repName); //specify the report location
	    sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //title of report
	    sparkReporter.config().setReportName("Opencart Functional Testing"); //name of report
	    sparkReporter.config().setTheme(Theme.DARK);
	    
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    
	    extent.setSystemInfo("Application", "Opencart");
	    extent.setSystemInfo("Computer Name", "localhost");
	    extent.setSystemInfo("Environment", "staging");
	    extent.setSystemInfo("User Name", System.getProperty("user.name")); //will get this pc user name
	    
	    String operatingSystem = context.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("OS", operatingSystem);
	    
	    String browser = context.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser Name", browser);	
	    
	    List<String> groupList = context.getCurrentXmlTest().getIncludedGroups();
	    if(!groupList.isEmpty()) //if no group in xml file, no group info added to the report
	    {
	    	extent.setSystemInfo("Grouping", groupList.toString()); //must convert from List<String> to String
	    }
	    
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); //create new entry in report
		test.assignCategory(result.getMethod().getGroups()); //to display group in report
		test.log(Status.PASS, "Test case PASSED is: "+result.getName()); //update status p/f/s
		
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test case FAILED is: "+result.getName());
		test.log(Status.FAIL, "Test Failed due to: "+result.getThrowable().getMessage());	
		
		try
		{
			//This class doesn't extend BaseClass, so it doesn’t inherit the capturedScreen() method., thus need new BaseClass object
			//You're creating a new BaseClass() object, It has no driver instance initialized, so driver is null, thus we need driver in BaseClass as static so that it can be used in new object
			String imgPath = new BaseClass().capturedScreen(result.getName()); //get Failed method name, passed it to capturedScreen method (and invoke it) in BaseClass, CapturedScreen method will return the image path
			test.addScreenCaptureFromPath(imgPath); //attach the img to report
		}
		catch(Exception e1) //if the screenshot is somehow not available/or not properly taken it will print the exception
		{
			e1.printStackTrace();
		}
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test case Skipped is: "+result.getName());
		test.log(Status.SKIP, "Test is skiiped due to: "+result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush(); //Writes test information from the started reporters to their output view. ExtentSparkReporter: flush output to HTML file
		
		//if you want to automatically open the report once the test finished
		/*String pathOfExtentReport = System.getProperty("user.dir")+"\\reports"+repName;
		File extentReport = new File(pathOfExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}*/
		
		//if you want to automatically send email
		/*
		try
		{
			//add dependency first -- org.apache.commons/commons-email
			File reportFile = new File(System.getProperty("user.dir") + "\\reports\\" + repName);
			URL url = reportFile.toURI().toURL();
			//create email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("your email","your email password"));
			email.setFrom("your email"); //from
			email.setSubject("Tests Result");
			email.setMsg("Please find the attached reports");
			email.addTo("target email(s)"); //receiver
			email.attach(url, "Extent report", "Here's the attached reports"); //(file location, name, description)
			email.send();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
	}
	
}
