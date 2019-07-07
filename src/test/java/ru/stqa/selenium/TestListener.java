package ru.stqa.selenium;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestListener {
    EventFiringWebDriver driver;

    public static class MyListener extends
            AbstractWebDriverEventListener
    {
        @Override
        public void beforeFindBy(By by, WebElement element,
                                 WebDriver driver)
        {
            //super.beforeFindBy(by, element, driver);
            System.out.println("Search element by:" + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element,
                                WebDriver driver) {
            //super.afterFindBy(by, element, driver);
            System.out.println("element " + by + " found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            //super.onException(throwable, driver);
            System.out.println("Exception: " + throwable);
            File tmp = ((TakesScreenshot)driver)
                    .getScreenshotAs(OutputType.FILE);
            File screen = new File("screen" + System
                    .currentTimeMillis()+".png");
            //System.out.println("screen name - " );
            try {
                Files.copy(tmp, screen);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testGoogle(){
      driver = new EventFiringWebDriver(new ChromeDriver());
      driver.register(new MyListener());
      driver.get("https://www.google.com/");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@name='q']")).click();
        driver.findElement(By.xpath("//input[@name='q']"))
                .sendKeys("text" );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@value ='Google Search' ]"))
                .click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

    }
}
