package utils;

import java.io.File;

public class Constants{
	//set your project folder here
	public static final String PROJECT_HOME = System.getProperty("user.dir");
	
	//Drivers Path
	public static final String CHROME_DRIVER= PROJECT_HOME + File.separator + "Drivers" + File.separator + "chromedriver";
	public static final String CHROME_DRIVER_WIN= PROJECT_HOME + File.separator + "Drivers" + File.separator + "chromedriver.exe";
	public static final String CHROME_DRIVER_Linux= PROJECT_HOME + File.separator + "Drivers" + File.separator + "chromedriver_linux";
	
	
	public static final String FIREFOX_DRIVER= PROJECT_HOME + File.separator + "Drivers" + File.separator + "geckodriver";
	public static final String FIREFOX_DRIVER_WIN= PROJECT_HOME + File.separator + "Drivers" + File.separator + "geckodriver.exe";
	public static final String FIREFOX_DRIVER_Linux= PROJECT_HOME + File.separator + "Drivers" + File.separator + "geckodriver_linux";
	
	public static final String Slack_Channel_File= PROJECT_HOME + File.separator + "config" + File.separator + "slack.json";
	
	public static final String EXTENT_HTML= PROJECT_HOME + File.separator + "target" + File.separator + "ExtentReport.html";
	
	public static final String RESULT_TXT= PROJECT_HOME + File.separator + "result.txt" ;
	
	public static final String SUREFIRE_REPORT= PROJECT_HOME + File.separator + "target" + File.separator + "surefire-reports" + File.separator + "TestSuite.txt" ;
	
	//Config path
	public static final String LOG4J_PATH= PROJECT_HOME + File.separator + "log4j" + File.separator + "log4j.xml";
	
	//repository
	public static final String CONFIG_REPOSITORY= PROJECT_HOME + File.separator + "properties" + File.separator + "config.properties";
	public static final String TESTDATA_REPOSITORY= PROJECT_HOME + File.separator + "properties" + File.separator + "testData.properties";
	public static final String FAILED_SCREENSHOT_PATH= PROJECT_HOME + File.separator + "screenshot" + File.separator + "FailedTestsScreenshots/";
	public static final String EXTENT_CONFIG_PATH= PROJECT_HOME + File.separator + "config" + File.separator;

}