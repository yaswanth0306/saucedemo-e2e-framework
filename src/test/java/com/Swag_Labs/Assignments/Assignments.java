package com.Swag_Labs.Assignments;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Swag_Labs.Generic.BaseClass;
import com.Swag_Labs.POM.CartPage;
import com.Swag_Labs.POM.CheckoutCompletePage;
import com.Swag_Labs.POM.CheckoutInfoPage;
import com.Swag_Labs.POM.CheckoutOverviewPage;
import com.Swag_Labs.POM.LoginPage;
import com.Swag_Labs.POM.ProductPage;

public class Assignments extends BaseClass{
	
	@Test
	public void verifyTitle() {
		LoginPage lp = new LoginPage(driver);
		lp.login(driver, "standard_user", "secret_sauce");
		String actualTitle = driver.getTitle();
		String exceptedTitle = "Swag Labs";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualTitle, exceptedTitle);
		sa.assertAll();
	}
	
	@Test
	public void addProductsToCartTest() {
	    LoginPage login = new LoginPage(driver);
	    ProductPage products = new ProductPage(driver);

	    // Step 1: Login
	    login.login(driver,"standard_user", "secret_sauce");

	    // Step 2: Verify login successful
	    Assert.assertTrue(products.isProductsPageDisplayed(), "Login Failed!");

	    // Step 3: Add two products
	    products.addTwoProductsToCart();

	    // Step 4: Go to cart page
	    products.goToCart();
	}
	
	@Test(priority = 4)
	public void verifyCartItems() {
	    CartPage cartPage = new CartPage(driver);

	    Assert.assertTrue(cartPage.isCartPageDisplayed(), "Cart page not displayed");
	    Assert.assertTrue(cartPage.verifyItemsInCart(2), "Items count mismatch in cart");

	    cartPage.clickCheckout(); // proceed to checkout
	}

	 @Test(priority = 5, dataProvider = "checkoutData")
	    public void enterCheckoutDetails(String firstName, String lastName, String zip) {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickCheckout();

	        CheckoutInfoPage checkoutPage = new CheckoutInfoPage(driver);
	        checkoutPage.enterCheckoutInformation(firstName, lastName, zip);
	        checkoutPage.clickContinue();

	        // Verification: Check if you are on Order Summary page
	        Assert.assertTrue(driver.getPageSource().contains("Checkout: Overview"),
	                "Failed to navigate to order summary page");
	    }
	 
	 
	 @Test(priority = 6)
	    public void validateOrderSummaryAndCompletePurchase() {
	        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);

	        Assert.assertTrue(overviewPage.isOverviewPageDisplayed(),
	                "Order Summary page not displayed.");

	        int itemCount = overviewPage.getSummaryItemCount();
	        Assert.assertEquals(itemCount, 2, "Incorrect number of items in summary.");

	        String total = overviewPage.getTotalPrice();
	        System.out.println("Order Total: " + total);

	        // Finish order
	        overviewPage.clickFinish();

	        // Validate order confirmation
	        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
	        Assert.assertTrue(completePage.isOrderComplete(), "Order not completed successfully.");
	        Assert.assertEquals(completePage.getConfirmationMessage(), "Thank you for your order!");

	        // Optional: Return to home
	        completePage.clickBackHome();
	    }
	 
	 @Test(priority = 7)
	    public void verifyOrderCompletion() {
	        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);

	        // ✅ Step 1: Validate confirmation message
	        String confirmation = completePage.getConfirmationMessage();
	        System.out.println("Confirmation Message: " + confirmation);

	        Assert.assertEquals(confirmation, "Thank you for your order!",
	                "Confirmation message does not match!");

	        // ✅ Step 2: Optional - Print subtext
	        System.out.println("Subtext: " + completePage.getConfirmationSubText();

	        // ✅ Step 3: Validate order completion flag
	        Assert.assertTrue(completePage.isOrderComplete(),
	                "Order was not completed successfully!");

	        // ✅ Step 4: Navigate back home
	        completePage.clickBackHome();
	    }
	
	


}
