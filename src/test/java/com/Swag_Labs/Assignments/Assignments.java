package com.Swag_Labs.Assignments;


import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.Swag_Labs.Generic.BaseClass;
import com.Swag_Labs.Generic.FileUtils;
import com.Swag_Labs.POM.CartPage;
import com.Swag_Labs.POM.CheckoutPage;
import com.Swag_Labs.POM.ConfirmationPage;
import com.Swag_Labs.POM.LoginPage;
import com.Swag_Labs.POM.OrderSummaryPage;
import com.Swag_Labs.POM.ProductPage;

public class Assignments extends BaseClass {

    @Test(priority = 1)
    public void completeE2EOrderFlow() throws IOException {

    	// verifying title
        String actualTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitle, expectedTitle, " Page title mismatch!");
        System.out.println("Title verified: " + actualTitle);

        // Add products to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addTwoProducts();
        CartPage cartPage = productPage.goToCart();

        List<String> cartItems = cartPage.getCartProductNames();
        List<Double> cartPrices = cartPage.getCartProductPrices();
        int cartCount = cartPage.getCartProductCount();

        System.out.println(" Cart Items: " + cartItems + " and  Count: " + cartCount);
        System.out.println(" Cart Prices: " + cartPrices);
        
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        //  Checkout with Excel data
        FileUtils fileUtils = new FileUtils();
        String firstName = fileUtils.readDataFromCheckoutdetailsExcel("Sheet1", 1, 0);
        String lastName = fileUtils.readDataFromCheckoutdetailsExcel("Sheet1", 1, 1);
        String zipCode = fileUtils.readDataFromCheckoutdetailsExcel("Sheet1", 1, 2);
        checkoutPage.enterCheckoutInformation(firstName, lastName, zipCode);

        OrderSummaryPage summaryPage = checkoutPage.clickContinue();

        // Validate order summary items and prices
        List<String> summaryItems = summaryPage.getSummaryItemNames();
        List<Double> summaryPrices = summaryPage.getSummaryItemPrices();

        // Validate names
        softAssert.assertEquals(summaryItems, cartItems, " Summary items do not match Cart items!");
        System.out.println(" Order Summary validated: " + summaryItems);

        // Validate prices
        for (int i = 0; i < cartItems.size(); i++) {
        	softAssert.assertEquals(summaryPrices.get(i), cartPrices.get(i),
                    " Price mismatch for item: " + cartItems.get(i));
        }
        System.out.println("Price comparison passed: " + summaryPrices);

        summaryPage.printOrderSummaryDetails();

        //  Finish order and confirm
        ConfirmationPage confirmationPage = summaryPage.clickFinish();
        softAssert.assertTrue(confirmationPage.isOrderConfirmed(), " Order confirmation failed!");
        String message = confirmationPage.getConfirmationMessage();
        System.out.println("✅ Confirmation Message: " + message);
        softAssert.assertTrue(message.contains("dispatched"), " Unexpected confirmation message!");

        //  Back to home
        confirmationPage.clickBackHome();
        System.out.println(" Order completed and back to home page.");
        
        softAssert.assertAll();
    }
}

