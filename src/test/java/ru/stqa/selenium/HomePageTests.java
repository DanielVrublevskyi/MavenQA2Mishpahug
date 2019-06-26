package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import org.testng.annotations.Test;
import org.testng.Assert;

public class HomePageTests extends TestBase{
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;
    public static final String FILTER_HOLIDAY_SHABBAT = "Shabbat";

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory
                .initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @Test
    public void loginPageIsLoadedTest(){
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        Assert.assertTrue(loginPage.itIsLoginPage());
    }

    public void singleFilterByHoliday(){
        homePage.chooseFilterHoliday(FILTER_HOLIDAY_SHABBAT);
        //homePage.waitEventsListReloaded();
        //Assert.assertTrue(homePage.isEventsHoliday(FILTER_HOLIDAY_SHABBAT));
    }


}
