package ru.stqa.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class TestListenerWeb {
    EventFiringWebDriver driver;

    public static class MyListener extends AbstractWebDriverEventListener{
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            //super.beforeFindBy(by, element, driver);
            System.out.println("Search element by locator: " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            //super.afterFindBy(by, element, driver);
            System.out.println("Element by locator " + by + " was found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            //super.onException(throwable, driver);
            System.out.println("Exception: " + throwable);
        }
    }

    @Test
    public void workWithGoogle(){
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
                .sendKeys("text");
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
