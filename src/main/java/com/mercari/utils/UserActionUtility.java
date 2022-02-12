package com.mercari.utils;

import org.awaitility.Awaitility;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * This class will be responsible for UI Actions utility
 * @author Nirmal.Shah
 */
public class UserActionUtility {
    private static final long WAIT_TIME_ELEMENT_SECS = 5;
    private static final long WAIT_MAX_TIME_ELEMENT_SECS = 30;
    private static final long PAGE_MAX_WAIT_TIME_SECS = 30;

    /**
     * This method will return page title
     * @param driver
     * @return
     */
    public static String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    /**
     * Method to get text of webElement
     * @param element WebElement
     */
    public static String getWebElementText(WebElement element){
        if(element!=null)
            return element.getText();
        else
            throw new NullPointerException("Please set the element value");
    }

    /**
     * Method to click on webElement
     * @param element WebElement
     */
    public static void clickOnWebElement(WebElement element){
        if(element!=null)
            element.click();
        else
            throw new NullPointerException("Please set the element value");
    }

    /**
     * Method to check is element is displayed
     * @param webElement = webElement on which isDisplayed will be called.
     * @return true/false
     */
    public static boolean isElementDisplayed(WebElement webElement) throws NoSuchElementException {
        boolean isVisible;
        try {
            isVisible = webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element is not loaded");
        }
        return isVisible;
    }

    /**
     * Method to explicit wait for element to be clickable
     * @param driver webdriver object
     * @param webElement webelement object
     */
    public static void waitForElementClickable(WebDriver driver, WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_ELEMENT_SECS));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Method to explicit wait for element to be clickable
     * @param driver webdriver object
     * @param webElement webelement object
     * @param seconds long wait in seconds
     */
    public static void waitForElementClickable(WebDriver driver, WebElement webElement,long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    /**
     * Method to explicit wait for visibility of element
     * @param driver webdriver object
     * @param webElement webelement object
     */
    public static void waitForElementVisible(WebDriver driver,WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_ELEMENT_SECS));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Method to explicit wait for visibility of element
     * @param driver webdriver object
     * @param webElement webelement object
     * @param seconds long wait in seconds
     */
    public static void waitForElementVisible(WebDriver driver,WebElement webElement,long seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * Method to check is element is enabled
     * @param webElement
     * @return true/false
     */
    public static boolean isElementEnabled(WebElement webElement) throws NoSuchElementException {

        boolean isEnabled;
        try {
            isEnabled = webElement.isEnabled();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Element is not loaded/exist");
        }
        return isEnabled;
    }

    /**
     * This method is application only for Select element. max wait 30 secs for condition to be true.
     * <br> condition is to wait till select is loaded with options list </br>
     * @param webElement on which user want to perform async wait
     */
    public static void AsyncOpsWait(WebElement webElement) {
        Select SelectOption = new Select(webElement);
        Awaitility.await()
                .pollInterval(WAIT_TIME_ELEMENT_SECS, TimeUnit.SECONDS)
                .atMost(WAIT_MAX_TIME_ELEMENT_SECS, TimeUnit.SECONDS)
                .until(() -> SelectOption.getOptions().size() > 1);
    }

    /**
     * Method to set text for webelement
     * @param webElement element like textfield
     * @param text text to be set in element
     */
    public static void enterText(WebElement webElement,String text) {
        webElement.sendKeys(text);
    }

    /**
     * Method to clear text
     * @param webElement
     */
    public static void clearText(WebElement webElement) {
        webElement.clear();
    }

    /**
     * Method to capture current screenshots
     * @throws InterruptedException
     */
    public static String captureCurrentScreenshot(WebDriver driver) throws InterruptedException {
        String srcFile = "";
        try {
            srcFile = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srcFile;
    }

    /**
     * Method to scroll down on page
     * @param driver object
     */
    public static void scrollDown(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Method to get userData from local
     *
     * @return userData
     */
    public static String getUserDataFromLocalStorage(WebDriver driver) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", "user"));
    }

    /**
     * Method to switch window
     *
     * @return windowHandleAfterOpenNewTab
     * @throws InterruptedException
     */
    public static ArrayList<String> openNewTab(WebDriver driver) throws InterruptedException {

        ArrayList<String> windowHandleBeforeOpenNewTab = new ArrayList<String>(driver.getWindowHandles());
        ((JavascriptExecutor) driver).executeScript("window.open();");
        ArrayList<String> windowHandleAfterOpenNewTab = new ArrayList<String>(driver.getWindowHandles());

        /*if (windowHandleBeforeOpenNewTab.size() == windowHandleAfterOpenNewTab.size()) {
            new TestInitializer();
            TestInitializer.failTestCase("Unable to open new Tab");

        }*/

        driver.switchTo().window(windowHandleAfterOpenNewTab.get(windowHandleAfterOpenNewTab.size() - 1));
        return windowHandleAfterOpenNewTab;
    }

    /**
     * Method to switch to new window
     * @param driver object
     * @throws InterruptedException
     */
    public static void switchWindow(WebDriver driver) throws InterruptedException {

        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    /**
     * Switch to frame by name
     * @param driver driver object
     * @param frameName frameName
     * @throws InterruptedException
     */
    public static void switchToFrame(WebDriver driver,String frameName) throws InterruptedException {
        driver.switchTo().defaultContent();
        driver.switchTo().frame(frameName);
    }

    /**
     * Method to refresh application
     *
     * @throws InterruptedException
     */
    public static void refreshApplication(WebDriver driver,String URL) throws InterruptedException {
        driver.navigate().to(URL);
    }

    /**
     * Wait for page load
     * @param driver driver object
     */
    public static void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_MAX_WAIT_TIME_SECS));
        wait.until(pageLoadCondition);
    }
}
