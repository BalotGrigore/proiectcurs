package stepDefinitions;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import proiect.grig.Backbone.*;

@SuppressWarnings("static-access")

public class DedemanLoghin {

    public WebDriver driver = new ChromeDriver();    
    public Elements elements = new Elements(driver);
    public spine spineInstance = new spine(driver);

    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @Given("we navigate to Dedeman.ro page")
    public void navigateToDedeman() {
        driver.get("https://www.dedeman.ro/ro");
        assertTrue("Not on Dedeman page", (driver.getCurrentUrl().contains("https://www.dedeman.ro/ro")));
        System.out.println("We are on dedeman.ro page");
    }

    @When("cookies appear we accept them")
    public void FixCookiesDedeman() {
        spineInstance.waitForAndClickWebElement(elements.AcceptCookieDedeman, 5, 1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean isGone = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
                "#notice-cookie-block > div > div.cookie-bar-inner-content > div.default-content > div.cookie-bar-section.cookie-bar-bottom > ul > li:nth-child(2) > button")));

        assertTrue("Still have cookies", isGone);
        System.out.println("Accepted cookies");
    }

    @Then("we will start login process by pressing Contul Meniu and authenticate")
    public void StartLogin() {
        // Wait and click contul meu
        spineInstance.waitForAndClickWebElement(elements.ContulMeu, 5, 1000);
        // Wait and click authenticate
        spineInstance.waitForAndClickWebElement(elements.Authenticate, 5, 1000);
        spineInstance.waitForWebElement(driver, elements.CampEmail, 5, 1000);
        assertTrue("Nu suntem pe pagina de login",
                driver.getCurrentUrl().contains("https://www.dedeman.ro/ro/customer/account/login"));

    }

    @When("we are asked for credential to login")
    public void findcredentialfileld() {
        // We are asked to insert logoin credentials
        assertTrue(elements.textRequestCredentials.getText().contains("Introdu adresa de e-mail sau nr. de telefon"));
        assertTrue(elements.CampEmail == null || elements.CampEmail.getText().isEmpty());
        assertTrue(elements.CampParola == null || elements.CampParola.getText().isEmpty());

    }

    @Then("we fill in the credentials {string} and {string}")
    public void FillInCredentials(String username, String password) {
      // elements.CampEmail.sendKeys(username);
       //elements.CampParola.sendKeys(password);
       driver.findElement(By.id("email-input")).sendKeys(username);
       driver.findElement(By.id("password-input")).sendKeys(password);
    }

    @Then("we press continue")
    public void PressContinue() {
        spineInstance.waitForAndClickWebElement(elements.ContinuaBtnDedeman, 5, 1000);
    }

    @Then("we will succesfully reach contul meu page")
    public void ReeachUserPage() {
        spineInstance.waitForWebElement(driver, elements.ContulMeuText, 5, 1000);
        assertTrue("Nu suntem pe pagina de contul meu", elements.ContulMeuText.getText().contains("Contul meu"));
        assertEquals("https://www.dedeman.ro/ro/customer/account", driver.getCurrentUrl());
        System.out.println("Scenario Outline:  Login into dedeman.ro with pre-registered account");
        driver.quit();
    }

}
