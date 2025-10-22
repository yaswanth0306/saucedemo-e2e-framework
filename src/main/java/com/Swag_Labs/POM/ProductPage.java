package com.Swag_Labs.POM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {
    private WebDriver driver;

    @FindBy(xpath = "//div[@class='inventory_item_name ']")
    private List<WebElement> allProductTitles;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    private final List<String> addedProducts = new ArrayList<>();

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Add products dynamically and store their names
    public void addTwoProducts() {
        addBackpackButton.click();
        addedProducts.add("Sauce Labs Backpack");

        addBikeLightButton.click();
        addedProducts.add("Sauce Labs Bike Light");
    }

    // ✅ Get list of products that were added
    public List<String> getAddedProductNames() {
        return addedProducts;
    }

    // ✅ Get total count of added items
    public int getAddedProductCount() {
        return addedProducts.size();
    }

    public CartPage goToCart() {
        cartIcon.click();
        return new CartPage(driver);
    }
}
