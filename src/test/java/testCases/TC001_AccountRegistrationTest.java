package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.accountRegistrationPage;
import pageObjects.homePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	@Test(groups={"Regression","Master"})
	public void verifyAccountRegistration()
	{
		logger.info("***Starting TC001_AccountRegistrationTest***");
		
		try // need try catch the test case if there,s exception on test.
		{
		homePage hp = new homePage(driver);
		hp.clickAccount();
		logger.info("Click myAccount Link");
		
		hp.clickRegister();
		logger.info("Click Register");
		accountRegistrationPage acc = new accountRegistrationPage(driver);
		acc.setFirstName(randomAlphabet(7).toUpperCase());
		acc.setLastName(randomAlphabet(7).toUpperCase());
		acc.setEmail(randomAlphabet(7)+"@hotmail.com");
		acc.setPhone(randomNumber(7));
		acc.setPassword("040782");
		acc.confirmPassword("040782");
		logger.info("Fill all the customer details");
		
		acc.clickTC();
		acc.clickContinue();
		logger.info("Continue");
		
		String confMsg = acc.getConfirmationMsg();
		if (confMsg.equals("Your Account Has Been Created!"))
			{
			Assert.assertTrue(true);
			}
		else
			{
			logger.error("Test Failed..."); //send error message
			logger.debug("debug logs..."); //send debug logs
			Assert.assertTrue(false);
			}

		logger.info("Validating the result");
		}
		catch(Exception e)
			{
			e.printStackTrace();;
			}
		
		logger.info("***Finished TC001_AccountRegistrationTest***");
	}
}
