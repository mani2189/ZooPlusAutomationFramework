Feature: Validation of ZooPlus Products and its Price values Comparision

 #TC-1
  Scenario: Validation of url contains Overview and print url to the console
    Given User opens the URL "https://www.zooplus.com/checkout/cartEmpty.htm"
    When User Click on Agree and Continue
    Then User adds one product to cart and validates the URL

 #TC-2
  Scenario: Validation of multiple products addition to cart and Sorting the prices
    Given User add multiple products to cart
    When User gets the List of all prices and printing the price values before sorting
    And User sorting the price values in the Decending order and printing after sorting

 #TC-3
  Scenario: Validation the Deletion of Highest price value and Asserting the Subtotal and Total Individually
    Given User finds the minumum and maximum value of the product in the cart
    When User increases one more quanity for the lowest price product
    Then User deletes the Maximum price value product
    And User Verifies the Sum of SubTotal and Total value of product

  #TC-4
  Scenario: Validation of  Subtotal and total prices for Different Shipping Country
    Given User changes the shipping country to Portugal with postalcode
    Then User verfies shipping charges of the selected country and  Printing them
    And User Verifies the Sum of SubTotal and Total value of product
   
