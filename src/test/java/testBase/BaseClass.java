package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public WebDriver driver;
	public Logger logger;
	public Properties propertyObj;
	
	@BeforeClass(groups={"Sanity","Regression","Master",}) //even though BaseClass already extended, we still need to specify the group here, bcoz group only run method that have the corresponded group name
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException, InterruptedException
	{
		//Loading config.properties file
		propertyObj = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//config.properties");
		propertyObj.load(file);	
		
		//for Grid setup
		if(propertyObj.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}
			
			//browser
			switch(browser.toLowerCase())
			{
			//Use exact BrowserName firefox, Internet Explorer, MicrosoftEdge, chrome (taken from the info on the selenium server hub cmd)
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); break;
			}
			driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(),capabilities); //must declare driver
		}
		
		if(propertyObj.getProperty("execution_env").equals("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("invalid browser"); return; //if there's no match case, return keyword will stop the method
			}
		}
			
		logger=LogManager.getLogger(this.getClass()); //will get the class name, get the log, and store it to logger
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(propertyObj.getProperty("appURL"));
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() throws InterruptedException
	{
		driver.quit();
	}
	
	public String randomAlphabet(int length)
	{
		String generatedString = UUID.randomUUID().toString().replaceAll("-", "");
		String lettersOnly = generatedString.replaceAll("[^a-zA-Z]", ""); //means "any character that is NOT a-z or A-Z".
		//if not enough character
		while(lettersOnly.length()<length)
		{
			generatedString = UUID.randomUUID().toString().replaceAll("-", "");
			lettersOnly += generatedString.replaceAll("[^a-zA-Z]", "");
		}
		return lettersOnly.substring(0,length); //(0,length) is start and end index
	}
		
	public String randomNumber(int length)
	{
		String generatedString = UUID.randomUUID().toString().replaceAll("-", "");
		String lettersOnly = generatedString.replaceAll("[a-zA-Z]", ""); //means "any character that is a-z or A-Z".
		//if not enough character
		while(lettersOnly.length()<length)
		{
			generatedString = UUID.randomUUID().toString().replaceAll("-", "");
			lettersOnly += generatedString.replaceAll("[a-zA-Z]", "");
		}
		return lettersOnly.substring(0,length); //(0,length) is start and end index
	
	}
	
	public String capturedScreen(String tname)
	{
		String timeFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFileName = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeFormat+".PNG";
		File targetFile = new File(targetFileName);
		sourceFile.renameTo(targetFile);
		return targetFileName;
	}

}
