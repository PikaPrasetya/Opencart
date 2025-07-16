package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{
	
	public homePage(WebDriver driver) //Constructor of homePage that takes a WebDriver and passes it to the parent class using super(driver).
	{
		super(driver); //Calls the parent (BasePage) constructor to initialize the driver
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement accountLink;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement loginLink;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement invalidAccMsg;
	
	public void clickAccount()
	{
		accountLink.click();
	}
	
	public void clickRegister()
	{
		registerLink.click();
	}

	public void clickLogin()
	{
		loginLink.click();
	}
	
	public boolean invAccMsg()
	{
		boolean invAcc = invalidAccMsg.isDisplayed();
		return invAcc;
	}
}
