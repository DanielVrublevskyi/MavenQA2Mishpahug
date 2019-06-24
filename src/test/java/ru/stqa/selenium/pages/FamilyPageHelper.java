package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyPageHelper extends PageBase {
    @FindBy(id = "idbtnaddevent")
    WebElement plusButton;

    @FindBy(css = "#fieldobjconfession")
    WebElement confessionField;

    @FindBy(css = "span[id='fieldobjlanguages']")
    WebElement languagesField;

    @FindBy(css = "#fieldobjfoodPreferences")
    WebElement foodField;

    public FamilyPageHelper(WebDriver driver) {
        super(driver);
    }

    public FamilyPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(plusButton,20);
        waitUntilElementIsVisible(confessionField,15);
        return this;
    }

    public boolean confessionValueIs(String value) {
        return confessionField.getText().equals(value);
    }

    public boolean languagesValueIs(String value) {
        return languagesField.getText().equals(value);
    }

    public boolean foodValueIs(String value) {
        return foodField.getText().equals(value);
    }
}
