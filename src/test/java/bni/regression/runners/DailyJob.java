package bni.regression.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "json:src/test/resources/executionReports/cucumber/DBTestAutomationReport.json", "pretty", "html:src/test/resources/executionReports/cucumber/DBAutomationHTMLReport" },
        features = {"src/test/resources/features"},
        glue = {"bni.regression.steps.endToEndIntegrationSteps"}
        ,tags = {"@test2, @smoke"})
public class DailyJob {

}