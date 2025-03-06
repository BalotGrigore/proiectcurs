package proiect.grig.Backbone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.util.ArrayList;

import org.openqa.selenium.By;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.*;
import java.util.List;

@SuppressWarnings("static-access")

public class BackboneClass {

    WebDriver driver; // Instance of WebDriver
    spine spine; // Instance of spine class
    Elements elements; // Instance of Elements class

    public BackboneClass(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver
        this.spine = new spine(driver); // Initialize spine
        this.elements = new Elements(driver); // Initialize Element

    }

    public void Openpage() {
        if (driver.getCurrentUrl().contains("https://www.emag.ro")) {
            System.out.println("Suntem deja pe pagina corecta emag.ro");
            // We will check if we are already on the correct page
        } else {
            driver.get("https://www.emag.ro");
            String pagetitle = driver.getTitle();
            assertEquals("Nu suntem pe pagina corecta, check page URL", "eMAG.ro - Căutarea nu se oprește niciodată",
                    pagetitle);
            System.out.println("Am deschis pagina corecta emag.ro");
            // If we are not on the correct page we will open it and check if we are on the
            // correct page
        }
    }

    public void FixCookies() {
        try {
            if (elements.cookieButton.isDisplayed()) {
                try {
                    // Wait for the cookies to appear before clicking
                    spine.waitForElementToBeClickable(elements.cookieButton);
                    // Click on accept cokies button
                    spine.clickOnElement(elements.cookieButton);
                    System.out.println("Am dat click pe Accept Cookies");
                } catch (Exception e) {
                }
            } else {
                try {
                    Thread.sleep(2000);
                    spine.clickOnElement(elements.cookieButton);
                    System.out.println("Am dat click pe Accept Cookies dupa implicit wait");
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
        }
    }

    public void NavAndClick() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // Step 1: Wait for and move to the first element
        spine.waitForAndMoveToWebElement(elements.Produse, 5, 2000);

        // Step 2: Wait for the next element and move to it
        spine.waitForAndMoveToWebElement(elements.TVAudioVideoFoto, 5, 2000);

        // Step 3: Wait for the next element and click on it
        spine.waitForAndMoveToWebElement(elements.Televizoare, 5, 2000).click();

        // Step 4: Validate the page title
        String pageTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL pentru Televizoare",
                "Televizoare. Cumpara televizorul potrivit. Vezi oferte - eMAG.ro", pageTitle);
        System.out.println("Am navigat cu succes pe pagina cu Televizoare");
    }

    public void OfertaZilei_Navigate() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // Wait for the "Oferta Zilei" element to be present and click it
        spine.waitForAndClickWebElement(elements.OfertaZilei, 5, 2000);

        // Verify that the current URL contains the expected path
        assertTrue("Nu suntem pe pagina de Oferta Zilei",
                driver.getCurrentUrl().contains("https://www.emag.ro/label-campaign/oferta-zilei"));
        System.out.println("Am navigat pe pagina Oferta Zilei");

        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Get current date information
        LocalDate currentDate = LocalDate.now();
        int dayOfMonth = currentDate.getDayOfMonth();
        int year = currentDate.getYear();
        String romanianMonth = spine.getRomanianMonth(currentDate.getMonthValue());
        String romanianWeekday = spine.getRomanianDayOfWeek(currentDate.getDayOfWeek());

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

        // Print success message
        System.out.println("Am verificat cu succes ca Oferta Zilei este pentru ziua de azi: " + expectedDay + " "
                + romanianMonth + " " + expectedYear + " (" + romanianWeekday + ")");
    }

    public void Resigilate_navigate() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // Wait for the "Resigilate" element to be present and click it
        spine.waitForAndClickWebElement(elements.Resigilate, 5, 2000);

        // We need to switch to the new window
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

        // Wait for the element to be present on the new page
        WebElement wait2 = spine.waitForWebElement(driver, elements.ProduseResigilateBigText, 5, 2000);
        assertNotNull(wait2, "The element should be present in the DOM.");

        // Verify we are on the correct page by checking for an element and the page
        // title
        assertEquals("The element should be present in the DOM.", elements.ProduseResigilateSmallText.getText(),
                "Produse resigilate");
        assertEquals("Nu suntem pe pagina de Produse resigilate", "Produse resigilate - eMAG.ro", driver.getTitle());
        System.out.println("Suntem pe pagina de Produse resigilate");
    }

    public void Genius() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // Wait for the "Genius" element to be present and click it
        spine.waitForAndClickWebElement(elements.Genius, 5, 2000);

        // Get the shadow host element
        WebElement shadowHost = driver.findElement(By.tagName("emag-genius"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        String GeniusTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei",
                "Genius: livrare gratuită și oferte exclusive pe eMAG, Tazz, Fashion Days și Freshful - eMAG.ro",
                GeniusTitle);
        System.out.println("Am dat click pe Genius si am gasit shadow root element");

        // Wait for the page to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the "Încearcă gratuit 3 luni" button
        WebElement elm = shadowRoot
                .findElement(By.cssSelector("div > div > div.main > div > div:nth-child(5) > button"));
        spine.clickOnElement(elm);
        System.out.println("Am apasat pe primul buton Încearcă gratuit 3 luni ");

        // Wait for the page to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Scroll to and drag element to change price
        WebElement GreenScroll = shadowRoot.findElement(By.cssSelector(
                "div > div > div.main > div > div._savingsCalculator_racfl_1._savingsCalculator_1b9w7_161 > fieldset > div:nth-child(5) > label > div._savingsCalculatorInput_682cl_1._input_racfl_42 > input[type=range]"));
        spine.moveToElement(GreenScroll);
        // Get initial price and parse to double
        WebElement InitialPrice = shadowRoot.findElement(By.cssSelector(
                "div > div > div.main > div > div._savingsCalculator_racfl_1._savingsCalculator_1b9w7_161 > div._outputPrice_racfl_53 > span > span > span > span:nth-child(1)"));
        String InitialPriceValue = InitialPrice.getText();
        double InitialPriceValueDouble = Double.parseDouble(InitialPriceValue);

        // Move green slider
        spine.moveSlider(GreenScroll, -50);

        // Get modified price and parse to double
        WebElement ChangedPrice = shadowRoot.findElement(By.cssSelector(
                "div > div > div.main > div > div._savingsCalculator_racfl_1._savingsCalculator_1b9w7_161 > div._outputPrice_racfl_53 > span > span > span > span:nth-child(1)"));
        String ChangedPriceValue = ChangedPrice.getText();
        double ChangedPricePriceValueDouble = Double.parseDouble(ChangedPriceValue);

        // Compare Price
        boolean statement = InitialPriceValueDouble != ChangedPricePriceValueDouble;
        assertTrue("Price didn't change", statement);
        System.out.println("Am dat drag in stanga la Green slider si am modificat pretul");

        // Click on the "Încearcă gratuit 3 luni" button2
        WebElement elm2 = shadowRoot
                .findElement(By.cssSelector("div > div > div.main > div > div:nth-child(19) > div > button"));
        spine.clickOnElement(elm2);
        System.out.println("Am gasit si apasat pe al doilea button \"Încearcă gratuit 3 luni\"");

        // Navigate to page https://auth.emag.ro/user/login and check element to be
        // present
        WebElement wait3 = spine.waitForElement(driver, By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"), 5,
                2000);
        spine.assertNotNull(wait3);
        WebElement elm3 = driver.findElement(By.xpath("//*[text()=\"Nu ai cont? Nu-ți face griji!\"]"));
        assertEquals("Nu suntem pe login page redirect from genius page",
                "Nu ai cont? Nu-ți face griji!\nPoți crea unul în pasul următor.", elm3.getText());
        System.out.println("Am ajuns pe pagina de user login de la emag");

    }

    public void GeniusDeals() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // Wait for the "GeniusDeals" element to be visible and click it
        spine.waitForAndClickWebElement(elements.GeniusDeals, 5, 2000);

        String url = driver.getCurrentUrl();
        assertTrue("Nu suntem pe pagina corecta: Genius Deals ",
                driver.getCurrentUrl().contains("https://www.emag.ro/label-campaign/genius-deals"));
        System.out.println("Suntem pe pagina de Genius Deals");

        // Regular expression to match numbers
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(url);

        // Store extracted numbers
        List<Integer> numbers = new ArrayList<>();

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        // Get current date and offer period
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int dayOfMonth = currentDate.getDayOfMonth();
        int inceputoferta = numbers.get(0);
        int sfarsitoferta = numbers.get(1);

        //Validate offer year is for current year
        boolean conditieAn = numbers.get(2) == year;
        assertTrue("Oferta nu este pentru anul current", conditieAn);

        // Validate current day is part of the offer
        boolean conditie1 = (dayOfMonth >= inceputoferta && dayOfMonth <= sfarsitoferta);
        assertTrue("Ziua nu este in oferta", conditie1);

        // Get month
        String romanianMonth = spine.getRomanianMonth(currentDate.getMonthValue());

        // Assertion for Month and current month offer in url
        if (!url.contains(romanianMonth)) {
            throw new AssertionError("Offer is for the wrong month. Expected month: " + romanianMonth);
        }

        System.out.println("Oferta Emag este pe perioada " + numbers.get(0) + " - " + numbers.get(1) + " , "
                + romanianMonth + " si azi " + dayOfMonth + " este in oferta");

    }

    public void EasyBuyBack() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        // For presentation to check first IF
        //driver.manage().window().maximize();

        try {
            // We check to see if the element is visible in the main menu without dropdown
            // and also act as a pageLoadre check
            WebElement wait = spine.waitForWebElement(driver, elements.EasyBuyBack, 5, 2000);

            // If the EasyBuyBack element is displayed we will click it
            if (elements.EasyBuyBack.isDisplayed()) {
                assertNotNull(wait, "The element should be present in the DOM.");
                spine.clickOnElement(elements.EasyBuyBack);
                System.out.println("Am dat click pe Easy BuyBack din main menu");
                // We need an assertion for this case to validate the click
                assertTrue("Nu suntem pe pagina corecta: Easy Buy Back ",
                driver.getCurrentUrl().contains("https://www.emag.ro/lps/emag-flip-buyback"));
                
                // Else we will try dropdown method
            } else {
                handleDropdownClick();
            }
        } catch (Exception e) {
            System.out.println("Elementul EasyBuyBack nu a fost gasit in timpul alocat. Incercam cu dropdown.");
            handleDropdownClick();
        }
    }

    public void handleDropdownClick() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        try {
            spine.clickOnElement(elements.VeziMaiMult); // open dropdown menu

            // During testing we discovered that if navigating from specific pages this
            // selector will change so we have 2 version to click on it
            // We will check for the first version of selector being displayed and click it
            if (elements.EasyBuyBackDropdown1.isDisplayed()) {
                spine.clickOnElement(elements.EasyBuyBackDropdown1);
                System.out.println("Am dat click pe Easy BuyBack cu dropdown ver1");

                // If the first option will fail then
                // We will use the second selector and click it
            } else {
                spine.clickOnElement(elements.EasyBuyBackDropdown2);
                System.out.println("Am dat click pe Easy BuyBack cu dropdown ver2");
            }
        }

        // If everything fails and we can't click on the button in any way we throw this
        // exception with specific error message
        catch (Exception e) {
            System.out.println("Nu am putut da click pe Easy BuyBack");
        }

        // A final validation we arrived on the correct page
        assertTrue("Nu suntem pe pagina corecta: Easy Buy Back ",
                driver.getCurrentUrl().contains("https://www.emag.ro/lps/emag-flip-buyback"));
    }

    public void OferteleEmag() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        try {
            // Wait for the "Ofertele eMAG" element to be present and click it
            spine.waitForAndClickWebElement(elements.OferteleEmag, 5, 2000);
            

        } catch (Exception e) {
            // If clicking the main menu button fails, attempt to use the dropdown
            System.out.println("Nu am putut da click pe Ofertele eMAG, incercam cu Dropdown");
        }

        try {
            // Click on the "Vezi Mai Mult" button to expand the dropdown menu
            
            spine.waitForAndClickWebElement(elements.VeziMaiMult, 5, 2000);

            // Check if the first dropdown version of "Ofertele eMAG" is visible
            if (elements.OferteleEmagDropdown.isDisplayed()) {
                spine.clickOnElement(elements.OferteleEmagDropdown);
                System.out.println("Am dat click pe OferteleEmag cu dropdown ver1");

            } else {
                // If the first dropdown option is not available, click on the second version
                spine.clickOnElement(elements.OferteleEmagDropdown2);
                System.out.println("Am dat click pe OferteleEmag cu dropdown ver2");
            }

        } catch (Exception e) {
            // Handle the failure of clicking the dropdown options
            System.out.println("Nu am putut da click pe Ofertele eMAG din dropdown");
        }

        // Validate that the user is redirected to the correct "Ofertele eMAG" page
        assertTrue("Nu suntem pe pagina corecta: Ofertele eMAG ",
                driver.getCurrentUrl().contains("https://www.emag.ro/nav/deals"));
        System.out.println("Suntem pe pagina Ofertele eMAG");
    }

    public void OferteleEmag2() {
        // When runing only this method/test alone we will make sure we have
        // pre-requesite
        Openpage();
        FixCookies();

        
        try {
            // Wait for the "Ofertele eMAG" element to be present and click it
            spine.waitForAndClickWebElement(elements.OferteleEmag, 5, 2000);

        } catch (Exception e) {
            // If clicking the main menu button fails, attempt to use the dropdown
            System.out.println("Nu am putut da click pe Ofertele eMAG, incercam cu Dropdown");
        }

        try {
            // Click on the "Vezi Mai Mult" button to expand the dropdown menu            
            spine.waitForAndClickWebElement(elements.VeziMaiMult, 5, 2000);
            // Click on dropdown button for Ofertele eMAG
            spine.waitForAndClickWebElement(elements.JustUseCss, 5, 2000);
            System.out.println("Am dat click pe OferteleEmag cu dropdown");

        } catch (Exception e) {
            // Handle the failure of clicking the dropdown options
            System.out.println("Nu am putut da click pe Ofertele eMAG din dropdown");
        }

        // Validate that the user is redirected to the correct "Ofertele eMAG" page
        assertTrue("Nu suntem pe pagina corecta: Ofertele eMAG ",
                driver.getCurrentUrl().contains("https://www.emag.ro/nav/deals"));
        System.out.println("Suntem pe pagina Ofertele eMAG");
    }    
        
     public void FavProduct() {

        // We will naviagte or mmake sure we are on the emag.ro page
        Openpage();
        FixCookies();

        // Check if we are on the Televizoare page or navigate there if not the case
        if (driver.getTitle().contains("Televizoare. Cumpara televizorul potrivit. Vezi oferte - eMAG.ro")) {
            System.out.println("Suntem deja pe pagina de televizoare pe pagina cu Televizoare");
        } else {
            NavAndClick();
        }

        // Wait and hover over favorite button and checked that it's empty
        spine.waitForAndMoveToWebElement(elements.FavoriteButton,
                5, 2000);
        spine.waitForAndMoveToWebElement(elements.TextFavoriteIsEmpty,
                5, 2000);
        assertTrue("Nu am dat hover corect",
                elements.TextFavoriteIsEmpty.getText().contains("Adauga produsele preferate la Favorite."));

        // Add first product on page product to favorite by pressing on the favorite
        // button of that product
        spine.clickOnElement(elements.ProductFavButton);

        // Check notification that product was added to favorite
        WebElement wait2 = spine.waitForWebElement(driver, elements.AddedToFavNotification, 5,
                500);
        wait2.getText().contains("Produsul a fost adaugat la Favorite");
        System.out.println("Produsul a fost adaugat la Favorite");

        // Favorite counter was incremented by 1
        assertTrue(elements.FavBtnIncremented1.getText().contains("1"));
        System.out.println("Counter produse favorite este 1");

        // Add second product to favorite
        spine.clickOnElement(elements.ProductFavButton2);
        System.out.println("Am adaugat 2 produse la favorite");

        // Favorite counter was incremented by 1 and now is 2
        assertTrue(elements.FavBtnIncremented2.getText().contains("2"));
        System.out.println("Counter produse favorite este 2");

        // Wait and hover over favorite button
        spine.waitForAndMoveToWebElement(elements.FavoriteButton,
                5, 2000);

        // Clickon button "Vezi toate produsele din favorite"
        elements.SeeFavProducts.click();

        // Wait for element and click on it to delete a product from favorite
        spine.waitForWebElement(driver, elements.DeleteProductFromFav, 5, 2000);

        // Delete a product from the list
        spine.clickOnElement(elements.DeleteProductFromFav);
        System.out.println("Am sters un produs din favorite");
        System.out.println("Aduga produsul ramas in cos");

       // Wait for Add to cart button to be vislible and click on it
        spine.waitForAndClickWebElement(elements.AddToCart, 5, 2000);
        System.out.println("Am dat click pe butonul de adugat produsul in cos");

        // Wait for element containing specific text "Produsul a fost adaugat in cos"
        spine.waitForWebElement(driver, elements.ProductAddedToCartText, 5, 2000);
        assertTrue(elements.ProductAddedToCartText.getText().contains("Produsul a fost adaugat in cos"));

        // Product was added to cart success message
        System.out.println("Produsul a fost adaugat in cos");

        // Wait for Vezi detali Cos text and click on Vezi detali Cos button
        spine.waitForWebElement(driver, elements.VeziDetaliiCosText, 5, 2000);
        spine.clickOnElement(elements.VeziDetaliiCosButton);
        
        // Success message
        System.out.println("Am dat click pe Vezi detalii cos");

        // Validat we are on the order page
        spine.waitForWebElement(driver, elements.CosulMeuText, 5, 2000);
        assertTrue(elements.CosulMeuText.getText().contains("Coșul meu"));
        System.out.println("Am ajuns la pagina de cos");

        // Press on continue button and try to ckeckout
        System.out.println("Incercam sa apasam pe butonul de continua");
        spine.waitForAndClickWebElement(elements.ContinuaBtn, 5, 2000);

        // We are redirected to the register page because we are not logged in the site
        spine.waitForWebElement(driver, elements.LoginPageText, 5, 2000);
        assertTrue(elements.LoginPageText.getText().contains("Introdu adresa de email"));
        System.out.println("Am ajuns la pagina de Login");
    }
}
