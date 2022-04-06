package Ecommerce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.reporters.Files;

import com.relevantcodes.extentreports.LogStatus;

import Pageclasses.ExtentReport;
import Pageclasses.LandingPage;

public class BaseClass {

	public WebDriver driver;
	public static Properties prop = new Properties();


	/****************** configure properties 
	 * @return ***********************/

	public static Properties configProp() {

		try {
			File file = new File(System.getProperty("user.dir")+ "\\src\\main\\java\\Utilites\\config.properties");

			
			FileInputStream fileInput = null;
			try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			//load properties file
			try {
				prop.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}


		}

		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());

		}

		return prop;

	}

	/****************** invoke browser***********************/
	public void invokeBrowser(String browserName)
	{
		try {
			
			if (browserName.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/driver/chromedriver.exe");
				driver = new ChromeDriver();

				System.out.println("entered in browser");
			} else if (browserName.equalsIgnoreCase("Mozila")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("Opera")) {
				System.setProperty("webdriver.opera.driver",
						System.getProperty("user.dir") + "/drivers/operadriver");
				driver = new OperaDriver();
			} else if (browserName.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
				driver = new OperaDriver();
			} else {
				driver = new SafariDriver();
			}
			
			ExtentReport.updateTestLog(LogStatus.PASS, browserName+"is invoked successfully");
		} catch (Exception e) {
			//			reportFail(e.getMessage());
		}

			System.out.println("chrome opened successfully");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	/***********************open Application****************************/

	public LandingPage openApplicatio()
	{
		driver.get(prop.getProperty("URL"));
		
		return PageFactory.initElements(driver, LandingPage.class);

	}



	public void close() {
		
		System.out.println("close method called");
		driver.quit();
	}

	/******************take Screenshot************************/
	public  void takeSnapShot(WebDriver driver, String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot

		TakesScreenshot scrShot =((TakesScreenshot)driver);

		//Call getScreenshotAs method to create image file

		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		//Move image file to new destination

		File DestFile=new File(fileWithPath);

		//Copy file at destination

		FileUtils.copyFile(SrcFile, DestFile);

	}


}
