package pages;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import setup.AbstractTest;
import utils.utility;

public class AccountsPage extends AbstractTest {
	
	WebDriver driver;
	Actions action ;     
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	 
	@FindBy(id="user_icon_id")
    WebElement btn_userIcon;
	
	@FindBy(id="accountsSubMenuButton")
    WebElement btn_accountSubMenu;
	
	@FindBy(xpath="//*[contains(@class, 'bx--tabs__nav-link') and normalize-space(text()) = 'Master Accounts']")
    WebElement link_masterAccountTab;
	
	@FindBy(xpath="//*[contains(@class, 'bx--tabs__nav-link') and normalize-space(text()) = 'Asset Accounts']")
    WebElement link_AssetAccountTab;
	
	@FindBy(xpath="//*[@class='bx--btn bx--btn--primary bx--btn--field']")
    WebElement btn_newAccount;
	
	@FindBy(xpath="//*[@class='bx--loading__stroke']")
    WebElement img_loading_overlay;
	
	@FindBy(xpath="//a[@href ='/accounts/createaccount/aws?type=billing']")
    WebElement btn_aws_billing;
	
	@FindBy(id="text-input-accountName")
    WebElement txt_userName;
	
	@FindBy(id="text-areadescription")
    WebElement txt_description;
	
	@FindBy(id="text-input-accountNumber")
    WebElement txt_accountNumber;
	
	@FindBy(id="text-input-s3Bucket")
    WebElement txt_s3Bucket;
	
	@FindBy(xpath="//*[contains(@class, 'bx--btn bx--btn--primary') and normalize-space(text()) = 'Create Account']")
    WebElement btn_createAccount;
	
	@FindBy(className="bx--inline-notification__details")
    WebElement alert_message_section;
	
	@FindBy(className="bx--inline-notification__close-button")
    WebElement btn_alertMessageClose;
	
	@FindBy(xpath="//a[@href ='/accounts']")
    WebElement link_account_management;
	
	@FindBy(id="data-table-search__input")
    WebElement txt_accountSearch;
	
	@FindBy(className="bx--overflow-menu__icon")
    WebElement btn_overflowMenu;
	
	@FindBy(xpath="//*[contains(@class, 'bx--overflow-menu-options__btn') and normalize-space(text()) = 'Delete']")
    WebElement btn_overflowMenu_delete;
	
	@FindBy(xpath="//*[contains(@class, 'bx--btn bx--btn--danger') and normalize-space(text()) = 'Delete']")
    WebElement btn_delete_alert;
	 
	@FindBy(xpath="(//*[@class='bx--tooltip-trigger bx--tooltip-text bx--tooltip-text-normal'])[1]")
    WebElement lbl_getAccountName;
	
	@FindBy(xpath="//*[@class='bx--table-row bx--table-row-empty']/td")
    WebElement lbl_getNoRecordMessage;
	
	@FindBy(className="bx--loading__stroke")
    WebElement img_spinner;

	
	public void clickUserIcon()
	{
		utility.waitUntilElementNotVisible(img_spinner);
		utility.waitUntilElementIsClickable(btn_userIcon);
		btn_userIcon.click();
		utility.waitFor(2);
	}
	
	public void clickAccountSubMenu()
	{
		btn_accountSubMenu.click();
		utility.waitUntilElementIsClickable(link_masterAccountTab);
		utility.waitUntilElementIsClickable(link_AssetAccountTab);
		utility.waitUntilElementIsClickable(btn_newAccount);
		utility.waitUntilElementNotVisible(img_spinner);
	}
	
	
	public void clickMasterAccountTab()
	{
		utility.waitUntilElementIsClickable(link_masterAccountTab);
		link_masterAccountTab.click();
		utility.waitUntilElementNotVisible(img_spinner);
	}
	
	public void clickAssetAccountTab()
	{
		utility.waitUntilElementIsClickable(link_AssetAccountTab);
		link_AssetAccountTab.click();
		utility.waitUntilElementNotVisible(img_spinner);
 
	}
	
	public void clickNewAccount()
	{
		utility.waitUntilElementIsClickable(btn_newAccount);
		btn_newAccount.click();
		utility.waitUntilElementNotVisible(img_spinner);
		
	}
	 
	public void clickAwsProvider()
	{
		utility.waitUntilElementIsClickable(btn_aws_billing);
		btn_aws_billing.click();
		utility.waitUntilElementNotVisible(img_spinner);
	}
	
	
	public void createAwsBilling(String accountName,String description,String accountNumber,String s3Bucket)
	{
		utility.waitUntilElementIsClickable(txt_userName);
		utility.waitUntilElementIsClickable(txt_description);
		utility.waitUntilElementIsClickable(txt_accountNumber);
		utility.waitUntilElementIsClickable(txt_s3Bucket);
		utility.waitUntilElementIsClickable(btn_createAccount);
		
		txt_userName.clear();
		txt_userName.sendKeys(accountName);
		
		txt_description.clear();
		txt_description.sendKeys(description);
		
		txt_accountNumber.clear();
		txt_accountNumber.sendKeys(accountNumber);
		
		txt_s3Bucket.clear();
		txt_s3Bucket.sendKeys(s3Bucket);
		
		btn_createAccount.click();

		utility.waitUntilElementNotVisible(img_spinner);
 
		
		utility.waitUntilElementIsClickable(btn_alertMessageClose);
		btn_alertMessageClose.click();
		
		link_account_management.click();
		
		utility.waitUntilElementNotVisible(img_spinner);
		
	}
	
	public void searchAccount(String accountName)
	{
		utility.waitUntilElementIsClickable(txt_accountSearch);
		txt_accountSearch.clear();
		txt_accountSearch.sendKeys(accountName);
		action = new Actions(driver);	   
		action.sendKeys(Keys.ENTER).build().perform();
		utility.waitUntilElementNotVisible(img_spinner);
	}
	
	public void clickOverflowMenu()
	{
		utility.waitUntilElementIsClickable(btn_overflowMenu);
		btn_overflowMenu.click();
	}
	
	public void clickDeleteOverflowMenu()
	{
		utility.waitUntilElementIsClickable(btn_overflowMenu_delete);
		btn_overflowMenu_delete.click();
	}
	
	public void clickDeleteAlertMessage()
	{
		utility.waitUntilElementIsClickable(btn_delete_alert);
		btn_delete_alert.click();
		utility.waitUntilElementNotVisible(img_spinner);
	}
	
	public String getAccountName()
	{	
		System.out.println(lbl_getAccountName.getText());
		return lbl_getAccountName.getText();
	}
	
	public String getNoRecordMessage()
	{	
		System.out.println(lbl_getNoRecordMessage.getText());
		return lbl_getNoRecordMessage.getText();
	}
	
	
	
	
	
	
	 
	

}
