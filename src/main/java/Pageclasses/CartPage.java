package Pageclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import Ecommerce.BaseClass;

public class CartPage {

	public String vegquantity;
	public WebDriver driver;
//	public String vegname = "Tomato";
	BaseClass base = new BaseClass();
	Properties prop = base.configProp();
	commonClass cf = new commonClass();
	public WebDriverWait wait;

	public CartPage(WebDriver driver) {
		this.driver = driver;

	}

	public void cartValidation() {
		//		verifying cart
		
		wait = new WebDriverWait(driver, 30);
		cf.exwait(webelementClass.cart_icon, 10, driver);
		driver.findElement(webelementClass.cart_icon).click();
		cf.exwait(webelementClass.product_in_cart, 10, driver);
		if(driver.findElement(webelementClass.product_in_cart).getText().contains(prop.getProperty("vegname"))) {
			System.out.println("visibale in Cart");
			
			ExtentReport.updateTestLog(LogStatus.PASS, "Cart List validation");
			try {
//				base.takeSnapShot(driver,"C:\\Users\\USER\\eclipse-workspace\\MobileEcommerce\\Result\\cart.png");
				ExtentReport.takeSnapShot(driver,"F:\\eclipse\\Rishabh_Rana\\MobileEcommerce\\Result\\Screenshot\\cart.png");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vegquantity = driver.findElement(webelementClass.prod_quantity).getAttribute("value");

			System.out.println("veg quantity"+vegquantity);
			System.out.println("clicked");
			
			cf.exwait(webelementClass.proceed_to_chkout, 10, driver);
			cf.click(webelementClass.proceed_to_chkout, driver);
			//driver.findElement(webelementClass.proceed_to_chkout).click();
			ExtentReport.updateTestLog(LogStatus.PASS, "process to checkout");

			
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(webelementClass.checkout_validation)));
		}
			
		else {
		
			ExtentReport.updateTestLog(LogStatus.FAIL, "item not added to Cart");
		}
		
			if(driver.findElement(webelementClass.chkout_cart_details).getText().contains(prop.getProperty("vegname"))) {
				System.out.println("navigated to checkout page");

//				Assert.assertTrue(driver.findElement(By.xpath("//table[@id='productCartTables']/tbody/tr/td[3]")).getText().equalsIgnoreCase(vegquantity));


				String quantity = driver.findElement(webelementClass.qty_in_cart).getText();
				String price = driver.findElement(webelementClass.price).getText();
				String total = driver.findElement(webelementClass.total).getText();

				int Quant = Integer.parseInt(quantity);
				int Price = Integer.parseInt(price);
				int Total = Integer.parseInt(total);

				ExtentReport.updateTestLog(LogStatus.PASS, "checkout page with expected details");
				if(Total==(Quant*Price)) {
					//					Assert.assertEquals(total, (Quant*Price));
					ExtentReport.updateTestLog(LogStatus.PASS, "price matched");
					System.out.println("price matched");
				}
				else {
					ExtentReport.updateTestLog(LogStatus.FAIL, "Price doesn't matched");
				}

			}

		}

	public Placeorder verifyCart() {

		//		verify header of cart table

		List<WebElement> header  = new ArrayList<WebElement>();

		header = driver.findElements(webelementClass.header);

		for(WebElement ele:header) {

			if(ele.getText().equalsIgnoreCase("#") || ele.getText().equalsIgnoreCase("Product Name") || ele.getText().equalsIgnoreCase("Quantiry") ||
					ele.getText().equalsIgnoreCase("Price") || ele.getText().equalsIgnoreCase("Total")) {

				System.out.println("headers as per expections");
				ExtentReport.updateTestLog(LogStatus.PASS, "Cart table headers validation");
			}
			else {
				System.out.println("headers are not expected");
				ExtentReport.updateTestLog(LogStatus.FAIL, "Cart table headers validation");
			}
		}

		if(driver.findElement(webelementClass.promocode_txt_box).isEnabled()) {

			driver.findElement(webelementClass.promocode_txt_box).sendKeys("");
			driver.findElement(webelementClass.promocode_btn).click();
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(driver.findElement(webelementClass.promocode_info).getText().equalsIgnoreCase("Empty code ..!")) {
				System.out.println("expected output");
			}
			else {
				System.out.println("message not as expected");
			}

			driver.findElement(webelementClass.promocode_txt_box).sendKeys(prop.getProperty("promocode"));
			driver.findElement(webelementClass.promocode_btn).click();

			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(driver.findElement(webelementClass.promocode_info).getText().equalsIgnoreCase("Invalid code ..!")) {
				System.out.println("expected output");
			}
			else {
				System.out.println("message not as expected");
			}

			driver.findElement(webelementClass.promocode_txt_box).clear();

			driver.findElement(webelementClass.place_order).click();

			try {
//				base.takeSnapShot();
				ExtentReport.takeSnapShot(driver,"F:\\eclipse\\Rishabh_Rana\\MobileEcommerce\\Result\\Screenshot\\CartPage.png");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return PageFactory.initElements(driver, Placeorder.class);

	}


}
