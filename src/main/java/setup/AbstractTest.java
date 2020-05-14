package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pagefactory.CorePageFactory;
import utils.Constants;

public class AbstractTest {
	
	public static Logger log;
	public CorePageFactory corePageFactory;
	static Properties configProp, testDataProp;
	public static WebDriver driver;
	public static String filePath=Constants.FAILED_SCREENSHOT_PATH;
//	public static ChromeDriver driver;
	String os;
	URL dockerUrl;
	DesiredCapabilities dcap;
	ChromeOptions options;
	FirefoxOptions firefoxOptions ;
	 
	@Parameters({"browser"})
	@BeforeTest
	public void loadPreRequisites(String browser) throws MalformedURLException
	{
		switch (browser.toLowerCase()) {
		case "chrome":
			
			os = System.getProperty("os.name").toLowerCase();
			if (os.contains("mac")) 
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER);
				options = new ChromeOptions();
				options.addArguments("--disable-infobars");
				driver=new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);		
			}
			else if (os.contains("linux"))
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_Linux);
				dcap = DesiredCapabilities.chrome();
				options = new ChromeOptions();
				options.addArguments("--headless"); 
				options.addArguments("--disable-extensions");                                                                                                                                  
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-infobars");
				options.addArguments("--disable-web-security");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--whitelisted-ips");
				options.addArguments("--verbose"); 
				options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver=new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
				
//				    
//				options.addArguments("--disable-gpu");
//				options.addArguments("--disable-web-security");
//				options.addArguments("--dns-prefetch-disable");
					
			}
			else
			{
				System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_WIN);	
				options = new ChromeOptions();
				options.addArguments("--disable-infobars");
				driver=new ChromeDriver(options);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);	
			}
		
			break;
		case "firefox":	
			os = System.getProperty("os.name").toLowerCase();
			if (os.contains("mac")) 
			{
				System.setProperty("webdriver.gecko.driver",Constants.FIREFOX_DRIVER);
				driver= new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);		
			}
			else if (os.contains("linux"))
			{
				System.setProperty("webdriver.gecko.driver",Constants.FIREFOX_DRIVER_Linux);
				firefoxOptions = new FirefoxOptions();
				firefoxOptions.addArguments("--headless"); 
				firefoxOptions.addArguments("--disable-extensions");                                                                                                                                  
				firefoxOptions.addArguments("--no-sandbox");
				firefoxOptions.addArguments("--disable-infobars");
				firefoxOptions.addArguments("--disable-web-security");
				firefoxOptions.addArguments("--disable-dev-shm-usage");
				firefoxOptions.addArguments("--whitelisted-ips");
				firefoxOptions.addArguments("--verbose"); 
				firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
				driver= new FirefoxDriver(firefoxOptions);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
			}
			
			break;
		case "safari":
			driver= new SafariDriver();
			break;
		case "ie":
			driver= new InternetExplorerDriver();
			break;
		default:
			System.out.println("Test");
		}	
		
		waitForPageLoaded();
		DOMConfigurator.configure(Constants.LOG4J_PATH);
		loadPropertiesFile();
		log=Logger.getLogger(this.getClass().getSimpleName());
		initialPageFactory(driver);
	}
	
	public static void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
	
	
	
	public void initialPageFactory(WebDriver driver){
		corePageFactory= new CorePageFactory(driver);
	}
	
	public static void loadPropertiesFile() {
		try {
			configProp = new Properties();
			testDataProp = new Properties();
			FileInputStream confRepo = new FileInputStream(Constants.CONFIG_REPOSITORY);
			FileInputStream testDataRepo = new FileInputStream(Constants.TESTDATA_REPOSITORY);
			configProp.load(confRepo);
			testDataProp.load(testDataRepo);
			confRepo.close();
			testDataRepo.close();
		} catch (IOException e) {
			Assert.fail("Failed to load properties file" + e.getMessage());
		}
	}
	
	public static String getConfigData(String key) {
		String propertyValue = null;
		try {
			propertyValue = configProp.getProperty(key);
			if (propertyValue == null) {
				System.out.println("Unable to find the key value for the given key \"" + key + "\"");
				Assert.fail("Unable to find the key value for the given key \"" + key + "\"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue.trim();
	}
	
	public static String getTestDataConfigData(String key) {
		String propertyValue = null;
		try {
			propertyValue = testDataProp.getProperty(key);
			if (propertyValue == null) {
				System.out.println("Unable to find the key value for the given key \"" + key + "\"");
				Assert.fail("Unable to find the key value for the given key \"" + key + "\"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return propertyValue.trim();
	}
	

}
