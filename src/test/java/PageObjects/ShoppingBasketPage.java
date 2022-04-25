package PageObjects;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import UtilityPackage.BaseClass;
import UtilityPackage.WebDriverManagerSingleton;

public class ShoppingBasketPage {
	public static WebDriver driver = WebDriverManagerSingleton.getInstanceOfWebDriverManager().getDriver();
	public static ArrayList<String> BeforeSortfinalpricelist = new ArrayList<String>();
    public static ArrayList<Double> minmaxpricelist = new ArrayList<Double>();
	public static ArrayList<Double> AfterSortfinalpricelist = new ArrayList<Double>();
	public static ArrayList<Double> FinalUpdatedPricesList = new ArrayList<Double>();
	NumberFormat formatter = new DecimalFormat("#0.00");
	StringBuilder sb = new StringBuilder();
	StringBuilder ShippingPriceValue = new StringBuilder();
	double ExpectedtotalPrice = 0.0;
	String[] shippingfeesvalue;
	String[] additionalchargesvalue;
	Double minprice;
	Double maxprice;
	@FindBy(xpath = "//div[@data-grid-v3='3 2@lg']")
	public static List<WebElement> allproductprices;
	
	@FindBy(xpath = "//span[@class='shipping__cost__value']")
	public static WebElement shippingfees;
	
	@FindBy(xpath = "//div[@data-grid-v3='3 2@lg']")
	public static List<WebElement> UpdatedPrices;
	
	@FindBy(xpath = "//span[@class='v3-text--no-margin v3-text--right']")
	public static WebElement subtotalxpath;
	
	@FindBy(xpath = "//span[@data-zta='overweightValue']")
	public static WebElement serviceadditionalCharges;
	
	@FindBy(xpath = "//span[@class='total__price v3-text--right']")
	public static WebElement ActualTotalPriceXpath;
	
	@FindBy(xpath = "//span[@data-zta='shippingCountryName']")
	public static WebElement deliveryaddresslocation;
	
	@FindBy(xpath = "//div[@class='tippy-content']//div[@class='shipping__country']//button[@type='button']")
	public static WebElement DropDownCountryXpath;
	
	@FindBy(xpath = "//li[@class='dropdown__list__item'][normalize-space()='Portugal']")
	public static WebElement CountrySelectionXpath;
	
	@FindBy(xpath = "//div[@class='tippy-content']//input[@id='zipCode']")
	public static WebElement EnterZipCode;
	
	@FindBy(xpath = "//div[@class='js-shipping-cost shipping__country__label']//*[name()='svg']")
	public static WebElement DownArrowXpath;
	
	@FindBy(xpath = "//div[@class='tippy-content']//button[@type='submit'][normalize-space()='Update']")
	public static WebElement UpdateCountry;

	public ShoppingBasketPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/** @Description Adding 3 Products to Shopping Basket */
	public void addMultipleProductToCart() {
		System.out.println("/**************Adding 3 products********************");
		try {
			BaseClass.scrollDown(); // Initial scrolling to get the list of webelements
			BaseClass.listofElementspresence("//div[@class='lSSlideWrapper usingCss']//child::li//button", 30);
			List<WebElement> overviewpageProdlist = driver
					.findElements(By.xpath("//div[@class='lSSlideWrapper usingCss']//child::li//button"));
			//System.out.println("The Total Size of the Recommended Product list:"+overviewpageProdlist.size());
			// When Grid not loads initially
			if (overviewpageProdlist.size() == 0) {
				driver.navigate().refresh();
				BaseClass.listofElementspresence("//div[@class='lSSlideWrapper usingCss']//child::li//button", 30);
			}
			int count = 0;
			for (int i = 0; i < overviewpageProdlist.size(); i++) // Not Changing the For Loop Size
			{
				BaseClass.scrollDown();
				BaseClass.listofElementspresence("//div[@class='lSSlideWrapper usingCss']//child::li//button", 30);
				
				//Taking list of items every time newly for each Iteration
				List<WebElement> overviewpageNewProdlist = driver
						.findElements(By.xpath("//div[@class='lSSlideWrapper usingCss']//child::li//button"));
				//System.out.println("Inside For Loop " + overviewpageNewProdlist.size() + " i:" + i);
				if (overviewpageNewProdlist.size() > 0) {
					if (overviewpageNewProdlist.get(i).isDisplayed()) {
						BaseClass.clickElement(overviewpageNewProdlist.get(i));
						count++;
						Thread.sleep(5000);
					}
				} else {
					driver.navigate().refresh(); // When Recommeded Grid did not loads
					Thread.sleep(5000);
				}
				if (count == 3) {
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** @Description Get All Prices from Shopping Basket */
	public void getAllProductPrices() {
		for (WebElement beforesortprices : allproductprices) {
			BeforeSortfinalpricelist.add(beforesortprices.getText());
			String[] individualPrices = beforesortprices.getText().split("€");
			AfterSortfinalpricelist.add(Double.parseDouble(individualPrices[1]));
		}
		System.out.println("The Price values Before Sorting are:" + BeforeSortfinalpricelist);
	}

	/** @Description Sorting Prices */
	public void sortPriceValuesInDecendingOrder() {
		Collections.sort(AfterSortfinalpricelist, Collections.reverseOrder());
		for (Double pricewithEuro : AfterSortfinalpricelist) {
			sb.append("€" + pricewithEuro + ", ");
		}
		System.out.println("The Price values After Sorting in Decending order are:" +sb);
	}

	/** @Description FindMinimumAndMaximum prices */
	public void findMinMaxPrices() {
		maxprice = Collections.max(AfterSortfinalpricelist);
		System.out.println("The maximum price is:" + maxprice);
		minprice = Collections.min(AfterSortfinalpricelist);
		System.out.println("The minimum price is:" + minprice);
	}

	/** @Description Increase MinimumPrice Quantity */
	public void increasetheMinPriceQuantity() throws Exception {
		String minPricexpath = "//div[@data-grid-v3='3 2@lg'] [contains(text(),'" + minprice + "')]";
		String addquantityxpath = "//div[@data-grid-v3='3 2@lg'] [contains(text(),'" + minprice
				+ "')]/parent::div/child::div[3]/form/div/child::button[2]";
		String minPriceXpathvalue = driver.findElement(By.xpath(minPricexpath)).getText();
		if (minPriceXpathvalue.contains(String.valueOf(minprice))) {
			BaseClass.clickElement(driver.findElement(By.xpath(addquantityxpath)));
			Thread.sleep(5000);
			System.out.println("Quantity Increased for the Minimum Price Successfully");
		}
	}

	/** @Description Delete Maximum Price product */
	public void DeleteMaximumPriceProduct() throws Exception {
		String maxPricexpath = "//div[@data-grid-v3='3 2@lg'] [contains(text(),'" + maxprice + "')]";
		String DeleteXpath = "//div[@data-grid-v3='3 2@lg'] [contains(text(),'" + maxprice
				+ "')]/parent::div/child::div[3]/form/button";
		String maxPriceXpathvalue = driver.findElement(By.xpath(maxPricexpath)).getText();
		if (maxPriceXpathvalue.contains(String.valueOf(maxprice))) {
			BaseClass.clickElement(driver.findElement(By.xpath(DeleteXpath)));
			Thread.sleep(5000);
			System.out.println("The Maximum Price Product has been  Deleted Successfully");
		}
	}

	/** @Description Calculate SubTotal and Total for Esonia and Portugal */
	public void calculateSubtotalAndTotal() {
		
		// ExpectedSubtotalprice VS ActualSubtotalprice
		FinalUpdatedPricesList = new ArrayList<Double>();// Everytime creates a New ArrayList
		double Expectedsubtotal = 0.0;
		for (WebElement finalprices : UpdatedPrices) {
			String[] individualPrices = finalprices.getText().split("€");
			FinalUpdatedPricesList.add(Double.parseDouble(individualPrices[1]));
		}
		for (int i = 0; i < FinalUpdatedPricesList.size(); i++) {
			Expectedsubtotal = Expectedsubtotal + FinalUpdatedPricesList.get(i);
		}
		Expectedsubtotal = Double.parseDouble(formatter.format(Expectedsubtotal));
		StringBuilder ExpectedSubtotalprice = new StringBuilder();
		ExpectedSubtotalprice.append("€" + Expectedsubtotal);
		String Actualsubtotalprice = subtotalxpath.getText().trim();
		Assert.assertTrue(Actualsubtotalprice.equals(String.valueOf(ExpectedSubtotalprice)), "Both are equal");
		System.out.println("The Subtotal Assertion is success:" + ExpectedSubtotalprice);
		
		// ExpectedTotalPriceValue VS ActualTotalPriceValue
		shippingfeesvalue = shippingfees.getText().split("€");// Replace can also be used- To be review
		if (shippingfeesvalue[1].equals("Free")) {
			shippingfeesvalue[1] = "0.0";
		}
		String additionalcharges = "";
		try {
			if (driver.findElement(By.xpath("//span[@class='js-overweight-modal-link v3-link']")).isDisplayed()) {
				additionalchargesvalue = serviceadditionalCharges.getText().split("€");
				additionalcharges = additionalchargesvalue[1];
				System.out.println("Condition Passed");
			}
		} catch (NoSuchElementException e) {
			additionalcharges = "0.0";
		}
		ExpectedtotalPrice = Double.parseDouble(shippingfeesvalue[1]) + Double.parseDouble(additionalcharges)
				+ Expectedsubtotal;
		ExpectedtotalPrice = Double.parseDouble(formatter.format(ExpectedtotalPrice));
		StringBuilder ExpectedtotalPriceValue = new StringBuilder();
		ExpectedtotalPriceValue.append("€" + ExpectedtotalPrice);
		String ActualTotalPriceValue = ActualTotalPriceXpath.getText().trim();
		Assert.assertTrue(ActualTotalPriceValue.equals(String.valueOf(ExpectedtotalPriceValue)), "Both are equal");
		System.out.println("The TotalPriceValue is Assertion Success:" + ExpectedtotalPriceValue);
	}

	/** @Description Select Country "Portugal" from DropDown */
	public void countryselectionDropdown() throws Exception {
		BaseClass.clickElement(DownArrowXpath);
		BaseClass.clickElement(DropDownCountryXpath);
		BaseClass.clickElement(CountrySelectionXpath);
		EnterZipCode.sendKeys("5000");
		BaseClass.clickElement(UpdateCountry);
		Thread.sleep(5000);
	}

	/** @Description Print Shipping Fees of portugal */
	public void printShippingFeesForPortugal() {
		String countryvalue = deliveryaddresslocation.getText();
		System.out.println("The Selected Country is:" + countryvalue);
		if (deliveryaddresslocation.getText().equalsIgnoreCase("Portugal (5000)")) {
			System.out.println("This Shipping price value is:" + shippingfees.getText());
		}
	}
}
