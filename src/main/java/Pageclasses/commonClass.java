package Pageclasses;

import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Ecommerce.BaseClass;

public class commonClass extends BaseClass {


	public static String getdatetimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timeStamp = timestamp.toString();
		String newStamp = timeStamp.replace(" ", "_");
		String updatedDate =newStamp.replace("-", "_");
		String updatedTime	= updatedDate.replace(":", "_");
		//		String updatetimeStamp[] = updatedTime.split(".");
		return updatedTime;
	}

	public void exwait(By ele_xpath, int timeinseconds, WebDriver driver) {

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(ele_xpath));
	}

	public void click(By ele_xpath, WebDriver driver) {

		driver.findElement(ele_xpath).click();
	}
}
