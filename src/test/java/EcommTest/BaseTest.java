package EcommTest;

import AmazonWebsite.Pages.Landingpage;
import PageObjects.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    WebDriver driver;
    LoginPage login;
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fos = new FileInputStream(
                System.getProperty("user.dir") + "/src/main/java/resources/GlobalData.properties");
        prop.load(fos);
        String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser"); // prop.getProperty("browser");

        System.out.println("Browser Name: " + browserName);

        if (browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browserName.contains("headless")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver=new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        if (!browserName.contains("headless")) {
            driver.manage().window().maximize();
        }
        return driver;
    }

    @BeforeMethod(alwaysRun = true)
    public LoginPage LaunchApplication() throws IOException {

       driver = initializeDriver();
        System.out.println("Launching application on URL: https://rahulshettyacademy.com/client");
       login=new LoginPage(driver);
        login.goTo(" https://rahulshettyacademy.com/client");
        return login;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
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
    }}

