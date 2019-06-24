package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class PageBase {
    public WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }
    public void enterTextToField(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void clickButton(WebElement element) {
        element.click();
    }
    public void waitUntilElementClickable(By locator, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementClickable(WebElement element, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsVisible(By locator, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void cancelSignInModalWindow() {
        Robot r = null;
        try {
            r = new Robot();
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                r.keyPress(KeyEvent.VK_TAB);
            }
            r.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsVisible(By locator, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElemAttrContainsText(By locator, String attribute, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.attributeContains(locator,attribute,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElemAttrContainsText(WebElement element, String attribute, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.attributeContains(element,attribute,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElemContainsText(By locator, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.textToBePresentInElementLocated(locator,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElemContainsText(WebElement element, String value, int time){
        try {
            new WebDriverWait(driver,time)
                    .until(ExpectedConditions.textToBePresentInElement(element,value));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
