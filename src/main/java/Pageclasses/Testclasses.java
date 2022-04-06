package Pageclasses;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Ecommerce.BaseClass;
import Pageclasses.LandingPage;

public class Testclasses extends BaseClass{

	
	//use to run the testcases
	BaseClass base = new BaseClass();
	LandingPage landingpage;
	CartPage cartpage;
	Placeorder placeorder;
	TopDeals topdeals;
	Properties prop;
	WebDriver driver;
	@Test
	public void teshomepage() {

		ExtentReport.startTest();
		System.out.println("entered in browser");
		prop=base.configProp();
		base.invokeBrowser(/*prop.getProperty("browser")*/ "Chrome");

		landingpage= base.openApplicatio();
		landingpage.verifyLandingPage();
		cartpage = landingpage.searchVeg(prop.getProperty("vegname"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cartpage.cartValidation();
		placeorder =cartpage.verifyCart();
		placeorder.proceedingPage();
		topdeals = landingpage.navigateToTopDeals();
		topdeals.verifyTopDeals();
		topdeals.verifyHeaders();
		base.close();
		ExtentReport.endTest();
	}





}
