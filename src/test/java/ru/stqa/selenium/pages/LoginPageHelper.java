package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy(id = "signinrequest")
    WebElement signInButton;

    @FindBy(id = "logininput")
    WebElement loginField;

    @FindBy(id = "passwordinput")
    WebElement passwordField;

    @FindBy(id = "wrongloginorpassword")
    WebElement wrongMessage;

    @FindBy(id = "closedsignin")
    WebElement closeLogin;


    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementClickable(signInButton, 20);
        return this;
    }

    public LoginPageHelper enterLoginPassword(String login, String password) {
        enterTextToField(loginField, login);
        enterTextToField(passwordField, password);
        clickButton(signInButton);
        return this;
    }

    public boolean wrongMessageIsDisplayed() {
        waitUntilElementIsVisible(wrongMessage, 10);
        return wrongMessage.getText().contains("Wrong Authorization!");
    }

    public LoginPageHelper closeLoginWindow() {

        closeLogin.click();
        return this;
    }

    public LoginPageHelper cancelModalWindowIfNoWrongMessage() {
        waitUntilElementIsVisible(wrongMessage, 10);
        if (!wrongMessage.isDisplayed())
            cancelSignInModalWindow();
        return this;
    }
}
