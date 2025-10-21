package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
public class CheckoutOverviewPage {
	
	    private WebDriver driver;

	    // --- List of all items in order summary ---
	    @FindBy(xpath = "//div[@class='cart_item']")
	    private List<WebElement> summaryItems;

	    // --- Total price label ---
	    @FindBy(xpath = "//div[@class='summary_total_label']")
	    private WebElement totalPrice;

	    // --- Finish button to complete purchase ---
	    @FindBy(id = "finish")
	    private WebElement finishButton;

	    // --- Constructor ---
	    public CheckoutOverviewPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // --- Verify order summary displayed ---
	    public boolean isOverviewPageDisplayed() {
	        return driver.getPageSource().contains("Checkout: Overview");
	    }

	    // --- Get number of products listed in summary ---
	    public int getSummaryItemCount() {
	        return summaryItems.size();
	    }

	    // --- Get total price text ---
	    public String getTotalPrice() {
	        return totalPrice.getText(); // e.g. "Total: $43.18"
	    }

	    // --- Click finish to complete purchase ---
	    public void clickFinish() {
	        finishButton.click();
	    }
	}



