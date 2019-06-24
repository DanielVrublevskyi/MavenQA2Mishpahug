package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageAuthHelper extends PageBase {
    @FindBy(id = "profile")
    WebElement profileIcon;

    @FindBy(id = "idcontainerbtnaddevent")
    WebElement plusButton;

    @FindBy(xpath = "//span[@id='typeviewsearchcalendar']")
    WebElement viewCalendar;

    public HomePageAuthHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageAuthHelper waitUntilPageIsLoaded(){
        waitUntilElementClickable(profileIcon,20);
        waitUntilElementClickable(viewCalendar,20);
        return this;
    }
    public HomePageAuthHelper openProfilePage(){
        clickButton(profileIcon);
        return this;
    }

    public boolean profileButtonTitleContainsText(String login) {
        return profileIcon.getAttribute("title").contains(login);
    }

    public boolean plusButtonIsDisplayed(){return plusButton.isDisplayed();}
}
