package Pageclasses;



import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Ecommerce.BaseClass;

public class LandingPage extends BaseClass {

	public WebDriver driver;
	public Properties prop  = BaseClass.configProp();
	public WebDriverWait wait; 
	public String vegquantity;
	public BaseClass base = new BaseClass();
	public commonClass cf = new commonClass();
//	webelementClass wb = new webelementClass();
	public LandingPage(WebDriver driver)
	{
		this.driver= driver;

	}

	public void verifyLandingPage() {
		driver.getTitle();

		Assert.assertEquals(driver.getTitle(),"GreenKart - veg and fruits kart");
		
		try {
			ExtentReport.takeSnapShot(driver,"F:\\eclipse\\Rishabh_Rana\\MobileEcommerce\\Result\\Screenshot\\homepage.png");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("landing page verified");
	}

	public CartPage searchVeg(String vegname)
	{
		cf.exwait(webelementClass.prod_visiable, 10, driver);
		System.out.println("prod visiable");
		driver.findElement(webelementClass.search_veg).sendKeys(vegname);
		ExtentReport.updateTestLog(LogStatus.PASS, "Vegtable name is entered successfully");
		List<WebElement> searchResult  = new ArrayList<WebElement>();
		searchResult = driver.findElements(webelementClass.search_result);
		
		
//			Thread.sleep(5000);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(webelementClass.cart_search_result)));
					
		for (int i=0; i<searchResult.size(); i++) {

			System.out.println("details of searching"+searchResult.get(i).getText());	

			if(searchResult.get(i).getText().contains(prop.getProperty("vegname"))) {
				System.out.println("vegtable found ");
				cf.exwait(webelementClass.add_qty, 10, driver);
				ExtentReport.updateTestLog(LogStatus.PASS, "Vegtable is searched successfully");
				driver.findElement(webelementClass.add_qty).click();
				System.out.println("quantity added");
				ExtentReport.updateTestLog(LogStatus.PASS, "Vegtable quantity increased");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				cf.exwait(webelementClass.add_to_cart, 10, driver);
				driver.findElement(webelementClass.add_to_cart).click();
				ExtentReport.updateTestLog(LogStatus.PASS, "add to cart is displayed");
				
				
				if(driver.findElement(webelementClass.added_to_cart).isDisplayed()) {
					System.out.println("added to cart");
					ExtentReport.updateTestLog(LogStatus.PASS, "Added to cart sucessfully");
					//					CartPage crtpage = new CartPage(vegname);
				}
			}
			else
			{
				
				try {
					System.out.println("searching skipped");
					
					ExtentReport.updateTestLog(LogStatus.FAIL, "searched vegetable is not found");
					ExtentReport.takeSnapShot(driver, System.getProperty("user.dir")+"\\Result\\Screenshot\\ss1.png");
					
					ExtentReport.endTest();
					cf.exwait(webelementClass.no_result, 10, driver);
					//Thread.sleep(5000);
//					driver.close();
////					base.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				break;
			}
		}

	
		return PageFactory.initElements(driver, CartPage.class);


	}



	public TopDeals navigateToTopDeals() {
		
		cf.exwait(webelementClass.top_deals, 5, driver);
		if(driver.findElement(webelementClass.top_deals).isDisplayed() && 
				driver.findElement(webelementClass.top_deals).isEnabled() ) {
			
			driver.findElement(webelementClass.top_deals).click();
			ExtentReport.updateTestLog(LogStatus.PASS, "Navigated to Top Deals page");
			
		}
		else
		{
			System.out.println("Top Deals is not enabled or displayed");
			
			ExtentReport.updateTestLog(LogStatus.FAIL, "Navigation to Top Deals Page");
		}
		
		return PageFactory.initElements(driver, TopDeals.class);
	}
}
