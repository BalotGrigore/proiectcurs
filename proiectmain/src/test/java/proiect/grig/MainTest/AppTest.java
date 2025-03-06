package proiect.grig.MainTest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import proiect.grig.Backbone.BackboneClass;

/**
 * Unit test for simple App focusing on navigation in all the amin menu pages .
 */
public class AppTest {

    public static void main(String[] args) {
        AppTest Staticfix = new AppTest();
        Staticfix.setup();
        try {
            Staticfix.testApp();
        } finally {
            Staticfix.teardown();
        }
    }

    //public WebDriver driver;
    public WebDriver driver = new ChromeDriver();

    @BeforeMethod 
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    }

    @Test
    public void testApp() {
        BackboneClass CallBackbone = new BackboneClass(driver);

        // Incepem testul aici
        System.out.println("Test Started");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        System.out.println("Test 1 Finished");
        // Am deschis pagina si dat click pe cookies daca apar
        
        CallBackbone.NavAndClick();
        System.out.println("Test 2 Finished");
        // Am navigat prin meniul de produse si am accesat pagina de televizoare
        
        CallBackbone.OfertaZilei_Navigate();
        System.out.println("Test 3 Finished");
        // Am navigat catre Oferta zilei si am verificat ca e pentru ziua curenta

        CallBackbone.Resigilate_navigate();
        System.out.println("Test 4 Finished");
        // Am navigat catre Resigilate si am validat ca suntem pe magina corecta gasind un elelemnt de pe pagina si page title

        CallBackbone.Genius();
        System.out.println("Test 5 Finished");
        // Am navigat catre Genius page si accesat sectiunea de Gaming

        CallBackbone.GeniusDeals();
        System.out.println("Test 6 Finished");
        // Am navigat catre GeniusDeal si am verificat ca suntem pe pagina corecta
        // folosind Pageurl

        CallBackbone.EasyBuyBack();
        System.out.println("Test 7 Finished");
        // Am navigat catre EasyBuyBack si am verificat ca suntem pe pagina corecta
        // folosind Pageurl

        CallBackbone.OferteleEmag();
        System.out.println("Test 8 Finished");
        // Am navigat catre OferteleEmag si am verificat ca suntem pe pagina corecta
        // folosind Pageurl

       CallBackbone.FavProduct();
        System.out.println("Test 9 Finished");
        // Am adaugat si sters produse la favorite, ama daugat produs in cos si am facut checkout pana la pagina de login
       

    }

    @AfterMethod 

    public void teardown() {
        System.out.println("Au trecut toate testele, inchidem");
        driver.quit();
    }
}
