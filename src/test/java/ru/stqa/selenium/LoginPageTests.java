package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;


public class LoginPageTests extends TestBase{
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;
    public HomePageAuthHelper homePageAuth;



    @BeforeMethod
    public void initTests(){
        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage.waitUntilPageIsLoaded();

    }

    @Test
    public void loginPositive() {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                 .enterLoginPassword(LOGIN, PASSWORD);
        homePageAuth.waitUntilPageIsLoaded();
        Assert.assertTrue(homePageAuth.profileButtonTitleContainsText(LOGIN));
    }


    @Test
    public void loginNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded()
                .enterLoginPassword(LOGIN,  PASSWORD +"11")
                .cancelModalWindowIfNoWrongMessage();

        Assert.assertTrue(loginPage.wrongMessageIsDisplayed());
        loginPage.closeLoginWindow();
        homePage.waitUntilPageIsLoaded();
        int counter = 0;
        if(homePage.homeIconIsDisplayed()) counter++;
        if(homePage.loginIconIsDisplayed()) counter++;
        if(homePage.registrationIconIsDisplayed()) counter++;
        if(homePage.homeAuthIconIsHidden()) counter++;
        if(homePage.profileIconIsHidden()) counter++;

        Assert.assertEquals(counter,5);


    }
}
