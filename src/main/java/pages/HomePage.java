package pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.AbstractTest;
import utils.utility;

public class HomePage extends AbstractTest {
	
	WebDriver driver;
	Actions action ;     
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 
	@FindBy(xpath="//*[@class='accept bx--btn bx--btn--primary']")
    WebElement btn_accept;
	
	@FindBy(xpath="//*[@class='bx--checkbox-label-text']")
    WebElement lbl_privacy_stmt;
	
	@FindBy(id="privacy-policy-link")
    WebElement link_privayPolicy;
	
	@FindBy(id="privacy-policy-modal_carbon-button_submit")
    WebElement btn_submit;
	

	public void clickPrivacyStatement()
	{
		utility.waitUntilElementIsClickable(lbl_privacy_stmt);
		lbl_privacy_stmt.click();
	}
	
	public void clickAcceptButton()
	{
		utility.waitFor(3);
		utility.waitUntilElementIsClickable(btn_accept);
		btn_accept.click();
		utility.waitFor(2);
	}
	
	public void clickPrivacyLink()
	{
		link_privayPolicy.click();
		String mainWindow = getMainWindowHandle(driver);
		closeAllOtherWindows(mainWindow);
	}
	
	public void clickSubmitButton()
	{
		utility.waitUntilElementIsClickable(btn_submit);
		btn_submit.click();
	}
	
	public String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	public boolean closeAllOtherWindows(String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}
		
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	
//	public void clickUserIcon()
//	{
//		utility.waitUntilElementNotVisible(img_spinner);
//		utility.waitUntilElementIsClickable(btn_userIcon);
//		btn_userIcon.click();
//		utility.waitFor(2);
//	}
//	
//	public void clickAccountSubMenu()
//	{
//		btn_accountSubMenu.click();
//		utility.waitUntilElementIsClickable(link_masterAccountTab);
//		utility.waitUntilElementIsClickable(link_AssetAccountTab);
//		utility.waitUntilElementIsClickable(btn_newAccount);
//		utility.waitUntilElementNotVisible(img_spinner);
//	}
//	
//	
//	public void clickMasterAccountTab()
//	{
//		utility.waitUntilElementIsClickable(link_masterAccountTab);
//		link_masterAccountTab.click();
//		utility.waitUntilElementNotVisible(img_spinner);
//	}
//	
//	public void clickAssetAccountTab()
//	{
//		utility.waitUntilElementIsClickable(link_AssetAccountTab);
//		link_AssetAccountTab.click();
//		utility.waitUntilElementNotVisible(img_spinner);
// 
//	}
//	
//	public void clickNewAccount()
//	{
//		utility.waitUntilElementIsClickable(btn_newAccount);
//		btn_newAccount.click();
//		utility.waitUntilElementNotVisible(img_spinner);
//		
//	}
//	 
//	public void clickAwsProvider()
//	{
//		utility.waitUntilElementIsClickable(btn_aws_billing);
//		btn_aws_billing.click();
//		utility.waitUntilElementNotVisible(img_spinner);
//	}
//	
//	
//	public void createAwsBilling(String accountName,String description,String accountNumber,String s3Bucket)
//	{
//		utility.waitUntilElementIsClickable(txt_userName);
//		utility.waitUntilElementIsClickable(txt_description);
//		utility.waitUntilElementIsClickable(txt_accountNumber);
//		utility.waitUntilElementIsClickable(txt_s3Bucket);
//		utility.waitUntilElementIsClickable(btn_createAccount);
//		
//		txt_userName.clear();
//		txt_userName.sendKeys(accountName);
//		
//		txt_description.clear();
//		txt_description.sendKeys(description);
//		
//		txt_accountNumber.clear();
//		txt_accountNumber.sendKeys(accountNumber);
//		
//		txt_s3Bucket.clear();
//		txt_s3Bucket.sendKeys(s3Bucket);
//		
//		btn_createAccount.click();
//
//		utility.waitUntilElementNotVisible(img_spinner);
// 
//		
//		utility.waitUntilElementIsClickable(btn_alertMessageClose);
//		btn_alertMessageClose.click();
//		
//		link_account_management.click();
//		
//		utility.waitUntilElementNotVisible(img_spinner);
//		
//	}
//	
//	public void searchAccount(String accountName)
//	{
//		utility.waitUntilElementIsClickable(txt_accountSearch);
//		txt_accountSearch.clear();
//		txt_accountSearch.sendKeys(accountName);
//		action = new Actions(driver);	   
//		action.sendKeys(Keys.ENTER).build().perform();
//		utility.waitUntilElementNotVisible(img_spinner);
//	}
//	
//	public void clickOverflowMenu()
//	{
//		utility.waitUntilElementIsClickable(btn_overflowMenu);
//		btn_overflowMenu.click();
//	}
//	
//	public void clickDeleteOverflowMenu()
//	{
//		utility.waitUntilElementIsClickable(btn_overflowMenu_delete);
//		btn_overflowMenu_delete.click();
//	}
//	
//	public void clickDeleteAlertMessage()
//	{
//		utility.waitUntilElementIsClickable(btn_delete_alert);
//		btn_delete_alert.click();
//		utility.waitUntilElementNotVisible(img_spinner);
//	}
//	
//	public String getAccountName()
//	{	
//		System.out.println(lbl_getAccountName.getText());
//		return lbl_getAccountName.getText();
//	}
//	
//	public String getNoRecordMessage()
//	{	
//		System.out.println(lbl_getNoRecordMessage.getText());
//		return lbl_getNoRecordMessage.getText();
//	}
//	
	
	
	
	
	
	 
	

}
