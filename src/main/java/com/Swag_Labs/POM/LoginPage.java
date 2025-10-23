package com.Swag_Labs.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement login_btn;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // ✅ Correct method
    public void login(String un, String pw) {
        username.sendKeys(un);
        password.sendKeys(pw);
        login_btn.click();
    }
}

