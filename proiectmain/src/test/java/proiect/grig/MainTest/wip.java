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
        AppTest Staticfix2 = new AppTest();
        Staticfix2.setup();
        Staticfix2.testApp();
        Staticfix2.teardown();
            } 

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
    

