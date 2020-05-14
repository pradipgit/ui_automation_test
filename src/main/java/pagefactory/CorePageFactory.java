package pagefactory;

import org.openqa.selenium.WebDriver;

import pages.AccountsPage;
import pages.HomePage;
import pages.LoginPage;

public class CorePageFactory {
	
	public WebDriver driver;
	
	public LoginPage loginPage = null;
	public HomePage homePage = null;
	public AccountsPage  accountPage=null;
	
	public CorePageFactory(WebDriver driver) {
		this.driver=driver;	
		setupOnce();	
	}

	public void setupOnce() {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		accountPage = new AccountsPage(driver);
		
	}

}
