package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class ProfilePageHelper  extends PageBase{
    @FindBy(id = "idbtneditprofile")
    WebElement editButton;
    @FindBy(id = "titleprofile")
    WebElement titleProfile;
    @FindBy(id = "ihome")
    WebElement homeAuthIcon;
    @FindBy(id = "statusinmishpahuginprofile")
    WebElement userRole;
    @FindBy(id = "family")
    WebElement familyIcon;

    @FindBy(xpath = "//span[@id='fieldobjconfession']")
    WebElement confessionField;
    @FindBy(xpath = "//div[contains(text(),'Languages:')]/../span")
    WebElement languagesField;
    @FindBy(xpath = "//span[@id='fieldobjfoodPreferences']")
    WebElement foodField;

    ProfileEditPageHelper profileEdit;

    public ProfilePageHelper(WebDriver driver) {
        super(driver);
        profileEdit = PageFactory.initElements(driver,ProfileEditPageHelper.class);

    }

    public ProfilePageHelper waitUntilPageIsLoaded(){
        waitUntilElementClickable(editButton,20);
        waitUntilElemContainsText(titleProfile,"My Profile",20);
        waitUntilAllElementsVisible(By.xpath("//div[@class='alert alert-success']"), 20);
        return this;
    }

    public String getTitleText() {
        return titleProfile.getText();
    }

    public ProfilePageHelper goToEditMode(){
        clickButton(editButton);
        return this;
    }

    public ProfilePageHelper openHomeAuthPage(){
        clickButton(homeAuthIcon);
        return this;
    }

    public ProfilePageHelper openFamilyPage(){
        clickButton(familyIcon);
        return this;
    }

    public ProfilePageHelper changeCurrentRoleToNew(String role){
        String[] roles = {"Guest","Family","GuestFamily"};
        if (!Arrays.asList(roles).contains(role)) try {
            throw new Exception("Error: Wrong role. There is no role '" + role + "' in the system");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.goToEditMode();
        profileEdit.waitUntilPageIsLoaded();
        if(role.equals(roles[0])) profileEdit.setGuestRole();
        if(role.equals(roles[1])) profileEdit.setFamilyRole();
        if(role.equals(roles[2])) profileEdit.setGuestFamilyRole();
        profileEdit.saveProfile();
        this.waitUntilPageIsLoaded();
        return this;
    }

    public String returnUserRole() {
        return userRole.getText();
    }

    public String getConfesions(){
        return confessionField.getText();
    }
    public String getFood(){
        return foodField.getText();
    }

    public String getLanguages(){
        return languagesField.getText();
    }
}
