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
        log.info("---ViewPageHelper:waitUntilPageIsLoaded method was started" );
        waitUntilElementIsVisible(holidayEvent,30);
        return this;
    }

    public String getHolidayBanner(){
        log.info("---ViewPageHelper:getHolidayBanner method was started" );
        return holidayEvent.getText();
    }
}
