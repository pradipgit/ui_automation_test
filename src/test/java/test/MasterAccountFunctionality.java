package test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import setup.AbstractTest;
import utils.utility;

public class MasterAccountFunctionality extends AbstractTest {
	
	
	public String accountName,description,accountNumber,s3Bucket;
	
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
	
	@Test(priority=2,description = "Create Master Account",dependsOnMethods="acceptPrivacyStatement")
	public void createMasterAccount()
	{	
		try
		{	
			accountName=getTestDataConfigData("accountName")+ utility.generateRandomNumber();
			description= getTestDataConfigData("description");
			accountNumber= getTestDataConfigData("accountNumber");
			s3Bucket = getTestDataConfigData("s3Bucket");
				
			corePageFactory.accountPage.clickUserIcon();
			log.info("Clicked on User Icon");
			
			corePageFactory.accountPage.clickAccountSubMenu();
			log.info("Clicked on Account Sub Menu");
			
			corePageFactory.accountPage.clickMasterAccountTab();
			log.info("Clicked on Master Account Tab");
			
			corePageFactory.accountPage.clickNewAccount();
			log.info("Clicked on New Master Account");
			
			corePageFactory.accountPage.clickAwsProvider();
			log.info("Clicked on AWS Provider");
			
			corePageFactory.accountPage.createAwsBilling(accountName,description,accountNumber,s3Bucket);
				
		}
		catch(Exception e)
		{
			log.error(e);
			Assert.fail("Failed to Create Master Account");
		}
		
	}
	
	@Test(priority=3,description = "Search Account",dependsOnMethods="createMasterAccount")
	public void searchMasterAccount()
	{	
		try
		{
			corePageFactory.accountPage.searchAccount(accountName);
			Assert.assertEquals(corePageFactory.accountPage.getAccountName(),accountName);
			log.info("Searching for Account Name");			
		}
		catch(Exception e)
		{
			log.error(e);
			Assert.fail("Failed to Search Account");
		}
		
	}
	
	
	@Test(priority=4,description = "Delete Master Account",dependsOnMethods="searchMasterAccount")
	public void deleteMasterAccount()
	{	
		try
		{
			corePageFactory.accountPage.clickOverflowMenu();
			log.info("Clicked on Over flow Menu");
			
			corePageFactory.accountPage.clickDeleteOverflowMenu();
			log.info("Clicked on Delete Menu");
			
			corePageFactory.accountPage.clickDeleteAlertMessage();
			log.info("Clicked on Delete from alert message");
			
			corePageFactory.accountPage.searchAccount(accountName);
			
			Assert.assertEquals(corePageFactory.accountPage.getNoRecordMessage(),"No Data Available");
			log.info("Searching for Account Name");		
	
		}
		catch(Exception e)
		{
			log.error(e);
			Assert.fail("Failed to Delete Master Account");
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
