package AmazonWebsiteTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class ListenersTest implements ITestListener {

    ExtentTest test;
    ExtentReports reporter = BaseTest.generateReport();
    ThreadLocal<ExtentTest> tExtent = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = reporter.createTest(result.getMethod().getMethodName());
        tExtent.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tExtent.get().log(Status.PASS, "test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest extentTest = tExtent.get();
        extentTest.fail(result.getThrowable());

        BaseTest base = (BaseTest) result.getInstance();
        WebDriver driver = base.getDriver();

        try {
            String ss = base.takeScreenshot(result.getMethod().getMethodName(), driver);
            extentTest.addScreenCaptureFromPath(ss);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        reporter.flush();
    }
}

