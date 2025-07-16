package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccount extends basePage{
	
	public myAccount (WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement accHeader;
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement myAccountLink;
	
	@FindBy(xpath="//a[normalize-space()='Logout']")
	WebElement logoutLink;
	
	public boolean isMyAccountExist()
	{
		try 
		{
		return(accHeader.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogout()
	{
		myAccountLink.click();
		logoutLink.click();
	}
}
