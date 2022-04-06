package Pageclasses;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Ecommerce.BaseClass;

public class Placeorder {

	WebDriver driver;
	BaseClass base = new BaseClass();
	Properties prop = base.configProp();
	public Placeorder(WebDriver driver) {
		this.driver = driver;

	}

	public void proceedingPage() {

		if(driver.findElement(webelementClass.select_country).isDisplayed()) {

			Select s1 = new Select(driver.findElement(webelementClass.select_country_options));
			s1.selectByVisibleText(prop.getProperty("country"));
			driver.findElement(webelementClass.checkbox_sel).click();

			driver.findElement(webelementClass.submit).click();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
