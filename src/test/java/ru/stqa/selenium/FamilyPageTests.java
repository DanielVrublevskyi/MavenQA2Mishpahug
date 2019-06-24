package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;


public class FamilyPageTests extends TestBase {
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;
    public HomePageAuthHelper homePageAuth;
    public ProfilePageHelper profilePage;
    public FamilyPageHelper familyPage;

    @BeforeMethod
    public void initTests() {

        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);
        familyPage = PageFactory.initElements(driver,FamilyPageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .enterLoginPassword(LOGIN, PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
    }

    @Test
    public void profileFamilyInfoTest()  {
        homePageAuth.openProfilePage();
        profilePage.waitUntilPageIsLoaded();
        if(profilePage.returnUserRole().equals("Guest")) {
            profilePage.changeCurrentRoleToNew("Family");
        }

        //------- Profile fields values saving ----------------------------
        String confessionProfile = profilePage.getConfesions();
        String languagesProfile = profilePage.getLanguages();
        String foodProfile = profilePage.getFood();

        profilePage.openFamilyPage();
        familyPage.waitUntilPageIsLoaded();

        int counter = 0;
        if (familyPage.confessionValueIs(confessionProfile)) counter++;
        if (familyPage.languagesValueIs(languagesProfile)) counter++;
        if (familyPage.foodValueIs(foodProfile)) counter++;

        Assert.assertEquals(3,counter);
    }
}
