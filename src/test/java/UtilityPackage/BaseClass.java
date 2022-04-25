package UtilityPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	public static WebDriver driver = WebDriverManagerSingleton.getInstanceOfWebDriverManager().getDriver();
	public static JavascriptExecutor js;
	public static void closeDriver() {
		driver.close();
	}
	public static void navigateToDriver(String url) {
		driver.get(url);
	}
	public static void maximizeBrowser() {
		driver.manage().window().maximize();
	}
	public static void waitforElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public static void elementTobeClickable(WebElement element, int WaitSeconds) throws Exception {
		try {
			WebDriverWait waits = new WebDriverWait(driver, WaitSeconds);
			waits.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
		}
	}
	public static void listofElementspresence(String xpath, int WaitSeconds) throws Exception {
		try {
			WebDriverWait waits = new WebDriverWait(driver, WaitSeconds);
			waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
		}
	}
	public static void elementClick(WebElement element)
	{
		try {
			elementTobeClickable(element, 5);
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			element.click();
		}
	}
	public static void clickElement(WebElement element) throws Exception{
		elementTobeClickable(element, 60);
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			element.click();
		}
	}
	public static void scrollDown() throws Exception {
		try {
			js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,500)");
		} catch (Exception e) {
			js.executeScript("window.scrollBy(0,600)");
		}
	}
}
