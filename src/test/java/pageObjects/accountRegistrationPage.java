package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountRegistrationPage extends basePage {
	
	public accountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstNameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement phoneField;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirmPasswordField;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement termCondition;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement accCreatedMsg;
	
	public void setFirstName(String firstName)
	{
		firstNameField.sendKeys(firstName);
	}
	
	public void setLastName(String lastName)
	{
		lastNameField.sendKeys(lastName);
	}
	
	public void setEmail(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void setPhone(String phone)
	{
		phoneField.sendKeys(phone);
	}
	
	public void setPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void confirmPassword(String password)
	{
		confirmPasswordField.sendKeys(password);
	}
	
	public void clickTC()
	{
		termCondition.click();
	}
	
	public void clickContinue()
	{
		continueButton.click();
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			accCreatedMsg.getText();
			return accCreatedMsg.getText();
		}
		catch(Exception e)
		{
			e.getMessage();
			return e.getMessage(); //if the message is not shown, the exception message will be returned
		}
	}

}
