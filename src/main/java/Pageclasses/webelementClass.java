package Pageclasses;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class webelementClass {

//		public WebElement ele = driver.findElement(By.xpath("//input[@type='search']"));
		
		//******************landing Page**********************
		public final static By search_veg 					= By.xpath("//input[@type='search']");
		public final static By search_result				= By.xpath("//div[@class='product']");
		public final static By add_qty						= By.xpath("//div/a[@class='increment']");
		public final static By add_to_cart					= By.xpath("//div/button[text()='ADD TO CART']");
		public final static By added_to_cart				= By.xpath("//div/button[@class='added']");
		public final static By top_deals					= By.xpath("//div[@class='cart']/a[text()='Top Deals']");
		public final static By no_result					= By.xpath("//h2[contains(text(),'Sorry, no products matched your search!')]");
		public final static By prod_visiable				= By.xpath("//div[@class='products']");
		
		//************************Cart Page ***********************
				//cartvalidation//
		public final static By cart_search_result			= By.xpath("//div[@class='products']/div[@class='product']");
		public final static By cart_icon					= By.xpath("//a[@class='cart-icon']");
		public final static By product_in_cart				= By.xpath("//ul[@class='cart-items']//p[@class='product-name']");
		public final static By prod_quantity				= By.xpath("//div/input[@class='quantity']");
		public final static By proceed_to_chkout			= By.xpath("//div[@class='action-block']/button[text()='PROCEED TO CHECKOUT']");
		public final static By chkout_cart_details			= By.xpath("//table[@id='productCartTables']/tbody/tr/td[2]");
		public final static By qty_in_cart					= By.xpath("//table[@id='productCartTables']/tbody/tr/td[3]");
		public final static By price						= By.xpath("//table[@id='productCartTables']/tbody/tr/td[4]");
		public final static By total						= By.xpath("//table[@id='productCartTables']/tbody/tr/td[5]");
		
				//verifyCart
		public final static By header						= By.xpath("//div[@class='products']/table/thead/tr/td");
		public final static By promocode_txt_box			= By.xpath("//div/input[@class='promoCode']");
		public final static By promocode_btn				= By.xpath("//div/button[@class='promoBtn']");
		public final static By promocode_info				= By.xpath("//div/span[@class='promoInfo']");
		public final static By place_order					= By.xpath("//span[@class='totAmt']/..//button[text()='Place Order']");
		public final static By checkout_validation			= By.xpath("//div[@class='cart-preview active']/div/button[@class=' ']");
		
		//**********************TopDeals**********************
		public final static By TopDeals_Label				= By.xpath("//div[@class='container']/..//select/../label");
		public final static By select_list					= By.xpath("//div[@class='container']/..//select");
		public final static By select_options				= By.xpath("(//div[@class='container']/..//select/option)[1]");
		public final static By options_List					= By.xpath("//div[@class='container']/..//select/option");
		public final static By options_First				= By.xpath("(//table/thead/tr/th/span[@aria-hidden='true'])[1]");
		public final static By result_table					= By.xpath("//table/thead");
//		public final static By select_list					= By.xpath("//div[@class='container']/..//select");
		public final static By srch_table				= By.xpath("//div[@class='container']//div/input");
		public final static By search_result_table			= By.xpath("//div[@class='container']//div/input/../label");
		
		
		public final static By VegColumn					= By.xpath("//table/tbody/tr/td[1]");
		public final static By result_table_rows			= By.xpath("//table/tbody/tr");
		
		
		public final static By table_header					= By.xpath("//table/thead/tr/th");
		
		
		///********************************Placeorder********************
		public final static By select_country				= By.xpath("//div/label[text()='Choose Country']");
		public final static By select_country_options 		= By.xpath("//div[@class='products']/div//select");
		public final static By checkbox_sel					= By.xpath("//div[@class='products']/div/input[@type = 'checkbox']");
		public final static By submit						= By.xpath("//div[@class='products']/div/button");
		
	}


