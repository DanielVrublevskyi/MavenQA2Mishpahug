package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroWindowHelper extends PageBase {
    @FindBy(id = "closedIntro")
    WebElement closedIntro;

    public IntroWindowHelper(WebDriver driver) {
        super(driver);
    }

    public IntroWindowHelper waitUntilPageIsLoaded(){
        waitUntilElementClickable(closedIntro,70);
        return this;
    }

    public IntroWindowHelper closeWindow(){
        clickButton(closedIntro);
        return this;
    }
}
