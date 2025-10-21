package com.Swag_Labs.POM;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    private WebDriver driver;

    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement confirmationMessage;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText(); // e.g. "Thank you for your order!"
    }

    public boolean isOrderComplete() {
        return confirmationMessage.isDisplayed();
    }

    public void clickBackHome() {
        backHomeButton.click();
    }
}

