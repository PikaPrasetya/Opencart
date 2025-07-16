package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basePage {
	
	public loginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@name='email']")
	WebElement emailField;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordField;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	public void setEmail(String email)
	{
		emailField.sendKeys(email);
	}
	
	public void setPassword(String password)
	{
		passwordField.sendKeys(password);
	}
	
	public void clickLogin()
	{
		loginBtn.click();
	}

}
