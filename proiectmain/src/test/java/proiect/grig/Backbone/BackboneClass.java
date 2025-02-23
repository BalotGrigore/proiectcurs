package proiect.grig.Backbone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.Assert.assertFalse;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Duration;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public void Openpage() {
        driver.get("https://www.emag.ro");
        String pagetitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL", "eMAG.ro - Căutarea nu se oprește niciodată",
                pagetitle);
        System.out.println("Suntem pe pagina corecta");
    }

    public void FixCookies() {
        try {
            WebElement cookieButton = spine.waitForElement(driver, By.cssSelector(
                    "body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.py-2.px-0.show > div > div.col-xs-12.col-sm-5.col-md-4.col-lg-3.cookie-banner-buttons > button.btn.btn-primary.btn-block.js-accept.gtm_h76e8zjgoo"),
                    5, 2000);
            cookieButton.click();
            System.out.println("Am dat click pe Accept Cookies");
        } catch (Exception e) {
            System.out.println("Nu am avut cookies pop-up this time");
        }
    }

    // Helper method for NavAndClick to wait for and move to an element
    public WebElement waitForAndMoveToElement(By locator, int timeoutSeconds, int pollingMillis) {
        WebElement element = spine.waitForElement(driver, locator, timeoutSeconds, pollingMillis);
        spine.assertNotNull(element);
        new Actions(driver).moveToElement(element).perform();
        return element;
    }

    public void NavAndClick() {
        // Step 1: Wait for and move to the first element
        waitForAndMoveToElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(1) > li"), 5, 2000);

        // Step 2: Wait for the next element and move to it
        waitForAndMoveToElement(By.xpath("//*[text()='TV, Audio-Video & Foto']"), 5, 2000);

        // Step 3: Wait for the next element and click on it
        WebElement ele3 = waitForAndMoveToElement(
                By.cssSelector(
                        "body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)"),
                5, 2000);

        spine.clickOnElement(ele3);

        // Step 4: Validate the page title
        String pageTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL pentru Televizoare", 
                "Televizoare. Cumpara televizorul potrivit. Vezi oferte - eMAG.ro", pageTitle);
        System.out.println("Suntem pe pagina cu Televizoare");
    }

    public void OfertaZilei_Navigate() {
        // Wait for the "Oferta Zilei" element to be present
        WebElement wait = spine.waitForElement(driver,
                By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");

        // Click on the "Oferta Zilei" element
        WebElement ofertazilei = driver.findElement(
                By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"));
        ofertazilei.click();

        // Verify that the current URL contains the expected path
        assertTrue("Nu suntem pe pagina de Oferta Zilei",
                driver.getCurrentUrl().contains("https://www.emag.ro/label-campaign/oferta-zilei"));
        System.out.println("Am navigat pe Pagina Oferta Zilei");
        System.out.println("Verificăm dacă oferta este pentru ziua de azi");

        // Get the current URL
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

    // Convert month number to its Romanian name without diacritics
    private String getRomanianMonth(int month) {
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
    private String getRomanianDayOfWeek(DayOfWeek day) {
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

    public void Resigilate_navigate() {
        // Wait for the "Resigilate" element to be present amd go to new page
        WebElement wait = spine.waitForElement(driver,
                By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement resigilate = driver
                .findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"));
        resigilate.click();
        // we should be on https://www.emag.ro/resigilate?ref=hdr_resigilate page
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        WebElement wait2 = spine.waitForElement(driver, By.cssSelector(
                "#main-container > section.page-section.page-listing-v2 > div > div.clearfix.pb-7 > div.page-container > div:nth-child(1) > div.listing-panel-heading.hidden-xs > div > div.listing-page-title.js-head-title"),
                5, 2000);
        assertNotNull(wait2, "The element should be present in the DOM.");
        WebElement textResigilate = driver.findElement(By.cssSelector("#main-container > section.page-section.page-listing-v2 > div > div.page-header > div.breadcrumb-outer-holder.breadcrumb-lighten.js-breadcrumb-holder > div > ol > li"));
        assertEquals("The element should be present in the DOM.", textResigilate.getText(), "Produse resigilate");
        assertEquals("Nu suntem pe pagina de Oferta Zilei","Produse resigilate - eMAG.ro", driver.getTitle());
    }



    public void Genius() {
        WebElement wait = spine.waitForElement(driver,
                By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement Genius = driver
                .findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"));
        spine.clickOnElement(Genius);
        WebElement shadowHost = driver.findElement(By.tagName("emag-genius"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        String GeniusTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei",
                "Genius: livrare gratuită și oferte exclusive pe eMAG, Tazz, Fashion Days și Freshful - eMAG.ro",
                GeniusTitle);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      

        WebElement elm = shadowRoot
                .findElement(By.cssSelector("div > div > div.main > div > div:nth-child(5) > button"));
        spine.clickOnElement(elm);
        
        FixCookies();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement elm2 = shadowRoot
                .findElement(By.cssSelector("div > div > div.main > div > div:nth-child(19) > div > button"));
        spine.clickOnElement(elm2);
        WebElement wait3 = spine.waitForElement(driver, By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"), 5,
                2000);
        spine.assertNotNull(wait3);
        WebElement elm3 = driver.findElement(By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"));
        assertEquals("Nu suntem pe login page redirect from genius page",
                "Nu ai cont? Nu-ți face griji!\nPoți crea unul în pasul următor.", elm3.getText());

    }

    public void GeniusDeals() {
        WebElement wait = spine.waitForElement(driver,
                By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(4) > a"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(4) > a")).click();
        assertTrue("Nu suntem pe pagina corecta: Genius Deals ",driver.getCurrentUrl().contains("https://www.emag.ro/label-campaign/genius-deals"));
    }

    public void EasyBuyBack() {
        try {
            WebElement wait = spine.waitForElement(driver,
            By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(5) > a"), 5, 2000);
        
            if (driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(5) > a")).isDisplayed()) {
                assertNotNull(wait, "The element should be present in the DOM.");
                driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(5) > a")).click();;
            }
            else {
            driver.findElement(By.cssSelector("#navbar-aux-dropdown")).click();
            driver.findElement(By.xpath("(//*[text()=\"Easy BuyBack \"])[2]")).click();
            }
        } catch (Exception e) {
            System.out.println("Nu am putut da click pe Easy BuyBack");
        }      
        assertTrue("Nu suntem pe pagina corecta: Easy Buy Back ",driver.getCurrentUrl().contains("https://www.emag.ro/lps/emag-flip-buyback"));  
    }

    public void OferteleEmag() {
        try {
            WebElement wait = spine.waitForElement(driver,
            By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(6) > a"), 5, 2000);
        
            if (driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(6) > a")).isDisplayed()) {
                assertNotNull(wait, "The element should be present in the DOM.");
                driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(6) > a")).click();;
            }
            else {
            driver.findElement(By.cssSelector("#navbar-aux-dropdown")).click();
            driver.findElement(By.xpath("(//*[text()=\"Ofertele eMAG\"])[2]")).click();
            }
        } catch (Exception e) {
            System.out.println("Nu am putut da click pe Ofertele eMAG");
        }
        assertTrue("Nu suntem pe pagina corecta: Ofertele eMAG ",driver.getCurrentUrl().contains("https://www.emag.ro/nav/deals"));
        
    }

    public void FavProduct() {
        WebElement wait = spine.waitForElement(driver, By.cssSelector("#my_wishlist > i"), 5, 2000);
        assertNotNull(wait, "The element should be present in the DOM.");
        WebElement ele = waitForAndMoveToElement(
                By.id(
                        "my_wishlist"),
                5, 2000);
        assertNotNull(ele, "The element should be present in the DOM.");
        assertTrue("Nu am dat hover corect", driver.findElement(By.xpath("//*[text()=\"Adauga produsele preferate la Favorite.\"]")).getText().contains("Adauga produsele preferate la Favorite."));
        //Am asteptat sa vedem butonul de favorite, am dat hover si am verifica ca nu avem nici un produs adaugat
        driver.findElement(By.xpath("//*[contains(@class, 'add-to-favorites btn')]")).click();
        WebElement wait2 = spine.waitForElement(driver, By.cssSelector("body > div.ns-wrap-top-right > div > div > div > div > div.table-cell.col-xs-9"), 5, 500);
        wait2.getText().contains("Produsul a fost adaugat la Favorite"); // notificare de pe site canda daugi produsul la favorite
        System.out.println("Produsul a fost adaugat la Favorite");
        assertTrue(driver.findElement(By.xpath("//*[text()=\"1\"]")).getText().contains("1")); //s-a incrementat counterul cu 1 pentru butonul de favorite
        // Am dat click pe butonul de favorite si am verificat ca produsul a fost adaugat cu succes la favorite prin notificarile de pe site si am incermentat numarul de produse favorite cu 1
        driver.findElement(By.xpath("(//*[contains(@class, 'add-to-favorites btn')])[2]")).click();
        assertTrue(driver.findElement(By.xpath("//*[text()=\"2\"]")).getText().contains("2"));
        // Am adaugat un al doilea produs la favorite si am verificat ca numarul de produse favorite a crescut cu 1 la un total de 2
        System.out.println("Am adaugat 2 produse la favorite");
        
        WebElement ele2 = waitForAndMoveToElement(
            By.id(
                    "my_wishlist"),
            5, 2000);
        assertNotNull(ele2, "The element should be present in the DOM.");
        
        driver.findElement(By.xpath("//a[@href='/favorites?ref=ua_favorites']")).click();
        WebElement waitfav = spine.waitForElement(driver,
                By.xpath("//*[text()=\"Sterge\"]"), 5, 2000);
        assertNotNull(waitfav, "The element should be present in the DOM.");
        driver.findElement(By.xpath("//*[text()=\"Sterge\"]")).click();
        System.out.println("Am sters un produs din favorite");
        System.out.println("apasam submit la produsul 2");
        WebElement waitfav2 = spine.waitForElement(driver,
                By.xpath("//*[@id=\"list-of-favorites\"]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/form/button/i"), 5, 2000);
        assertNotNull(waitfav2, "The element should be present in the DOM.");
        driver.findElement(By.xpath("//*[@id=\"list-of-favorites\"]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/div[2]/form/button/i")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement waitfav3 = spine.waitForElement(driver,
                By.xpath("//*[@class='d-none d-sm-block']"), 5, 2000);
        assertNotNull(waitfav3, "The element should be present in the DOM.");
        assertTrue(driver.findElement(By.xpath("//*[@class='d-none d-sm-block']")).getText().contains("Produsul a fost adaugat in cos"));
        WebElement waitfav4 = spine.waitForElement(driver,
                By.xpath("//*[text()=\"Vezi detalii cos\"]"), 5, 2000);
        assertNotNull(waitfav4, "The element should be present in the DOM.");
        driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm btn-block']")).click();
        WebElement waitfav5 = spine.waitForElement(driver,
                By.xpath("//*[text()=\"Coșul meu\"]"), 5, 2000);
        assertNotNull(waitfav5, "The element should be present in the DOM.");
        assertTrue(driver.findElement(By.xpath("//*[text()=\"Coșul meu\"]")).getText().contains("Coșul meu"));
        WebElement waitfav6 = spine.waitForElement(driver,
                By.cssSelector("#cart-products > div > div.col-md-4.col-lg-3.main-cart-container-right > div.placeholder.order-summary-container > div > div.order-summary-actions"), 5, 2000);
        assertNotNull(waitfav6, "The element should be present in the DOM.");
        waitfav6.click();
        System.out.println("Avem un produs in cosi si incercam sa il cumparam");
        WebElement waitfav7 = spine.waitForElement(driver,
                By.xpath("//*[text()=\"Introdu adresa de email\"]"), 5, 2000);
        assertNotNull(waitfav7, "The element should be present in the DOM.");
        assertTrue(driver.findElement(By.xpath("//*[text()=\"Introdu adresa de email\"]")).getText().contains("Introdu adresa de email"));
        System.out.println("Am ajuns la pagina de Login");
    }
    
}
