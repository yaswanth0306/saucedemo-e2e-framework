package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class OrderSummaryPage {
    private WebDriver driver;

    @FindBy(xpath = "//span[@class='title' and text()='Checkout: Overview']")
    private WebElement summaryTitle;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> summaryItems;

    @FindBy(className = "summary_subtotal_label")
    private WebElement itemTotalLabel;

    @FindBy(className = "summary_tax_label")
    private WebElement taxLabel;

    @FindBy(className = "summary_total_label")
    private WebElement totalLabel;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public OrderSummaryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ✅ Get product names in summary page
    public List<String> getSummaryItemNames() {
        List<String> names = new ArrayList<>();
        for (WebElement item : summaryItems) {
            names.add(item.getText().trim());
        }
        return names;
    }

    // ✅ Print all pricing info (optional verification)
    public void printOrderSummaryDetails() {
        System.out.println("Subtotal: " + itemTotalLabel.getText());
        System.out.println("Tax: " + taxLabel.getText());
        System.out.println("Total: " + totalLabel.getText());
    }

    // ✅ Finish order
    public ConfirmationPage clickFinish() {
        finishButton.click();
        return new ConfirmationPage(driver);
    }
}
	