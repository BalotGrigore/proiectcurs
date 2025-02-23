package proiect.grig.Backbone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    
}
