package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;

public class ProfilePageTests extends TestBase {
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;
    public HomePageAuthHelper homePageAuth;
    public ProfilePageHelper profilePage;


    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);

        homePage.waitUntilPageIsLoaded()
                .openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .enterLoginPassword(LOGIN, PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
    }

    @Test
    public void profileTitleTest()  {
        homePageAuth.openProfilePage();
        profilePage.waitUntilPageIsLoaded();
        Assert.assertEquals(profilePage.getTitleText(),"My Profile: " + LOGIN);
    }

    @Test
    public void profileURGuestTest()  {
        homePageAuth.openProfilePage();
        profilePage.waitUntilPageIsLoaded()
                    .changeCurrentRoleToNew("Guest")
                    .openHomeAuthPage();
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertFalse(homePageAuth.plusButtonIsDisplayed());
    }

    @Test
    public void profileURFamilyTest()  {
        homePageAuth.openProfilePage();
        profilePage.waitUntilPageIsLoaded()
                .changeCurrentRoleToNew("Family")
                .openHomeAuthPage();
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.plusButtonIsDisplayed());
    }

    @Test
    public void profileURFamilyAndGustTest()  {
        homePageAuth.openProfilePage();
        profilePage.waitUntilPageIsLoaded()
                .changeCurrentRoleToNew("GuestFamily")
                .openHomeAuthPage();
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.plusButtonIsDisplayed());
    }
}
