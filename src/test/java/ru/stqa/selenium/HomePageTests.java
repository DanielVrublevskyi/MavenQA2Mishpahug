package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import org.testng.annotations.Test;
import org.testng.Assert;
import ru.stqa.selenium.util.DataProviders;

import java.util.Set;

public class HomePageTests extends TestBase{
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;


    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory
                .initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePage.waitUntilPageIsLoaded();
    }

    @Test(groups = {"sanity"})
    public void loginPageIsLoadedTest(){
        log.startTestCase("loginPageIsLoadedTest");
        log.info("Open login page");
        homePage.openLoginPage();
        log.info("Wait until page is loaded");
        loginPage.waitUntilPageIsLoaded();
        log.info("Assert: Verify if it is login page");
        Assert.assertTrue(loginPage.itIsLoginPage(),
                "Not Passed. It is not login page. There is no SignIn button.");
        log.endTestCase("loginPageIsLoadedTest");
    }

    @Test (groups = {"regression"},dataProviderClass = DataProviders.class, dataProvider = "singleFilterByHoliday")
    public void singleFilterByHoliday(String holiday){
        //String holiday = "Purimm";
        log.startTestCase("singleFilterByHoliday");
        log.info("PARAMETER: " + holiday);
        log.info("Choose filter by" + holiday);
        homePage.chooseFilterHoliday(holiday)
                 .waitEventsListReloaded();
        log.info("Assert: Verify if holiday of all events are" + holiday);
       Assert.assertTrue(homePage.isEventsHoliday(holiday),
               "Not passed. Exists event with another holiday value (not " + holiday);
       log.endTestCase("singleFilterByHoliday-" + holiday);
    }

    @Test
    public void doubleFilterByFoodConf(){
        log.startTestCase("doubleFilterByFoodConf");
        log.info("FILTER 'FOOD', PARAMETER: " + FILTER_HOLIDAY_FOOD);
        log.info("FILTER 'CONFESSION',PARAMETER: " + FILTER_HOLIDAY_CONF);
        homePage.chooseFilterFood(FILTER_HOLIDAY_FOOD)
                .waitEventsListReloaded()
                .chooseFilterConf(FILTER_HOLIDAY_CONF)
                .waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsContainsPreference(FILTER_HOLIDAY_FOOD),
                "Not passed. Not all events contain food value" + FILTER_HOLIDAY_FOOD);
        Assert.assertTrue(homePage.isEventsContainsPreference(FILTER_HOLIDAY_CONF),
                "Not passed. Not all events contain confession value " + FILTER_HOLIDAY_CONF);
        log.endTestCase("doubleFilterByFoodConf");
    }
/*
    @Test
    public void singleFilterByHolidaysWithWindows(){
        homePage.chooseFilterHoliday(FILTER_HOLIDAY_SHABBAT)
                .waitEventsListReloaded();
        System.out.println("Window before click:" + driver
                .getWindowHandle());
        System.out.println("All windows before click: " + driver
                .getWindowHandles());
        String mainHandle = driver.getWindowHandle();
        System.out.println("mainHandle: "+mainHandle);
        driver.findElement(By.className("moreItemEvents")).click();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Window after click:" + driver
                .getWindowHandle());
        System.out.println("All windows before click: " + driver
                .getWindowHandles());
        Set<String> setHandles = driver.getWindowHandles();
        System.out.println("setHandles: " + setHandles);
        String anotherHandle="";
        for(String handle: setHandles){
            if(!handle.equals(mainHandle)) anotherHandle=handle;
        }
        driver.switchTo().window(anotherHandle);
        System.out.println("window after switching:" + driver
                .getWindowHandle());
        driver.close();
        driver.switchTo().window(mainHandle);
    }
    */

    @Test(groups = {"regression"},dataProviderClass = DataProviders.class, dataProvider = "singleFilterByHoliday")
    public void singleFilterByHolidaysEventsInWindow(String holiday){
        log.startTestCase("singleFilterByHolidaysEventsInWindow");
        log.info("PARAMETER: " + holiday);
        homePage.chooseFilterHoliday(holiday)
                .waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsHolidayInWindow(holiday),
                "Not passed. Exists event with another holiday value (not " + holiday + ")");
        log.endTestCase("singleFilterByHolidaysEventsInWindow");

    }


}
