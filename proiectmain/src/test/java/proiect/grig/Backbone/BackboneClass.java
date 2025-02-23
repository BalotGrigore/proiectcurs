package proiect.grig.Backbone;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
@SuppressWarnings("static-access")



    // mail:testgrigselenium@gmail.com
    // pass:testgrig123@test.com
    // passEmag:Testgrig123@test.com

public class BackboneClass {

WebDriver driver;
spine spine;

    
  public BackboneClass(WebDriver driver) {
  this.driver = driver;
  this.spine = new spine(driver);
  
}


/*public class FluentHelper {
    public static WebElement waitForElement(WebDriver driver, By by, int timeoutSeconds, int pollingIntervalMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMillis))
                .ignoring(Exception.class);
        return wait.until((ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(by));
    }
} */


    public void Openpage () {
        driver.get("https://www.emag.ro");
        String pagetitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL","eMAG.ro - Căutarea nu se oprește niciodată",pagetitle);
        System.out.println("Suntem pe pagina corecta");
    }
    
    
    public void FixCookies () {
        try {
            WebElement cookieButton = spine.waitForElement(driver,By.cssSelector("body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.py-2.px-0.show > div > div.col-xs-12.col-sm-5.col-md-4.col-lg-3.cookie-banner-buttons > button.btn.btn-primary.btn-block.js-accept.gtm_h76e8zjgoo"),
                    5, 2000);
            cookieButton.click();
            System.out.println("Am dat click pe Accept Cookies");
        } catch (Exception e) {
            System.out.println("Nu am avut cookies pop-up this time");
        }
    }

    
    public void NavAndClick () {
        WebElement wait = spine.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(1) > li"), 5, 2000);
        spine.assertNotNull(wait);
        WebElement ele = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(1) > li"));
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        WebElement wait2 = spine.waitForElement(driver, By.xpath("//*[text()='TV, Audio-Video & Foto']"), 5, 2000);
        assertNotNull(wait2, "The element should be present in the DOM.");
        WebElement ele2 = driver.findElement(By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-list-container > ul > li:nth-child(4)"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(ele2).perform();
        WebElement wait3 = spine.waitForElement(driver, By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)"), 5, 2000);
        assertNotNull(wait3, "The element should be present in the DOM.");
        WebElement ele3 = driver.findElement(By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)"));
        spine.clickOnElement(ele3);
        String pagetitle2 = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL pentru Televizoare","Televizoare. Cumpara televizorul potrivit. Vezi oferte - eMAG.ro",pagetitle2);
        System.out.println("Suntem pe pagina cu Televizoare");  
        
    }

    public void OfertaZilei_Navigate () {
        WebElement wait = spine.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement ofertazilei = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"));
        ofertazilei.click();
        assertTrue("Nu suntem pe pagina de Oferta Zilei", driver.getCurrentUrl().contains("https://www.emag.ro/label-campaign/oferta-zilei") );
        System.out.println("Am navigat pe Pagina Offerta Zilei");
        System.out.println("Verificamm daca oferta este pentru ziua de azi");
         String currentUrl = driver.getCurrentUrl();
            
            // Get current date information
            LocalDate currentDate = LocalDate.now();
            int dayOfMonth = currentDate.getDayOfMonth();
            int year = currentDate.getYear();
            String romanianMonth = getRomanianMonth(currentDate.getMonthValue());
            String romanianWeekday = getRomanianDayOfWeek(currentDate.getDayOfWeek());
            
            // Convert numerical values to strings
            String expectedDay = String.valueOf(dayOfMonth);
            String expectedYear = String.valueOf(year);

            // Assertion for Day
            if (!currentUrl.contains(expectedDay)) {
                throw new AssertionError("Offer is for the wrong day. Expected day: " + expectedDay);
            }
            // Assertion for Month
            if (!currentUrl.contains(romanianMonth)) {
                throw new AssertionError("Offer is for the wrong month. Expected month: " + romanianMonth);
            }
            // Assertion for Year
            if (!currentUrl.contains(expectedYear)) {
                throw new AssertionError("Offer is for the wrong year. Expected year: " + expectedYear);
            }
            // Assertion for Weekday
            if (!currentUrl.contains(romanianWeekday)) {
                throw new AssertionError("Offer is for the wrong day of the week. Expected weekday: " + romanianWeekday);
            }
            
            System.out.println("All assertions passed: The offer URL matches the current date details.");
    
    }

    // Convert month number to its Romanian name
    private static String getRomanianMonth(int month) {
        switch (month) {
            case 1:  return "ianuarie";
            case 2:  return "februarie";
            case 3:  return "martie";
            case 4:  return "aprilie";
            case 5:  return "mai";
            case 6:  return "iunie";
            case 7:  return "iulie";
            case 8:  return "august";
            case 9:  return "septembrie";
            case 10: return "octombrie";
            case 11: return "noiembrie";
            case 12: return "decembrie";
            default: return "";
        }
    }
    
    // Convert DayOfWeek to its Romanian name
    private static String getRomanianDayOfWeek(DayOfWeek day) {
        switch (day) {
            case MONDAY:    return "luni";
            case TUESDAY:   return "marti"; // or "marți" if your encoding supports it
            case WEDNESDAY: return "miercuri";
            case THURSDAY:  return "joi";
            case FRIDAY:    return "vineri";
            case SATURDAY:  return "sambata"; // or "sâmbătă"
            case SUNDAY:    return "duminica";
            default:        return "";
        }
    }

    public void Resigilate_navigate () {
        WebElement wait = spine.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement resigilate = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"));
        resigilate.click(); 
        WebElement wait2 = spine.waitForElement(driver, By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"), 5, 2000);
        assertNotNull(wait2, "The element should be present in the DOM.");
        WebElement ele = driver.findElement(By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"));
        spine.clickOnElement(ele);       
        WebElement wait3 = spine.waitForElement(driver, By.cssSelector("#main-container > section:nth-child(1) > div > div.clearfix.pb-7 > div.page-container > div:nth-child(1) > div.listing-panel-heading.hidden-xs > div > div.listing-page-title.js-head-title > h1"),5,2000);
        assertNotNull(wait3, "The element should be present in the DOM.");
        String ResigilateTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei", "Audio-Foto-Video, Gaming si Birotica Disponibilitate Resigilate eMAG.ro", ResigilateTitle);
    }
   
    public void Genius () {
        WebElement wait = spine.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement Genius = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"));
        spine.clickOnElement(Genius);
        
        WebElement shadowHost = driver.findElement(By.tagName("emag-genius"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        String GeniusTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei", "Genius: livrare gratuită și oferte exclusive pe eMAG, Tazz, Fashion Days și Freshful - eMAG.ro", GeniusTitle);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        FixCookies ();

        WebElement elm = shadowRoot.findElement(By.cssSelector("div > div > div.main > div > div:nth-child(5) > button"));
        spine.clickOnElement(elm);
        WebElement elm2 = shadowRoot.findElement(By.cssSelector("div > div > div.main > div > div:nth-child(19) > div > button"));
        spine.clickOnElement(elm2);
        WebElement wait3 = spine.waitForElement(driver, By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"), 5, 2000);
        WebElement elm3 = driver.findElement(By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"));
        assertEquals("Nu suntem pe login page redirect from genius page", "Nu ai cont? Nu-ți face griji!\nPoți crea unul în pasul următor." , elm3.getText());
       
        
}
}


