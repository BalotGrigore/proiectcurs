package proiect.grig.MainTest;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import proiect.grig.Backbone.BackboneClass;

public class wip {

    public static void main(String[] args) {
        wip Staticfix2 = new wip();
        Staticfix2.setup();
        Staticfix2.testApp();
        Staticfix2.teardown();
    }

    WebDriver driver;
    // public WebDriver driver = new ChromeDriver();

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @Test
    public void testApp() {
        BackboneClass CallBackbone = new BackboneClass(driver);

        //CallBackbone.OfertaZilei_Navigate();
        CallBackbone.Genius();
    }

    @AfterEach

    public void teardown() {
        System.out.println("Au trecut toate testele, inchidem");
        driver.quit();
    }
}
