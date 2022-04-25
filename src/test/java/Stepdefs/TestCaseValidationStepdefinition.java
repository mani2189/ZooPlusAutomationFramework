package Stepdefs;


import org.testng.Assert;

import PageObjects.ShoppingBasketPage;
import PageObjects.ZooPlusHomePage;
import UtilityPackage.BaseClass;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCaseValidationStepdefinition extends BaseClass {

	ZooPlusHomePage homepage = new ZooPlusHomePage(driver);
	ShoppingBasketPage shoppingbasketpage = new ShoppingBasketPage(driver);
	
	@Before
    public void beforeScenario(Scenario scenario){
        System.out.println("Scenario name is "+scenario.getName());
    } 
	
	@Given("User opens the URL {string}")
	public void user_opens_the_url(String url) throws InterruptedException {
		System.out.println("/**************Scenario1********************");
		BaseClass.maximizeBrowser();
		driver.get("https://www.zooplus.com/checkout/cartEmpty.htm");
		
	}

	@When("User Click on Agree and Continue")
	public void user_click_on_agree_and_continue() {
		try {
			homepage.closeconsentlayerwindow();// Please click this method and see the code logic
		} catch (Exception e) {
		}
	}

	/** @Description TC-1- Validation */
	@Then("User adds one product to cart and validates the URL")
	public void user_adds_one_product_to_cart_and_validates_the_url() {
		try {
			homepage.addSingleProductToCart();// Please click this method and see the code logic
			System.out.println("Single Product added");
			Thread.sleep(3000);
			String ActualURL = driver.getCurrentUrl();
			System.out.println("The Actual URL:" + ActualURL);
			Assert.assertTrue(ActualURL.contains("overview"), "The Navigated URl contains overview");
			System.out.println("The Navigated URL Contains the Overview and Assersion Passed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Scenario2

	@Given("User add multiple products to cart")
	public void user_add_multiple_products_to_cart() {
		System.out.println("/**************Scenario2********************");
		try {
			shoppingbasketpage.addMultipleProductToCart();// Please click this method and see the code logic
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** @Description TC-2- Validation - Printing Price values Before Sorting */
	@When("User gets the List of all prices and printing the price values before sorting")
	public void user_gets_the_list_of_all_prices_and_printing_the_price_values_before_sorting() {
		try {
			shoppingbasketpage.getAllProductPrices(); // Click this method to see the logic of fetching the prices //														// values
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** @Description TC-2- Validation - Printing Price values After Sorting */
	@When("User sorting the price values in the Decending order and printing after sorting")
	public void user_sorting_the_price_values_in_the_decending_order_and_printing_after_sorting() {
		shoppingbasketpage.sortPriceValuesInDecendingOrder();// Click this Method to see the logic of Sorting in //																// Decending order
	}

	//Scenario3
	/** @Description TC-3- Validation - Finding Maximum ,Minimum Price value */
	@Given("User finds the minumum and maximum value of the product in the cart")
	public void user_finds_the_minumum_and_maximum_value_of_the_product_in_the_cart() {
		System.out.println("/**************Scenario3********************");
		shoppingbasketpage.findMinMaxPrices();// Click this method to see code login to find minmax price values
	}

	@When("User increases one more quanity for the lowest price product")
	public void user_increases_one_more_quanity_for_the_lowest_price_product() {
		try {
			shoppingbasketpage.increasetheMinPriceQuantity();// Click this method to see code Logic Contain Increasing																// // quantity
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/** @Description TC-3: Validation - Deleting the MaximumPrice */
	@Then("User deletes the Maximum price value product")
	public void user_deletes_the_maximum_price_value_product() {
		try {
			shoppingbasketpage.DeleteMaximumPriceProduct(); // Click this method to see code Logic of Deletion max price															// // product
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/** @Description TC-3 & TC 4 - Validation -Calculate Subtotal and Total price -Printing them*/	              	 
	@Then("User Verifies the Sum of SubTotal and Total value of product")
	public void user_verifies_the_sum_of_sub_total_and_total_value_of_product() {
		shoppingbasketpage.calculateSubtotalAndTotal();// Click this method to see code Logic of Total and Subtotal //														// value assertion
	}

	//Scenario4
	@Given("User changes the shipping country to Portugal with postalcode")
	public void user_changes_the_shipping_country_to_portugal_with_postalcode() {
		System.out.println("/**************Scenario4********************");
		try {
			shoppingbasketpage.countryselectionDropdown();// Click this method to see Code Logic of How Different
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**@Description TC-4- Validation -Calculate Shipping charges for differentCountry-Printing them*/ 
	@Then("User verfies shipping charges of the selected country and  Printing them")
	public void user_verfies_shipping_charges_of_the_selected_country_and_printing_them() {
		shoppingbasketpage.printShippingFeesForPortugal();// logic of shipping charges calculation
	}
}
