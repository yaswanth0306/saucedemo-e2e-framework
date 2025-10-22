package com.Swag_Labs.Assignments;


import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.Swag_Labs.Generic.BaseClass;
import com.Swag_Labs.POM.CartPage;
import com.Swag_Labs.POM.CheckoutPage;
import com.Swag_Labs.POM.ConfirmationPage;
import com.Swag_Labs.POM.LoginPage;
import com.Swag_Labs.POM.OrderSummaryPage;
import com.Swag_Labs.POM.ProductPage;

public class Assignments extends BaseClass{
	
	@Test(priority = 1)
	public void verifyTitle() {
		LoginPage lp = new LoginPage(driver);
		lp.login(driver, "standard_user", "secret_sauce");
		String actualTitle = driver.getTitle();
		String exceptedTitle = "Swag Labs";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualTitle, exceptedTitle);
		sa.assertAll();
	}
	
	@Test(priority = 2)
	public void addProductsToCartTest() {
	    LoginPage login = new LoginPage(driver);
	    ProductPage products = new ProductPage(driver);
	    login.login(driver,"standard_user", "secret_sauce");
	  //  Assert.assertTrue(products.isProductsPageDisplayed(), "Login Failed!");
	    products.addTwoProducts();
	    products.goToCart();
	}
	
	@Test(priority = 3)
	public void validateAddedProductsAndCount() {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.login(driver,"standard_user", "secret_sauce");

	    ProductPage productPage = new ProductPage(driver);
	    productPage.addTwoProducts();

	    List<String> addedProducts = productPage.getAddedProductNames();
	    int addedCount = productPage.getAddedProductCount();

	    CartPage cartPage = productPage.goToCart();
	    List<String> cartProducts = cartPage.getCartProductNames();
	    int cartCount = cartPage.getCartProductCount();

	    System.out.println("Added Products: " + addedProducts);
	    System.out.println("Cart Products: " + cartProducts);
	    System.out.println("Added Count: " + addedCount + " | Cart Count: " + cartCount);
	    
	    Assert.assertEquals(cartCount, addedCount, " Product count mismatch!");
	    Assert.assertEquals(cartProducts, addedProducts, " Cart products do not match added products!");

	    System.out.println(" Products and count match successfully!");
	}


	 @Test(priority = 4)
	    public void enterCheckoutDetails(String firstName, String lastName, String zip) {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.enterCheckoutInformation(firstName, lastName, zip);
	        checkoutPage.clickContinue();
	     
	    }
	 
	 @Test
	 public void validateOrderSummaryItems() {
	     LoginPage loginPage = new LoginPage(driver);
	     loginPage.login(driver,"standard_user", "secret_sauce");

	     ProductPage productPage = new ProductPage(driver);
	     productPage.addTwoProducts();
	     CartPage cartPage = productPage.goToCart();

	  
	     List<String> cartItems = cartPage.getCartProductNames();
	     System.out.println("Items in Cart: " + cartItems);

	     CheckoutPage checkoutPage = cartPage.clickCheckout();
	     checkoutPage.enterCheckoutInformation(null, null, null);;
	     OrderSummaryPage summaryPage = checkoutPage.clickContinue();

	   
	     List<String> summaryItems = summaryPage.getSummaryItemNames();
	     System.out.println("Items in Summary: " + summaryItems);

	     Assert.assertEquals(summaryItems.size(), cartItems.size(),
	             "❌ Item count mismatch between Cart and Summary!");

	     for (int i = 0; i < cartItems.size(); i++) {
	         Assert.assertEquals(summaryItems.get(i), cartItems.get(i),
	                 "❌ Product mismatch: " + cartItems.get(i) + " vs " + summaryItems.get(i));
	     }

	     System.out.println("✅ Order Summary Validation Passed!");

	    
	     summaryPage.printOrderSummaryDetails();

	     
	     ConfirmationPage confirmationPage = summaryPage.clickFinish();
	     Assert.assertTrue(confirmationPage.isOrderConfirmed(), "❌ Order confirmation failed!");
	 }

	 
	 @Test
	 public void verifyOrderCompletion() {
	     LoginPage loginPage = new LoginPage(driver);
	     loginPage.login(driver,"standard_user", "secret_sauce");

	     ProductPage productPage = new ProductPage(driver);
	     productPage.addTwoProducts();
	     CartPage cartPage = productPage.goToCart();

	     CheckoutPage checkoutPage = cartPage.clickCheckout();
	     checkoutPage.enterCheckoutInformation("John", "Doe", "560001");
	     OrderSummaryPage summaryPage = checkoutPage.clickContinue();

	     ConfirmationPage confirmationPage = summaryPage.clickFinish();

	     Assert.assertTrue(confirmationPage.isOrderConfirmed(), "❌ Order confirmation not displayed!");

	     
	     String message = confirmationPage.getConfirmationMessage();
	     System.out.println("✅ Confirmation Message: " + message);

	     
	     Assert.assertTrue(message.contains("dispatched"), "❌ Order message not as expected!");
	     System.out.println("✅ Order completed successfully!");

	     confirmationPage.clickBackHome();
	 }

	
	


}
