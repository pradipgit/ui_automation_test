	package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.sun.jersey.api.client.ClientResponse;

import setup.AbstractTest;

public class utility extends AbstractTest{
	
	
	public static WebDriverWait wait;

	public static int counter = 30;
	public static String message;
	public static int n=0;
	public static int m=0;

	 public static void waitUntilElementIsClickable(WebElement ele) {
		try {	
			boolean flag = false;
			String message = null;
			waitUntilElementIsVisible(ele);
			for (int i = 0; i < counter; i++) {
				try {
//					implicitlyWait(0);
					if (ele.isEnabled()) {
						flag = true;
						break;
					} else {
						// log.info("waiting for element to be clickable " + i + "seconds");
						System.out.println("waiting for element to be clickable " + i + "seconds");
						waitFor(1);
					}
				} catch (Exception e) {
					// log.info("Waiting for element to be clickable " + i + " seconds");
					System.out.println("waiting for element to be clickable " + i + "seconds");
					waitFor(1);
					message = e.getMessage();
				}
			}
			if (!flag) {
				// log.error(element + " is not clickable till " + counter + " seconds due to " + message);
				
				Assert.fail(ele + " is not clickable till " + counter + " seconds due to " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			implicitlyWait(60);
		}
	}

	final public static void waitUntilElementIsVisible(WebElement ele) {
		try {
			boolean flag = false;
			String message = null;
			for (int i = 0; i < counter; i++) {
				try {
//					implicitlyWait(0);
					if (ele.isDisplayed()) {
						flag = true;
						break;
					} else {
						// log.info("waiting for element to be visible!!! " + i + "seconds");
						System.out.println("waiting for element to be clickable " + i + "seconds");
						waitFor(1);
					}
				} catch (NoSuchElementException | StaleElementReferenceException e) {
					// log.info("Waiting for element to be visible !!! " + i + " seconds :: " +element);
					
					System.out.println("waiting for element to be visible " + i + "seconds");
					waitFor(1);
				} catch (Exception e) {
					// log.info("Waiting for element to be visible!!! " + i + " seconds");
					System.out.println("waiting for element to be visible " + i + "seconds");
					waitFor(1);
					message = e.getMessage();
				}
			}
			if (!flag) {
				// log.error(element + " is not clickable till " + counter + " seconds due to "
				// + message);
				Assert.fail(ele + " is not clickable till " + counter + " seconds due to " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			implicitlyWait(60);
		}
	}
	
 
	
	public static void waitUntilElementNotVisible(WebElement ele) 
	{
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			boolean flag = false;
			String message = null;
			for (int i = 0; i < counter; i++) {
				try {
					if (ele.isDisplayed()) {
						System.out.println("waiting for element to be Invisible " + i + "seconds");
						waitFor(1);
					} else {
						flag = true;
						break;
					}
				} catch (NoSuchElementException | StaleElementReferenceException e) {
					flag = true;
					break;
				} catch (Exception e) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				Assert.fail(ele + " is not Invisible till " + counter + " seconds due to " + message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
	
	
	 public static void waitFor(float f) {
		try {
			Thread.sleep((long) f * 1000);
		} catch (InterruptedException e) {
		}
	}
	 
	 public static void takeScreenShot(String methodName) {
		 log.info("Entered URL");
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name 
           try {
				FileUtils.copyFile(scrFile, new File(filePath+methodName+".png"));
//				System.out.println("***Placed screen shot in "+filePath+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
   }
	 

	 public static String readText(String filename) throws IOException
	 {
		 	InputStream is = new FileInputStream(filename);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			        
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			        
			while(line != null){
			   sb.append(line).append("\n");
			   line = buf.readLine();
			}
	        
			String fileAsString = sb.toString();
			
//			System.out.println(fileAsString);
			
			return fileAsString;
	 }
	 
	 public static void postResultInSlack() throws JsonParseException, IOException {
		 	 
		 	//sending the result.txt result to Slack		 
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("Content-type", "application/json");
			
			String url =  getConfigData("slackurl");
			String slack_file_path = Constants.Slack_Channel_File;

			String body = "{\"channel\": \"channelName\", " + "\"username\": \"CoreBot\", " + "\"text\": \"value\", "
					+ "\"icon_emoji\": \":mega:\"}";
			
			//post the Testcase Results into slack
			String fileAsString =readText(Constants.RESULT_TXT);
		
			String channel = getValueFromJSON("channel", slack_file_path);
			log.info("Selected channel: "+channel);
			body = body.replace("channelName", channel);
			body = body.replace("value", fileAsString);
//			System.out.println(body);
			ClientResponse response = RestRequest.doPost(url, map, body);
			response.getEntity(String.class);
				
		}
	 
	 
	 public static String getValueFromJSON(String input, String jsonFilePath) throws JsonParseException, IOException {

			String a[] = input.split("#");

			InputStream is = new FileInputStream(jsonFilePath);

			JsonFactory factory = new JsonFactory();
			JsonParser parser = factory.createParser(is);

			int l = a.length;
			String name = null, value = null;
			int i = 0;

			parser.nextToken();

			while (i < l) {

				parser.nextToken();
				value = parser.getValueAsString();
				name = parser.getCurrentName();

				if (name.equals(a[i])) {

					parser.nextToken();
					value = parser.getValueAsString();
					name = parser.getCurrentName();
					i++;
				}

			}
			log.info(name + " " + value);
			return value;

		}
	 
//	 
//	 public static void zipFile() throws IOException {
//	        String sourceFile = Constants.EXTENT_HTML;
//	        FileOutputStream fos = new FileOutputStream("report.tar.gz");
//	        ZipOutputStream zipOut = new ZipOutputStream(fos);
//	        File fileToZip = new File(sourceFile);
//	        FileInputStream fis = new FileInputStream(fileToZip);
//	        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
//	        zipOut.putNextEntry(zipEntry);
//	        byte[] bytes = new byte[1024];
//	        int length;
//	        while((length = fis.read(bytes)) >= 0) {
//	            zipOut.write(bytes, 0, length);
//	        }
//	        zipOut.close();
//	        fis.close();
//	        fos.close();
//	    }
//	 
	 
	 public static void appendText(String message)
	 {
		 try
		 { 
		     String filePath= Constants.RESULT_TXT;
		     FileWriter fw = new FileWriter(filePath,true); //the true will append the new data
		     fw.write(message);//appends the string to the file
		     fw.close();
		 }
		 catch(Exception ioe)
		 {
		     System.err.println("IOException: " + ioe.getMessage());
		 }
	 }


	 public static void createFilewithEnvInfo()
	 {
		 try
		 { 
			 File myObj = new File("result.txt");
			 
			 if (!myObj.exists()) {
				 myObj.createNewFile();
				}
			 else {
		    	  myObj.delete();
		    	  myObj.createNewFile();
		      }
			 
		 }
		 catch(Exception ioe)
		 {
		     System.err.println("IOException: " + ioe.getMessage());
		 }
	 }
	 
	 public static void addEnvironmentInfo()
	 {	
		 if(n==0)
			{
			message = "\n" ;
			utility.appendText(message);
	
			message = "CORE-B UI Automation  :" ;
		 	utility.appendText(message);	
		 	
		 	message = "\n" ;
			utility.appendText(message);
				
			message = "The Environment Url  :" + getConfigData("login_url") + "\n" ;
		 	utility.appendText(message);
		 	
		 	message = "The User Name :" + getConfigData("username") + "\n" ;
		 	utility.appendText(message);
		 	
		 	message = "\n" ;
			utility.appendText(message);
		 				 	
			++n;
			
			}
			else
			{
				
			}
		 	
	 } 
	 
	 public static int generateRandomNumber()
	 {
		 	Random rand = new Random(); 
		 	int rand_int1 = rand.nextInt(1000);
		 	return rand_int1;
	 }
	 
}


