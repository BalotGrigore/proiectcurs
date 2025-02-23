package proiect.grig.MainTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.After;
import org.junit.Before;
import java.time.Duration;
import proiect.grig.Backbone.BackboneClass;

/**
 * Unit test for simple App.
 */
public class AppTest {
    
    public static void main( String[] args )
    {
        AppTest Staticfix = new AppTest();
        Staticfix.setup();
        try {
        Staticfix.testApp();
        }finally{
        Staticfix.teardown();
            }
        }

    //public WebDriver driver;
    public WebDriver driver = new ChromeDriver();
        
   @Before
    public void setup() {    
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--incognito");
       driver = new ChromeDriver(options);
       driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500)); 
                    
    }


    @Test
    public void testApp() {
        BackboneClass CallBackbone = new BackboneClass(driver);

      // Test 1 incepe aici   
        System.out.println("Test 1 Start");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        System.out.println("Test 1 Finished");
     // Am deschis pagina si dat click pe cookies daca apar  
     
     // Test 2 incepe aici 
        System.out.println("Test 2 Start");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        CallBackbone.NavAndClick();
        System.out.println("Test 2 Finished");
     // Am navigat prin meniul de produse si am accesat pagina de televizoare
         
     // Test 3 incepe aici 
        System.out.println("Test 3 Start");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        CallBackbone.OfertaZilei_Navigate();
        System.out.println("Test 3 Finished");
     // Am navigat catre Oferta zilei si accesat sectiunea de imbracaminte barbati

     // Test 4 incepe aici 
        System.out.println("Test 4 Start");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        CallBackbone.Resigilate_navigate();
        System.out.println("Test 4 Finished");
     // Am navigat catre Resigilate si accesat sectiunea de Audio-Foto-Video, Gaming si Birotic

     // Test 5 incepe aici 
        System.out.println("Test 5 Start");
        CallBackbone.Openpage();
        CallBackbone.FixCookies();
        CallBackbone.Genius();
        System.out.println("Test 5 Finished");
  // Am navigat catre Genius page
    }

    @After
    
    public void teardown() {
        System.out.println("Au trecut toate testele, inchidem");
        driver.quit();
    }
}
