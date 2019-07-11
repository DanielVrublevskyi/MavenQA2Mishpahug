package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class HomePageHelper extends PageBase{
    @FindBy(id = "idsignin")
    WebElement loginIcon;

    @FindBy(className = "navi-item")
    List<WebElement> iconList;

    @FindBy(id = "ihome")
    WebElement homeAuthIcon;

    @FindBy(id = "profile")
    WebElement profileIcon;

    @FindBy(name = "selectholidays")
    WebElement filterHoliday;

    @FindBy(name = "selectfood")
    WebElement filterFood;

    @FindBy(name = "selectconfession")
    WebElement filterConf;

    @FindBy(xpath = "//div[@class='itemEventInsert']")
    List<WebElement> listEvents;

    ViewEventPageHelper viewEvent;

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilPageIsLoaded(){
        log.info("---HomePageHelper: waitUntilPageIsLoaded method was started");
        log.info("Wait Until Login icon element is clickable ");
        waitUntilElementClickable(loginIcon,40);
        log.info("Wait Until all events are visible ");
        waitUntilAllElementsVisible(listEvents,40);
        log.info("HomePageHelper: waitUntilPageIsLoaded method was finished");
        return this;
    }

    public HomePageHelper openLoginPage() {
        log.info("---HomePageHelper: openLoginPage method was started");
        log.info("Click on Login icon");
        clickButton(loginIcon);
        log.info("HomePageHelper: openLoginPage method was finished");
        return this;
    }



    public boolean homeIconIsDisplayed() {
        log.info("---HomePageHelper: homeIconIsDisplayed method was started");
        log.info("Verify if Home Icon is displayed");
        return  iconList.get(0).isDisplayed();
    }

    public boolean loginIconIsDisplayed() {
        log.info("---HomePageHelper: loginIconIsDisplayed method was started");
        log.info("Verify if Login Icon is displayed");
        return iconList.get(1).isDisplayed();
    }

    public boolean registrationIconIsDisplayed() {
        log.info("---HomePageHelper: registrationIconIsDisplayed method was started");
        log.info("Verify if Registration Icon is displayed");
        return iconList.get(2).isDisplayed();
    }

    public boolean homeAuthIconIsHidden() {
        log.info("---HomePageHelper: homeAuthIconIsHidden method was started");
        log.info("Verify if Home Icon is hidden");
        return !homeAuthIcon.isDisplayed();
    }

    public boolean profileIconIsHidden() {
        log.info("---HomePageHelper: profileIconIsHidden method was started");
        log.info("Verify if Profile Icon is hidden");
        return !profileIcon.isDisplayed();
    }


    public HomePageHelper chooseFilterHoliday(String value) {
        log.info("---HomePageHelper:chooseFilterHoliday method was started, value =" + value);
        log.info("Select filter by Holiday" + value);
        Select selector = new Select(filterHoliday);
        try{
            selector.selectByValue(value);
        } catch (Exception e){
            log.info("Exception: " + e);
            throw new NoSuchElementException("" +e);
        }

        return this;
    }

    public HomePageHelper chooseFilterFood(String value) {
        log.info("---HomePageHelper:chooseFilterFood method was started, value =" + value);
        log.info("Select filter by Food" + value);
        Select selector = new Select(filterFood);
        selector.selectByValue(value);
        return this;
    }

    public HomePageHelper chooseFilterConf(String value) {
        log.info("---HomePageHelper:chooseFilterConf method was started, value =" + value);
        log.info("Select filter by Confesion" + value);
        Select selector = new Select(filterConf);
        selector.selectByValue(value);
        return this;
    }

    public HomePageHelper waitEventsListReloaded() {
        log.info("---HomePageHelper:waitEventsListReloaded method was started" );

        try{
            log.info("Try to wait until all events are loaded" );
            new WebDriverWait(driver, 20)
                    .until(ExpectedConditions.visibilityOfAllElements(listEvents));
        } catch(Exception e){
            try {
                log.info("On error try to wait 1000ms");
                Thread.sleep(1000);
                log.info("Try to exec waitUntilAllElementsVisible(listEvents,40)");
                waitUntilAllElementsVisible(listEvents,40);
            } catch (InterruptedException ex) {
                log.info("On error, see exeption");
                ex.printStackTrace();
            }
        }
        return this;
    }

    public boolean isEventsHoliday(String value) {
        log.info("---HomePageHelper:isEventsHoliday method was started, value = " + value );
        int counter = 0;
        log.info("Before the circle of searching elements in events list, counter = " + counter);
        for(WebElement element: listEvents){
            if(element.findElement(By.className("holidayItemEvents"))
                    .getText().equals(value)) counter++;
        }
        log.info("After the circle of searching  elements in events list, counter = " + counter);
        return counter == listEvents.size();
    }


    public boolean isEventsContainsPreference(String value) {
        log.info("---HomePageHelper:isEventsContainsPreference method was started, value = " + value );
        int counter = 0;
        log.info("Before the circle of searching elements in events list, counter = " + counter);
        for(WebElement element: listEvents){
            if(element.findElement(By.className("preferenceItemEvents"))
                    .getText().contains(value)) counter++;
        }
        log.info("After the circle of searching  elements in events list, counter = " + counter);
        return counter == listEvents.size();
    }

    public boolean isEventsHolidayInWindow(String value) {
        log.info("---HomePageHelper:isEventsHolidayInWindow method was started, value = " + value );
        int counter = 0;
        log.info("Before the circle of searching elements in events list, counter = " + counter);
        for(WebElement event: listEvents){
            //System.out.println("Title: " + event.findElement(By.className("divTitleItemEvents")).getText());
            if(getHolidayFromEventInWindow(event).contains(value)) counter++;
        }
        log.info("After the circle of searching  elements in events list, counter = " + counter);
        return counter == listEvents.size();
    }

    private String getHolidayFromEventInWindow(WebElement event) {
        log.info("---HomePageHelper:getHolidayFromEventInWindow method was started" );
        String mainHandle = driver.getWindowHandle();
        log.info("Get handle of the current window, mainHandle = " + mainHandle);
        log.info("Click on the link 'View More'");
        clickButton(event.findElement(By.xpath(".//span[@class= 'moreItemEvents']")));
        log.info("Wait until number of windows will be 2, call method waitUntilNumberOfWindowsToBe()");
        waitUntilNumberOfWindowsToBe(2,15);
        log.info("Switch to the new opened window, call method 'getAnotherHandle()'");
        driver.switchTo().window(getAnotherHandle(mainHandle));
        log.info("Initiate ViewEventPageHelper.class");
        viewEvent = PageFactory.initElements(driver, ViewEventPageHelper.class);
        viewEvent.waitUntilPageIsLoaded();
        String holidayBanner = viewEvent.getHolidayBanner();
        log.info("Get hoiday value from opened window, holidayBanner = " + holidayBanner);
        log.info("Close the current window");
        driver.close();
        log.info("Switch to main window");
        driver.switchTo().window(mainHandle);
        return holidayBanner;
    }


}
