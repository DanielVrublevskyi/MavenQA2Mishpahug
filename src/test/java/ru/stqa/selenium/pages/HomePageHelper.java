package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageHelper extends PageBase{
    @FindBy(id = "idsignin")
    WebElement loginIcon;

    @FindBy(className = "navi-item")
    List<WebElement> iconList;

    @FindBy(id = "ihome")
    WebElement homeAuthIcon;

    @FindBy(id = "profile")
    WebElement profileIcon;





    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded(){

        waitUntilElementClickable(loginIcon,20);

        return this;
    }

    public HomePageHelper openLoginPage() {
        clickButton(loginIcon);
        return this;
    }



    public boolean homeIconIsDisplayed() {
        return  iconList.get(0).isDisplayed();
    }

    public boolean loginIconIsDisplayed() {
        return iconList.get(1).isDisplayed();
    }

    public boolean registrationIconIsDisplayed() {
        return iconList.get(2).isDisplayed();
    }

    public boolean homeAuthIconIsHidden() {
        return !homeAuthIcon.isDisplayed();
    }

    public boolean profileIconIsHidden() {
        return !profileIcon.isDisplayed();
    }


}
