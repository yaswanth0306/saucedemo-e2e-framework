package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private WebDriver driver;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> cartItems;
    
    @FindBy(css = ".cart_item .inventory_item_price")
    private List<WebElement> cartProductPrices;
    
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<String> getCartProductNames() {
        List<String> cartProductNames = new ArrayList<>();
        for (WebElement item : cartItems) {
            cartProductNames.add(item.getText().trim());
        }
        return cartProductNames;
    }
    
    public List<Double> getCartProductPrices() {
        List<Double> prices = new ArrayList<>();
        for (WebElement e : cartProductPrices) {
            // Remove $ and convert to double
            prices.add(Double.parseDouble(e.getText().replace("$", "").trim()));
        }
        return prices;
    }

    
    public int getCartProductCount() {
        return cartItems.size();
    }
    

    public CheckoutPage clickCheckout() {
        checkoutButton.click();
        return new CheckoutPage(driver);
    }

}
