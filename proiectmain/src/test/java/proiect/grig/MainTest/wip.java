package proiect.grig.MainTest;
import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import proiect.grig.Backbone.BackboneClass;


public class wip {

     public static void main( String[] args )
    {
        wip Staticfix2 = new wip();
        Staticfix2.setup();
        Staticfix2.testApp();
        Staticfix2.teardown();
            } 

    WebDriver driver;
    //public WebDriver driver = new ChromeDriver();
            
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

     

    // Test 2 incepe aici
    System.out.println("Test 4 Start");
    CallBackbone.Openpage();
    CallBackbone.FixCookies();
    CallBackbone.NavAndClick();
    CallBackbone.FavProduct();
    System.out.println("Test 4 Finished");
 // Am navigat prin meniul de produse si am accesat pagina de televizoare

    }

    
    @After
    
    public void teardown() {
        System.out.println("Au trecut toate testele, inchidem");
        driver.quit();
    }
}
    

