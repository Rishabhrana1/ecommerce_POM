package Pageclasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ecommerce.BaseClass;

public class TopDeals {

	WebDriver driver;
	BaseClass base = new BaseClass();
	Properties prop = base.configProp();
	
	
	public TopDeals(WebDriver driver) {
		this.driver = driver;
	}
	

	

	public void verifyTopDeals()
	{

		try {
			
//			List<String> veglisname = new ArrayList<String>();
//			List<String> vegListname = new ArrayList<String>();
			Actions act = new Actions(driver);
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String parentWindow =  driver.getWindowHandle();
		Set<String> windoHandles = new HashSet<String>();
		windoHandles= driver.getWindowHandles();

		Iterator<String> itr= windoHandles.iterator();

		while(itr.hasNext()) {
			String  childWindow = itr.next();

			if(!childWindow.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
		if(driver.findElement(webelementClass.TopDeals_Label).getText().contains("Page size:")) {

			Select s1 = new Select(driver.findElement(webelementClass.select_list));

			System.out.println(driver.findElement(webelementClass.select_options).getText());
			driver.findElement(webelementClass.select_list).click();
			List<WebElement> options = driver.findElements(webelementClass.options_List);
			System.out.println("options got");
			driver.findElement(webelementClass.options_First).click();
			for(int i=1; i<options.size(); i++) {

				System.out.println("inside for loop");
				options.get(i).getText();
				options.get(i).click();
				WebDriverWait wb = new WebDriverWait(driver, 10);
				wb.until(ExpectedConditions.visibilityOfElementLocated(webelementClass.result_table));
				int rows = driver.findElements(webelementClass.result_table_rows).size();
				System.out.println(rows);
				if(rows == Integer.parseInt(options.get(i).getText()) ){
					System.out.println("data displayed as per selected from drop dorpdown" );
					
					try {
						
						ExtentReport.takeSnapShot(driver,"F:\\eclipse\\Rishabh_Rana\\MobileEcommerce\\Result\\Screenshot\\"+options.get(i).getText()+".png");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					System.out.println("error in displayed number of selected records");
					System.out.println(options.get(i).getText());
				}

				List<WebElement> vegList = new ArrayList<WebElement>();
				vegList = driver.findElements(webelementClass.VegColumn);
				
				List<String> vegNameList = new ArrayList<String>();
				for(WebElement ele: vegList) {
					vegNameList.add(ele.getText());
				}
				Collections.sort(vegNameList);

				List<WebElement> sortvegList = new ArrayList<WebElement>();
				List<String> sortvegNameList = new ArrayList<String>();
				vegList = driver.findElements(webelementClass.VegColumn);

				for(WebElement ele: sortvegList) {
					sortvegNameList.add(ele.getText());
				}

				if(vegNameList.equals(vegNameList)) {
					System.out.println("sorting fuctionality for veg name is workign as expected");
				}
				else {
					System.out.println("sorting is not working as expected");
				}

			}

		}
	}

	public void searchvegTopDeal() {

		if(driver.findElement(webelementClass.search_result_table).isDisplayed()){

			driver.findElement(webelementClass.srch_table).sendKeys(prop.getProperty("vegname"));

			int rows = driver.findElements(webelementClass.result_table_rows).size();

			List<WebElement> vegList = new ArrayList<WebElement>();
			vegList = driver.findElements(webelementClass.VegColumn);

			if(rows == vegList.size()) {

				for(WebElement veg:vegList) {

					if(veg.getText().equalsIgnoreCase(prop.getProperty("vegname")))
					{
						System.out.println("search result displayed");
					}
					else {
						System.out.println("search result not displayed");
					}

				}
			}
			else {
				System.out.println("result rows doesn't match");
			}

		}
	}
	
	public void verifyHeaders() {
		
		System.out.println("headers method called"); 
		
		List<WebElement> ele = driver.findElements(webelementClass.table_header);
		List<String>  vegnameCol = new ArrayList<String>();
		int i=0;
		for(WebElement elmnt :ele) {
			vegnameCol.add(elmnt.getText());
			if(vegnameCol.get(i).equalsIgnoreCase("Veg/fruit name") || vegnameCol.get(i).equalsIgnoreCase("Price") || 
					vegnameCol.get(i).equalsIgnoreCase("Discount price") ) {
				
//				act.doubleClick(elmnt).perform();
				elmnt.click();
				
				//take screenshot after every click
				
				try {
					
					ExtentReport.takeSnapShot(driver,"F:\\eclipse\\Rishabh_Rana\\MobileEcommerce\\Result\\Screenshot\\headers.png");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.out.println("headers are not as expected");
			}
			
			i++;
		}
		
		driver.switchTo().defaultContent();
		
//		driver.quit();
	}
}


