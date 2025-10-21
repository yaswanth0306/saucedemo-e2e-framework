package com.Swag_Labs.POM;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOrderCompletionpage {

    private WebDriver driver;

    // Confirmation message after successful order
    @FindBy(xpath = "//h2[@class='complete-header']")
    private WebElement confirmationMessage;

    // Subtext message (optional)
    @FindBy(xpath = "//div[@class='complete-text']")
    private WebElement confirmationSubText;

    // Back to home button
    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    // Constructor
    public CheckoutOrderCompletionpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Capture main confirmation message
    public String getConfirmationMessage() {
        return confirmationMessage.getText();  // Expected: "Thank you for your order!"
    }

    // ✅ Capture subtext message
    public String getConfirmationSubText() {
        return confirmationSubText.getText();  // Expected: "Your order has been dispatched..."
    }

    // ✅ Verify order completion
    public boolean isOrderComplete() {
        return confirmationMessage.isDisplayed() &&
               getConfirmationMessage().equalsIgnoreCase("Thank you for your order!");
    }

    // ✅ Go back to products page
    public void clickBackHome() {
        backHomeButton.click();
    }
}

