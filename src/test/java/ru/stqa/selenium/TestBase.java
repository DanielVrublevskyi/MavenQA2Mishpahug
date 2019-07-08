package ru.stqa.selenium;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.common.io.Files;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.IntroWindowHelper;
import ru.stqa.selenium.util.LogLog4j;

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
  public static Logger Log = Logger.getLogger(LogLog4j.class.getName());
  public static LogLog4j log = new LogLog4j();


  public EventFiringWebDriver driver;
  public IntroWindowHelper introWindow;

  public static class MyListener extends
          AbstractWebDriverEventListener {
    @Override
    public void beforeFindBy(By by, WebElement element,
                             WebDriver driver) {
      //super.beforeFindBy(by, element, driver);
      Log.info("Search element by:" + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element,
                            WebDriver driver) {
      //super.afterFindBy(by, element, driver);
      Log.info("element " + by + " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
      //super.onException(throwable, driver);
      Log.info("Exception: " + throwable);
      File tmp = ((TakesScreenshot) driver)
              .getScreenshotAs(OutputType.FILE);
      File screen = new File("screen" + System
              .currentTimeMillis() + ".png");
      //System.out.println("screen name - " );
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

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

    driver = new EventFiringWebDriver(WebDriverPool
            .DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
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
