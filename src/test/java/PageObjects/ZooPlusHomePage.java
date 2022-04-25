package PageObjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import UtilityPackage.BaseClass;
import UtilityPackage.WebDriverManagerSingleton;

public class ZooPlusHomePage {
	public static WebDriver driver = WebDriverManagerSingleton.getInstanceOfWebDriverManager().getDriver();
	
	@FindBy(xpath ="//button[text()= 'Agree and continue']")
	public static WebElement consentlayerwindow;
	
	@FindBy(xpath ="//div[@class='lSSlideWrapper usingCss']//child::li//button")
	public static List<WebElement> Prodlist;
	
	public ZooPlusHomePage( WebDriver driver)
	{
	   this.driver = driver;
	   PageFactory.initElements(driver, this);
	}
	// Action Methods
	//BaseClass Contains Click method to perform on webelement
	public void closeconsentlayerwindow() throws Exception {
		BaseClass.elementClick(consentlayerwindow); 
	}
	public void addSingleProductToCart() throws Exception
	{
		BaseClass.scrollDown();
		for(int i =0;i<Prodlist.size();i++)
		{ 
			 if(Prodlist.get(i).isDisplayed())
			 {
				 BaseClass.clickElement(Prodlist.get(i));
				 break;
			 }
		} 
}
}
