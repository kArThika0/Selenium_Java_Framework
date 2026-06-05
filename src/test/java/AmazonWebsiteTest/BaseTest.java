package AmazonWebsiteTest;

import AmazonWebsite.Pages.Cartpage;
import AmazonWebsite.Pages.Landingpage;
import AmazonWebsite.Pages.Signinpage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Landingpage landingpage;
    //public Signinpage signInPage;
   // public Cartpage cartpage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fos = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/AmazonWebsite/Resources/GlobalProperties.properties");
        prop.load(fos);
        String browserName = prop.getProperty("browserName");
        WebDriver webdriver;

        if (browserName.equalsIgnoreCase("chrome")) {
            webdriver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            webdriver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            webdriver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        webdriver.manage().window().maximize();
        return webdriver;
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod(alwaysRun = true)
    public Landingpage LaunchApplication() throws IOException {
        driver.set(initializeDriver());
        System.out.println(
                "Thread = " + Thread.currentThread().getId() +
                        " Driver = " + getDriver().hashCode());

        landingpage = new Landingpage(getDriver());
       // signInPage  = new Signinpage(driver);
        //cartpage    = new Cartpage(driver);

        landingpage.goToLandingpage("https://www.amazon.com/");
        landingpage.dismissLocationAlert();
        return landingpage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
            getDriver().quit();
            driver.remove();
        }


    public String takeScreenshot(String testcaseName, WebDriver driver) throws IOException {
        TakesScreenshot ss = (TakesScreenshot) driver;
        File source = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(System.getProperty("user.dir") + "/reports/" + testcaseName + ".png"));
        return System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
    }

    public static ExtentReports generateReport() {
        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        spark.config().setDocumentTitle("Amazon ecomm website testing");
        spark.config().setReportName("Test Results");

        ExtentReports reporter = new ExtentReports();
        reporter.attachReporter(spark);
        reporter.setSystemInfo("Tester", "Karthika");
        return reporter;
    }

    public List<HashMap<String, String>> jsonToHashmap(String path) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

}
