package proiect.grig.Backbone;
import static org.junit.Assert.assertEquals;
import java.time.Duration;
import org.apache.poi.sl.usermodel.Shadow;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.gargoylesoftware.htmlunit.javascript.host.dom.ShadowRoot;


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

/* @FindBy (css = "body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)" )
WebElement clickelemnt1;
public void clickOnElements () {spine.clickOnElement(clickelemnt1); } */


    public void Openpage () {
        driver.get("https://www.emag.ro");
        String pagetitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL","eMAG.ro - Căutarea nu se oprește niciodată",pagetitle);
        System.out.println("Suntem pe pagina corecta");
    }

    
    public class FluentHelper {
    public static WebElement waitForElement(WebDriver driver, By by, int timeoutSeconds, int pollingIntervalMillis) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(pollingIntervalMillis))
                .ignoring(Exception.class);
        return wait.until((ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(by));
    }
}

    public void FixCookies () {
         try {
        WebElement wait = FluentHelper.waitForElement(driver, By.cssSelector("body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.py-2.px-0.show > div > div.col-xs-12.col-sm-5.col-md-4.col-lg-3.cookie-banner-buttons > button.btn.btn-primary.btn-block.js-accept.gtm_h76e8zjgoo"), 5, 2000);
        WebElement dacaavemcookies = driver.findElement(By.cssSelector("body > div.gdpr-cookie-banner.js-gdpr-cookie-banner.py-2.px-0.show > div > div.col-xs-12.col-sm-5.col-md-4.col-lg-3.cookie-banner-buttons > button.btn.btn-primary.btn-block.js-accept.gtm_h76e8zjgoo"));
         dacaavemcookies.click();
         System.out.println("Am dat click pe Accept Cookies");
            //daca apar cookiurile dam click pe accept
    
        } catch (Exception e) {
            //afisam un mesaj cum ca butonul de cookie nu a aparut si mergem mai departe
            System.out.println("Nu am avut cookies pop-up this time");
        }
    }

    public void NavAndClick () {
        WebElement wait = FluentHelper.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(1) > li"), 5, 2000);
        WebElement ele = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(1) > li"));
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
        WebElement wait2 = FluentHelper.waitForElement(driver, By.xpath("//*[text()='TV, Audio-Video & Foto']"), 5, 2000);
        WebElement ele2 = driver.findElement(By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-list-container > ul > li:nth-child(4)"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(ele2).perform();
        WebElement wait3 = FluentHelper.waitForElement(driver, By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)"), 5, 2000);
        WebElement ele3 = driver.findElement(By.cssSelector("body > div.main-container-outer > div.megamenu-container.megamenu-always-open > div > div.megamenu-details > div:nth-child(3) > div.megamenu-details-department-items > ul > li:nth-child(1) > div.megamenu-group.collapse.megamenu-group-4078 > a:nth-child(1)"));
        spine.clickOnElement(ele3);
        String pagetitle2 = driver.getTitle();
        assertEquals("Nu suntem pe pagina corecta, check page URL pentru Televizoare","Televizoare. Cumpara televizorul potrivit. Vezi oferte - eMAG.ro",pagetitle2);
        System.out.println("Suntem pe pagina cu Televizoare");       
    }

    public void OfertaZilei_Navigate () {
        WebElement wait = FluentHelper.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"), 5, 2000);
        WebElement ofertazilei = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(1) > a"));
        ofertazilei.click();
        WebElement wait2 = FluentHelper.waitForElement(driver, By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"), 5, 2000);
        WebElement ele = driver.findElement(By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"));
        spine.clickOnElement(ele);
        WebElement wait3 = FluentHelper.waitForElement(driver, By.cssSelector("#main-container > section:nth-child(1) > div > div.clearfix.pb-7 > div.page-container > div:nth-child(1) > div.listing-panel-heading.hidden-xs > div > div.listing-page-title.js-head-title > h1"),5,2000);
        String OfertaZIleiTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei", "Imbracaminte barbati eMAG.ro", OfertaZIleiTitle);
    };

    public void Resigilate_navigate () {
        WebElement wait = FluentHelper.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"), 5, 2000);
        WebElement resigilate = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(2) > a"));
        resigilate.click(); 
        WebElement wait2 = FluentHelper.waitForElement(driver, By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"), 5, 2000);
        WebElement ele = driver.findElement(By.cssSelector("#main-container > div > div.lc-sections-landing > div:nth-child(1) > a.lc-section-title > div:nth-child(1)"));
        spine.clickOnElement(ele);       
        WebElement wait3 = FluentHelper.waitForElement(driver, By.cssSelector("#main-container > section:nth-child(1) > div > div.clearfix.pb-7 > div.page-container > div:nth-child(1) > div.listing-panel-heading.hidden-xs > div > div.listing-page-title.js-head-title > h1"),5,2000);
        String ResigilateTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei", "Audio-Foto-Video, Gaming si Birotica Disponibilitate Resigilate eMAG.ro", ResigilateTitle);
    }
   
    public void Genius () {
        WebElement wait = FluentHelper.waitForElement(driver, By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"), 5, 2000);
        WebElement Genius = driver.findElement(By.cssSelector("#auxiliary > div > div > ul:nth-child(3) > li:nth-child(3) > a"));
        spine.clickOnElement(Genius);
       
        WebElement shadowHost = driver.findElement(By.tagName("emag-genius"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
      //  WebElement elementInShadowDom = shadowRoot.findElement(By.cssSelector("div > div.c > div > div._main_b6p6p_132 > div > div:nth-child(5) > button"));
     //   elementInShadowDom.click();

     //   WebElement wait2 = FluentHelper.waitForElement(driver, By.cssSelector("div > div.c > div > div._main_b6p6p_132 > div > div:nth-child(5) > button"), 10, 2000);
        String GeniusTitle = driver.getTitle();
        assertEquals("Nu suntem pe pagina de Oferta Zilei", "Genius: livrare gratuită și oferte exclusive pe eMAG, Tazz, Fashion Days și Freshful - eMAG.ro", GeniusTitle);
        WebElement elm = shadowRoot.findElement(By.cssSelector("div > div.c > div > div._main_b6p6p_132 > div > div:nth-child(5) > button"));
        spine.clickOnElement(elm);
        WebElement elm2 = shadowRoot.findElement(By.cssSelector("div > div.c > div > div._main_b6p6p_132 > div > div:nth-child(19) > div > button"));
        spine.clickOnElement(elm2);
        WebElement wait3 = FluentHelper.waitForElement(driver, By.cssSelector("##user_login_email"), 5, 2000);
        WebElement elm3 = driver.findElement(By.cssSelector("##user_login_email"));
        elm3.getText();
        assertEquals("Nu suntem pe login page redirect from genius page", "Introdu adresa de email" , elm3);
       
        
}
}

