package proiect.grig.Backbone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.function.Function;

public class spine {
    WebDriver driver;

     public spine(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickOnElement(WebElement element)
    {
        element.click();
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

    public void assertNotNull(WebElement element)
    {
        if (element == null)
        {
            throw new AssertionError("The element should be present in the DOM.");
        }       
    }
}