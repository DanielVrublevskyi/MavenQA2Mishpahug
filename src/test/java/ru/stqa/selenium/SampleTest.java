package ru.stqa.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SampleTest extends TestBase {

Alert alert;

/*
  @Test
  public void alertVerfication() throws InterruptedException {
    driver.get("file:///E:/Marina/Tel%20Ran/QAHaifa-2/3_Selenium%20Java/AlertsDemo.html");
    List<WebElement> buttons = driver.findElements(By.tagName("button"));
    System.out.println("button - " + buttons.get(0).getText());
    buttons.get(0).click();
    alert = driver.switchTo().alert();
    System.out.println("Alert#1 text: " + alert.getText());
    Thread.sleep(2000);
    alert.accept();
    Thread.sleep(2000);
    buttons.get(1).click();
    alert = driver.switchTo().alert();
    System.out.println("Alert#2 text: " + alert.getText());
    Thread.sleep(2000);
    alert.accept();
    Thread.sleep(2000);
    buttons.get(1).click();
    alert = driver.switchTo().alert();
    Thread.sleep(2000);
    alert.dismiss();
    buttons.get(2).click();
    alert = driver.switchTo().alert();
    Thread.sleep(2000);
    alert.sendKeys("Moshe");
    Thread.sleep(2000);
    alert.accept();
    buttons.get(2).click();
    alert = driver.switchTo().alert();
    alert.sendKeys("David");
    Thread.sleep(2000);
    alert.dismiss();


    Thread.sleep(5000);

  }*/
}
