package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
    private WebDriver driver;

    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement confirmationHeader;

    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement confirmationMessage;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isOrderConfirmed() {
        return confirmationHeader.isDisplayed();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    public ProductPage clickBackHome() {
        backHomeButton.click();
        return new ProductPage(driver);
    }
}

