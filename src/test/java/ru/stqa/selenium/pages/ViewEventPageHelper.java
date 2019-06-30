package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ViewEventPageHelper extends PageBase {
    @FindBy(id = "holidayEvent")
    WebElement holidayEvent;

    public ViewEventPageHelper(WebDriver driver) {
        super(driver);
    }

    public ViewEventPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsVisible(holidayEvent,30);
        return this;
    }

    public String getHolidayBanner(){
        return holidayEvent.getText();
    }
}
