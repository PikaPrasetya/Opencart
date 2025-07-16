package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePage;
import pageObjects.loginPage;
import pageObjects.myAccount;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT_2 extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")
	public void validateLogin(String email, String password, String status)
	{
		//try
		//{
			logger.info("Start test account");
			homePage hp = new homePage(driver);
			hp.clickAccount();
			hp.clickLogin();
			
			logger.info("login page");
			loginPage lp = new loginPage(driver);
			lp.setEmail(email);
			lp.setPassword(password);
			lp.clickLogin();
			
			logger.info("validate account");
			myAccount acc = new myAccount(driver);
			boolean accStatus = acc.isMyAccountExist();
		
			if(status.equalsIgnoreCase("valid"))
			{
				if(accStatus==true)
				{					
					acc.clickLogout();
					Assert.assertTrue(true);
				}
				else if(hp.invAccMsg()==true)
				{
					Assert.assertTrue(false);
				}
			}
			else if(status.equalsIgnoreCase("invalid"))
			{
				if(accStatus==true)
				{
					acc.clickLogout();
					Assert.assertTrue(false);
				}
				else if(hp.invAccMsg()==true)
				{
					Assert.assertTrue(true);
				}
			}
		/*}
		catch(Exception e)
		{
			Assert.fail();
		}*/
	}

}
