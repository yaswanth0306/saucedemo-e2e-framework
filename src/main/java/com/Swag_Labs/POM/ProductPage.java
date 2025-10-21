package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	    WebDriver driver;

	    
	    @FindBy(id="add-to-cart-sauce-labs-backpack")
	    private WebElement backpackAddBtn;

	    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
	    private WebElement bikeLightAddBtn;

	    @FindBy(className = "shopping_cart_link")
	    private WebElement cartIcon;

	    @FindBy(xpath = "//span[@class='title']")
	    private WebElement productsTitle;

	    // ✅ Constructor
	    public ProductPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // ✅ Verify you're on the Products page
	    public boolean isProductsPageDisplayed() {
	        return productsTitle.isDisplayed();
	    }

	    // ✅ Add two products
	    public void addTwoProductsToCart() {
	        backpackAddBtn.click();
	        bikeLightAddBtn.click();
	    }

	    // ✅ Navigate to Cart
	    public void goToCart() {
	        cartIcon.click();
	    }

	    // ✅ Optional: Get current page title
	    public String getPageTitle() {
	        return driver.getTitle();
	    }
	}

	


