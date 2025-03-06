package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"}, 
    glue = {"stepDefinitions"},
    plugin = {
        "pretty", // Prints Gherkin steps in the console
        "html:target/cucumber-reports.html", // Generates an HTML report
        "json:target/cucumber.json", // Generates a JSON report
        "junit:target/cucumber.xml" // Generates a JUnit XML report
    },
    monochrome = true, // Improves readability of console output
    tags = "@AllTestsScenario"
    // Tag Options:
    // tags = "@specific"
    // tags = "@AllTestsScenario"
    // tags = "@dedeman"
    
    )

public class TestRunner {
}