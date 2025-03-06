package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import proiect.grig.Backbone.BackboneClass;

public class AllTestScenario {

    public WebDriver driver = new ChromeDriver();
    public BackboneClass CallBackbone = new BackboneClass(driver);

    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

    }

    @Given("the user navigates to emag.ro page")
    public void placeholder1() {
        CallBackbone.Openpage();
    }

    @When("cookies appear we will accept")
    public void placeholder2() {
        CallBackbone.FixCookies();
    }

    @Then("we will navigate to Televisoare page")
    public void placeholder3() {
        CallBackbone.NavAndClick();
    }

    @Then("we will navigate to and check that Oferata Zilei is for current day")
    public void placeholder4() {
        CallBackbone.OfertaZilei_Navigate();
    }

    @Then("we will navigate to and check Resigilate page")
    public void placeholder5() {
        CallBackbone.Resigilate_navigate();
    }

    @Then("we will navigate to and check Genius page")
    public void placeholder6() {
        CallBackbone.Genius();
    }

    @Then("we will navigate to and check Genius Deals page")
    public void placeholder7() {
        CallBackbone.GeniusDeals();
    }

    @Then("we will navigate to and check EasyBuyBack page")
    public void placeholder8() {
        CallBackbone.EasyBuyBack();
    }

    @Then("we will navigate to and check Ofertele Emag page")
    public void placeholder9() {
        CallBackbone.OferteleEmag();
    }

    @Then("we will add a products to favorite and try to checkout a product")
    public void placeholder10() {
        CallBackbone.FavProduct();
        driver.quit();
    }

}
