package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
	
	
	    private WebDriver driver;

	    // --- Locators for checkout fields ---
	    @FindBy(id = "first-name")
	    private WebElement firstNameInput;

	    @FindBy(id = "last-name")
	    private WebElement lastNameInput;

	    @FindBy(id = "postal-code")
	    private WebElement zipCodeInput;

	    @FindBy(id = "continue")
	    private WebElement continueButton;

	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    
	    public void enterCheckoutInformation(String firstName, String lastName, String zip) {
	        firstNameInput.clear();
	        firstNameInput.sendKeys(firstName);

	        lastNameInput.clear();
	        lastNameInput.sendKeys(lastName);

	        zipCodeInput.clear();
	        zipCodeInput.sendKeys(zip);
	    }

	    public OrderSummaryPage clickContinue() {
	        continueButton.click();
	        return new OrderSummaryPage(driver);
	    }
	}



