package com.Swag_Labs.POM;



import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	    private WebDriver driver;

	    // 1️⃣ Cart Page Title
	    @FindBy(xpath = "//span[@class='title']")
	    private WebElement cartTitle;

	    // 2️⃣ List of all products in cart
	    @FindBy(xpath = "//div[@class='cart_item']")
	    private List<WebElement> cartItems;

	    // 3️⃣ Checkout Button
	    @FindBy(id = "checkout")
	    private WebElement checkoutButton;

	    // Constructor
	    public CartPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // ✅ Verify Cart Page Loaded
	    public boolean isCartPageDisplayed() {
	        return cartTitle.isDisplayed();
	    }

	    // ✅ Get count of products in cart
	    public int getCartItemsCount() {
	        return cartItems.size();
	    }

	    // ✅ Validate that 2 products were added
	    public boolean verifyItemsInCart(int expectedCount) {
	        return cartItems.size() == expectedCount;
	    }

	    // ✅ Proceed to checkout
	    public void clickCheckout() {
	        checkoutButton.click();
	    }
	}


