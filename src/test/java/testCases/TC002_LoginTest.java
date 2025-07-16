package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccount;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin()
	{
		try
		{
		logger.info("***Starting TC002 Test");
		homePage hp = new homePage(driver);
		loginPage lp = new loginPage(driver);
		
		logger.info("click my account");
		hp.clickAccount();
		
		logger.info("click login");
		hp.clickLogin();
		
		logger.info("Set email & password");
		lp.setEmail(propertyObj.getProperty("email"));
		lp.setPassword(propertyObj.getProperty("password"));
		
		logger.info("click submit");
		lp.clickLogin();
		
		logger.info("Validating account");
		myAccount acc = new myAccount(driver);
			if(acc.isMyAccountExist()==true)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
				logger.error("Test failed");
				logger.debug("debug log");
			}
		}
		catch (Exception e)
		{
			Assert.fail();
		}
		logger.info("Finish Test TC002");
	}	

}
