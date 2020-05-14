package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import setup.AbstractTest;

public class AssetAccountFunctionality extends AbstractTest {
	

	@Test(priority=0,description = "Login")
	public void loginToCore()
	{
		try
		{
			//enter URL		
			corePageFactory.loginPage.enterUrl(getConfigData("login_url"));	
			log.info("Entered URL");
			
		//enter user name	
			corePageFactory.loginPage.enterUserName(getConfigData("username"));	
			log.info("Entered UserName");
					
		//click continue button
			corePageFactory.loginPage.clickContinueButton();
			log.info("Clicked Continue Button");
			
		//enter password		
			corePageFactory.loginPage.enterPassword(getConfigData("password"));
			log.info("Entered Password");
			
		//click sign in button			
			corePageFactory.loginPage.clickSignInButton();
			log.info("Clicked Signin Button");
		
		}
		catch(Exception e)
		{
			Assert.fail("Login Failed");
		}
		
	}

	@Test(priority=1,description = "Accept the Privacy Statement",dependsOnMethods="loginToCore")
	public void acceptPrivacyStatement()
	{	
		try
		{
			corePageFactory.homePage.clickPrivacyStatement();
			log.info("Clicked on Privacy Statement");
			
			corePageFactory.homePage.clickPrivacyLink();
			log.info("Clicked on Privacy Link");
			
			corePageFactory.homePage.clickSubmitButton();
			log.info("Clicked on Privacy Link");
			
		}
		catch(Exception e)
		{
			Assert.fail("Failed to Accept the Privacy Statement");
		}
		
	}
	
	@AfterTest
	public void closeBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
		}
	}

}
