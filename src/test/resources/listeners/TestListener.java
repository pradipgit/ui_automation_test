package listeners;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.fasterxml.jackson.core.JsonParseException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utils.Constants;
import utils.utility;

public class TestListener implements ITestListener,ISuiteListener,IReporter{
	private Logger log=Logger.getLogger(this.getClass().getSimpleName());
	static ExtentReports extent= new ExtentReports(System.getProperty("user.dir")+File.separator+"target"+File.separator+"ExtentReport.html");
	public static ExtentTest logger;
	private String suiteName;
	public String fileName;
	public String filePath=Constants.EXTENT_CONFIG_PATH;
	public String methodName;
	public String message;
	public int n=0;
	public int m=0;
	public int pass,fail,skip;
	int count =0;
	 

	@Override
	public void onStart(ITestContext context) {
		log.info("****************************************************************************************");
		log.info("                                " + context.getName() + "       ");
		log.info("----------------------------------------------------------------------------------------");
//		System.out.println(filePath);
		extent.loadConfig(new File(filePath+"extent-config.xml"));
		suiteName="<<--- " +context.getName()+ " --->>";

	}

	@Override
	public void onTestStart(ITestResult result) {

		log.info("\n\n" + "<< --- TestCase START --->> " + result.getName() + "\n");
		logger = extent.startTest(suiteName + " # " + result.getName().toString());
		logger.log(LogStatus.INFO,"<b><i>Description of the test :: </b></i> \"" + result.getMethod().getDescription() + "\"");
		fileName = result.getName();
		
		utility.addEnvironmentInfo();
		
		 if(count==0)
			{
				message = "\n";
				utility.appendText(message);
				
				message = " " + suiteName + "\n";		
				utility.appendText(message);
				 
				message = "\n";
				utility.appendText(message);
			
			++count;
			
			log.info("Successfully posted the logs into result.txt file");
			
			}
			else
			{
				log.info("Already posted the logs into result.txt file");
			}
		
		 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.info("\n\n TestCase: " + result.getName() + ": --->>> PASS \n");
		logger.log(LogStatus.PASS, result.getName().toString() + " test has passed");
		message = " " + result.getName().toString() + ": PASS \n";		
		utility.appendText(message);	
		pass=pass+1;
		extent.endTest(logger);		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger = extent.startTest(suiteName + " # " + result.getName().toString());
		logger.log(LogStatus.INFO,
				"<b><i>Description of the test :: </b></i>\"" + result.getMethod().getDescription() + "\"");
		logger.log(LogStatus.SKIP, result.getName().toString() + " test has Skipped");
		message = " "  +result.getName().toString() + ": SKIPPED \n";		
		utility.appendText(message);
		skip=skip+1;
		extent.endTest(logger);		
//		log.info("\n\n TestCase: " + result.getName() + ": --->>> SKIPPED");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		log.info("***** Error " + result.getName() + " test has failed *****");
		logger.log(LogStatus.FAIL, result.getName().toString() + " test has failed");
		message = " "  +result.getName().toString() + ": FAIL \n";		
		utility.appendText(message);
		methodName=result.getMethod().getDescription();
		utility.takeScreenShot(methodName);
		fail=fail+1;
		extent.endTest(logger);
		
	}	

	@Override
	public void onFinish(ITestContext context) {
		log.info("----------------------------------------------------------------------------------------\n");
		log.info("****************************************************************************************\n");
		--count;
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//		log.info("\n\n TestCase: " + result.getName() + ": --->>> FAILED With percentage");
	}

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		if(n==0)
		{
		utility.createFilewithEnvInfo();
		++n;
		
		}
		else
		{
//			System.out.println("Successfully posted the logs into result.txt file");
		}
	}

	@Override
	public void onFinish(ISuite suite) {
	}
	
	 
	 
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		int total=0; 	
		message = "\n" ;
		utility.appendText(message);
	
	 	message = "Test Execution Summary : \n" ;
		utility.appendText(message);
		
		message = "\n" ;
		utility.appendText(message);
		
		total = pass + fail + skip;
//		System.out.println("Total Tests :" + total);
		message = "Total Tests : "+ total ;
		utility.appendText(message);
		
		message = "\n" ;
		utility.appendText(message);

//		System.out.println("Passed :" + pass);
		
		message = "Passed :" + pass + "," ;
	 	utility.appendText(message);
	 	
//		System.out.println("Fail :" + fail);
		
		message = "Failed :" + fail + "," ;
	 	utility.appendText(message);
//	 
//		System.out.println("Skipped :" + skip);
		
		message = "Skipped :" + skip + "\n" ;
	 	utility.appendText(message);
	 	message = "\n" ;
		utility.appendText(message);
		
		
		 for (ISuite iSuite : suites) {

	         //Get a map of result of a single suite at a time

	            Map<String,ISuiteResult> results =    iSuite.getResults();

	         //Get the key of the result map

	            Set<String> keys = results.keySet();

	        //Go to each map value one by one

	            for (String key : keys) {

	             //The Context object of current result

	            ITestContext context = results.get(key).getTestContext();
	             
	            message = "Suite Name-> "+context.getName()+" ,Start Date Time for execution-> "+context.getStartDate()+" ,End Date Time for execution-> "+context.getEndDate()+" ";
	            utility.appendText(message);
	    	 	message = "\n" ;
	    		utility.appendText(message);
	              
	        }

	        }
		 
		 try {
			 utility.postResultInSlack();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
