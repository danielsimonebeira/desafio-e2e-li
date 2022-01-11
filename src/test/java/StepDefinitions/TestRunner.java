package StepDefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Features",
        glue = {"StepDefinitions"},
        monochrome = true,
//plugin = {"pretty", "html:target/test-report"})
        plugin = {"pretty", "junit:target/test-report.html"})
//plugin = { "com.cucumber.ExtentCucumberFormatter:target/repost.html" })
public class TestRunner {
}
