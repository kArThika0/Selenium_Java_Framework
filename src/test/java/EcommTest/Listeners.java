package EcommTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;

public class Listeners implements ITestListener {

    private static ExtentReports reporter;
    private static final Object lock = new Object();
    ThreadLocal<ExtentTest> tExtent = new ThreadLocal<>(); //thread safe

    private static ExtentReports getReporter() {
        if (reporter == null) {
            synchronized (lock) {
                if (reporter == null) {
                    reporter = EcommTest.BaseTest.generateReport();
                }
            }
        }
        return reporter;
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testMethodName = result.getMethod().getMethodName();
        synchronized (lock) {
            ExtentTest test = getReporter().createTest(testMethodName);
            tExtent.set(test);
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = tExtent.get();
        if (test != null) {
            test.log(Status.PASS, "test passed");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = tExtent.get();
        if (test != null) {
            test.fail(result.getThrowable());
            
            try {
                WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
                
                // Take screenshot
                String screenshotPath = getScreenshot(result.getMethod().getMethodName(), driver);
                if (screenshotPath != null) {
                    test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
                }
                
            } catch (Exception e) {
                test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = tExtent.get();
        if (test != null) {
            test.skip("Test skipped");
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
        // Initialize reporter if needed
        getReporter();
    }

    @Override
    public void onFinish(ITestContext context) {
        // Only flush on the final suite finish, not per test finish
        // Check if this is the last test context
        synchronized (lock) {
            if (reporter != null) {
                reporter.flush();
            }
        }
        tExtent.remove();
    }

    private String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
        try {
            TakesScreenshot ss = (TakesScreenshot) driver;
            File source = ss.getScreenshotAs(OutputType.FILE);
            String screenshotPath = System.getProperty("user.dir") + "/reports/" + testcaseName + ".png";
            FileUtils.copyFile(source, new File(screenshotPath));
            return screenshotPath;
        } catch (Exception e) {
            return null;
        }
    }
}
