package singh.mahabir.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    publish = false,
    features = "src/test/resources/features",
    plugin = {
      "pretty",
      "json:target/cucumber-reports/Cucumber.json",
      "junit:target/cucumber-reports/Cucumber.xml",
      "html:target/cucumber-reports/Cucumber.html"
    },
    extraGlue = "singh.mahabir.bdd.stepdefs",
    monochrome = true)
public class CucumberOptionConfigTest {}
