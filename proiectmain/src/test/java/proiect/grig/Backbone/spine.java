package proiect.grig.Backbone;

import org.testng.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.util.function.Function;

public class spine {
    WebDriver driver;

    public spine(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Assert.assertTrue(element.isDisplayed(), "Element is not visible!");
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public WebElement waitForAndMoveToElement(By locator, int timeoutSeconds, int pollingMillis) {
        WebElement element = waitForElement(driver, locator, timeoutSeconds, pollingMillis);
        assertNotNull(element);
        new Actions(driver).moveToElement(element).perform();
        return element;
    }

    public WebElement waitForAndMoveToWebElement(By WebElement, int timeoutSeconds, int pollingMillis) {
        WebElement element = waitForElement(driver, WebElement, timeoutSeconds, pollingMillis);
        assertNotNull(element);
        new Actions(driver).moveToElement(element).perform();
        return element;
    }

    public WebElement waitForAndMoveToWebElement(WebElement element, int timeoutSeconds, int pollingMillis) {
        WebElement Element = waitForWebElement(driver, element, timeoutSeconds, pollingMillis);
        assertNotNull(Element);
        new Actions(driver).moveToElement(Element).perform();
        return Element;
    }

    // Moved FluentHelper functionality here as a static method
    public static WebElement waitForElement(WebDriver driver, By by, int timeoutSeconds, int pollingIntervalMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMillis))
                .ignoring(Exception.class);
        return wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(by);
            }
        });
    }

   
    public static WebElement waitForWebElement(WebDriver driver, WebElement element, int timeoutSeconds,
            int pollingIntervalMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMillis))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementNotInteractableException.class);

        return wait.until(ExpectedConditions.visibilityOf(element)); // Wait until visible
    }

    // Moved FluentHelper functionality here as a static method
    public void assertNotNull(WebElement element) {
        if (element == null) {
            throw new AssertionError("The element should be present in the DOM.");
        }
    }

    // Helper methods for OfertaZilei_Navigate2
    // Convert month number to its Romanian name without diacritics
    public static String getRomanianMonth(int month) {
        switch (month) {
            case 1:
                return "ianuarie";
            case 2:
                return "februarie";
            case 3:
                return "martie";
            case 4:
                return "aprilie";
            case 5:
                return "mai";
            case 6:
                return "iunie";
            case 7:
                return "iulie";
            case 8:
                return "august";
            case 9:
                return "septembrie";
            case 10:
                return "octombrie";
            case 11:
                return "noiembrie";
            case 12:
                return "decembrie";
            default:
                return "";
        }
    }

    // Convert DayOfWeek to its Romanian name without diacritics
    public static String getRomanianDayOfWeek(DayOfWeek day) {
        switch (day) {
            case MONDAY:
                return "luni";
            case TUESDAY:
                return "marti";
            case WEDNESDAY:
                return "miercuri";
            case THURSDAY:
                return "joi";
            case FRIDAY:
                return "vineri";
            case SATURDAY:
                return "sambata";
            case SUNDAY:
                return "duminica";
            default:
                return "";
        }
    }

    // Helper method for Genius()
    public void moveSlider(WebElement sliderHandle, int xOffset) {
        Actions actions = new Actions(driver);
        actions.clickAndHold(sliderHandle)
                .moveByOffset(xOffset, 0)
                .release()
                .perform();
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}