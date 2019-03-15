package Runner;



import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
 features = "C:\\Users\\hi\\eclipse-workspace\\Wiremock\\src\\main\\java\\Feature\\Wiremocktest.Feature"
 ,glue={"test.rest.Wiremocktest"})
public class TestRunner {

}
