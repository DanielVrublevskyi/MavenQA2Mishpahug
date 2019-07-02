package ru.stqa.selenium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.IntroWindowHelper;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;
  public static final String LOGIN = "marinaA";
  public static final String PASSWORD = "marina1!";
  public static final String FILTER_HOLIDAY_SHABBAT = "Shabbat";
  public static final String FILTER_HOLIDAY_FOOD = "Kosher";
  public static final String FILTER_HOLIDAY_CONF = "Irreligious";

  protected WebDriver driver;
  protected IntroWindowHelper introWindow;

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {

    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver.get(baseUrl);
    introWindow = PageFactory.initElements(driver, IntroWindowHelper.class);
    introWindow.waitUntilPageIsLoaded().closeWindow();
  }

  @AfterMethod
  public void tearDownMethod() {
    driver.quit();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
