package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.util.Set;

public class HomePageTests extends TestBase{
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;


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

    @Test
    public void singleFilterByHoliday(){
        homePage.chooseFilterHoliday(FILTER_HOLIDAY_SHABBAT)
                 .waitEventsListReloaded();
       Assert.assertTrue(homePage.isEventsHoliday(FILTER_HOLIDAY_SHABBAT));
    }

    @Test
    public void doubleFilterByFoodConf(){
        homePage.chooseFilterFood(FILTER_HOLIDAY_FOOD)
                .waitEventsListReloaded()
                .chooseFilterConf(FILTER_HOLIDAY_CONF)
                .waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsContainsPreference(FILTER_HOLIDAY_FOOD));
        Assert.assertTrue(homePage.isEventsContainsPreference(FILTER_HOLIDAY_CONF));
    }

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
    @Test
    public void singleFilterByHolidaysEventsInWindow(){
        homePage.chooseFilterHoliday(FILTER_HOLIDAY_SHABBAT)
                .waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsHolidayInWindow(FILTER_HOLIDAY_SHABBAT));

    }


}
