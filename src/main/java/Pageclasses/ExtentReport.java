package Pageclasses;

import java.io.File;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.reporters.Files;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.HTMLReporter;
import com.relevantcodes.extentreports.LogStatus;

import Ecommerce.BaseClass;

public class ExtentReport {

	static ExtentReports reports;
	HTMLReporter htmlreports;
	static ExtentTest test;
	static WebDriver driver;
	public static BaseClass base = new BaseClass();

	public static void startTest()
	{
		
	
		String updatedTime = commonClass.getdatetimeStamp();
		System.out.println("timeStamp="+updatedTime);
		reports = new ExtentReports(System.getProperty("user.dir")+"//Result//ExtentReportResults"+updatedTime+".html");
		test = reports.startTest("ExtentDemo");

		reports.addSystemInfo("Environment", "Test");
		reports.addSystemInfo("Build", "first");
		reports.addSystemInfo("Browser", "Chrome");

		reports.config().documentTitle("GreenKart Automation Report");	
	}

	//	public static void logScreenshot(WebDriver screenDriver, String testCaseName, String res, Exception e) throws IOException{
	//		try{ 
	//			//take the screen print of the screenDriver 
	//			File file=((TakesScreenshot)screenDriver).getScreenshotAs(OutputType.FILE); 
	//			File dir = new File("Report/screenshot/"+testCaseName); 
	//			//make the directory with the name mentioned above  
	//			dir.mkdirs(); 
	//			String fileName="Report/screenshot/"+testCaseName+"/"+testCaseName+".jpg";
	//			//copy the screen print into the path mentioned above
	//			FileUtils.copyFile(file, new File(fileName)); 
	//			//creates a new test step 
	//			ExtentTest logger= this.childTest; 
	//			//logs the test step status as fail with the exception as the description
	//			logger.fail(e); 
	////			//logs the screen print taken 
	//			logger.info("Attachedscreenshot").addScreenCaptureFromPath("screenshot/"+testCaseName+"/"+testCaseName+".jpg");
	//			catch(Exception ex){ 
	//				throw new Exception("Exceptionwhiletakingscreenshot: "+ex);
	//				public void logSkipTest(WebDriver screenDriver1,String testCaseName1,String res1)throws IOException{ 
	//					Filefile=((TakesScreenshot)screenDriver).getScreenshotAs(OutputType.FILE); 
	//					File dir =new File(“Report/screenshot/”+testCaseName); 
	//					dir.mkdirs(); 
	//					StringfileName=“Report/screenshot/”+testCaseName+“/”+testCaseName+“.jpg”;
	//					FileUtils.copyFile(file, new File(fileName));
	//					
	//				}
	//			}
	//		}
	//	}
	

	public static void updateTestLog(LogStatus status, String Message) {
		test.log(status , Message);
		
		if(status.equals("FAIL")) {
			test.log(status, "Failed");
			
		}
		
	}

	public static void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File DestFile=new File(fileWithPath);

		//Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);
		
//		test.addScreenCapture(fileWithPath);

	}
	@AfterTest
	public static void endTest() {
		reports.endTest(test);
		reports.flush();
//		base.close();
		
		//	reports.close();
	}
}