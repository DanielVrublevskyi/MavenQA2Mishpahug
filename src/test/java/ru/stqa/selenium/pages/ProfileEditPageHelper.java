package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfileEditPageHelper extends PageBase{
    @FindBy(id = "idbtnsaveprofile")
    WebElement saveButton;

    @FindBy(id = "idbtncancelsaveprofile")
    WebElement cancelButton;

    @FindBy(id = "typeuser1inprofile")
    WebElement guestButton;

    @FindBy(id = "typeuser2inprofile")
    WebElement familyButton;

    @FindBy(id = "typeuser3inprofile")
    WebElement guestFamilyButton;

    public ProfileEditPageHelper(WebDriver driver) {
        super(driver);
    }
    public ProfileEditPageHelper waitUntilPageIsLoaded(){
        waitUntilElementClickable(By.id("typeuser1inprofile"),10);
        waitUntilElementClickable(saveButton,10);
        waitUntilElementClickable(cancelButton,10);
        return this;
    }

    public ProfileEditPageHelper setGuestRole(){
        clickButton(guestButton);
        waitUntilElemAttrContainsText(guestButton,"class","active",10);
        return this;
    }

    public ProfileEditPageHelper setFamilyRole(){
        clickButton(familyButton);
        waitUntilElemAttrContainsText(familyButton,"class","active",10);
        return this;
    }

    public ProfileEditPageHelper setGuestFamilyRole(){
        clickButton(guestFamilyButton);
        waitUntilElemAttrContainsText(guestFamilyButton,"class","active",10);
        return this;
    }

    public ProfileEditPageHelper saveProfile(){
        clickButton(saveButton);
        return this;
    }
}
