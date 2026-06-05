package EcommTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features="src/main/java/CucumberBDD",
        glue="EcommTest",
        monochrome = true,
        tags="@positive",
        plugin={"html:target/cucumber-reports.html",
                "json:target/cucumber-reports.json",
                "junit:target/cucumber-reports.xml"}
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {

}
