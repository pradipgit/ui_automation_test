package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.AbstractTest;
import utils.utility;

public class LoginPage extends AbstractTest {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="username")
    WebElement ibm_username;
	
	@FindBy(id="btn_signin")
    WebElement btn_signin;
	
	@FindBy(name="password")
    WebElement ibm_password;
	
	@FindBy(id="continue-button")
    WebElement btn_continue;
	
	@FindBy(id="signinbutton")
    WebElement signin_btn;
	
	
	public void enterUrl(String url)
	{
		driver.navigate().to(url);
		//utility.waitFor(10);
	}
	 
	
	public void enterUserName(String uName)
	{
		utility.waitUntilElementIsClickable(ibm_username);
		//utility.waitFor(2);
//		utility.waitForElement(ibm_username);
		ibm_username.clear();
		ibm_username.sendKeys(uName);
		//utility.waitFor(2);
	}
	 
	
	public void enterPassword(String pwd)
	{
		//utility.waitFor(5);
		utility.waitUntilElementIsClickable(ibm_password);
//		utility.waitForElement(ibm_password);
		ibm_password.clear();
		ibm_password.sendKeys(pwd);
		//utility.waitFor(15);
	}
	 
	
	public void clickButtonSignIn()
	{	
//		utility.waitForElement(btn_signin);
		utility.waitUntilElementIsClickable(btn_signin);
		btn_signin.click();
		utility.waitFor(2);
	}
	 
	public void clickContinueButton()
	{	
//		utility.waitForElement(btn_continue);
		utility.waitUntilElementIsClickable(btn_continue);
		btn_continue.click();
		utility.waitFor(2);
	}
	
	public void clickSignInButton()
	{
	    //utility.waitFor(5);
//		utility.waitForElement(signin_btn);
		utility.waitUntilElementIsClickable(signin_btn);
		signin_btn.click();
		utility.waitFor(2);
	
	}
	
	

}
