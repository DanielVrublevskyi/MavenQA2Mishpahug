package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;

import java.util.Set;

public class HomePageWebTests extends TestBase{
    public HomePageHelper homePage;
    public LoginPageHelper loginPage;

    @BeforeMethod
    public void initTest(){
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

    @Test(dataProviderClass = DataProviders.class, dataProvider = "singleFilterByHolidayWeb")
    public void singleFilterByHoliday(String holiday){
        homePage.chooseFilterHoliday(holiday)
                .waitEventsListReloaded();
        Assert.assertTrue(homePage.isEventsHoliday(holiday));
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
        //System.out.println("window after closing:" + driver
              //  .getWindowHandle());
        driver.switchTo().window(mainHandle);
        System.out.println("window after switching to mainWindow:" + driver
                .getWindowHandle());
    }


}
