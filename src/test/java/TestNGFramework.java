import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestListener;

public class TestNGFramework implements ITestListener{

    @Parameters({"URL", "APIKey"})
    @Test(groups = {"Smoke"})
    public void Example(String urlname, String key) {

        System.out.println(urlname);
        System.out.println(key);
    }


    @Test(groups = {"Smoke"})
    public void demoMethods() {
        System.out.println("Smoke test execution");
    }

    @Test(dataProvider="Example2")
    public void ExampleV2(String username,String password){

        System.out.println(username);
        System.out.println(password);
    }

    @DataProvider
    public Object[][] Example2() {

        Object[][] data=new Object[3][2];
        data[0][0]= "firstUsername";
        data[0][1]="firstPassword";
        data[1][0]="secondUsername";
        data[1][1]= "second password";
        data[2][0]="third username";
        data[2][1]="thirdPassword";
        return data;

    }

    @Override
    public void onTestSuccess(ITestResult result) {
       System.out.println("Listener executing after every test pass");
        System.out.println( result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
     //screenshot code, if fail response api
        System.out.println("Listener executing after every fail" +result.getTestName());
    }
}

/*
<includes> only includes the particular method from a class and does not execute anyother methods
<excludes> excludes the mentioned methods and executes all the other methods
we can modularize tests by grouping under test tag.
if there are multiple packages we need to mention class name in the class tag as "packaga.classname"


Annotations
@BeforeTest
@AfterTest
@BeforeSuite
@AfterSuite

the below two executes after/before every @Test
@BeforeMethod
@AfterMethod

@BeforeClass
@AfterClass

For grouping 40 testcases out of 200 to check smoke test
@Test(groups ={"Smoke"})

the test which has dependOnMethods will execute only after AnotherTest is executed.
@Test(dependsOnMethods={"AnotherTest1", "AnotherTest2"})

//skips the execution of test if enabled is false. if its true executes Test
@Test (enabled=false)


Use <parameter> at suite level and test level in the xml file to parameterize the value - (global URLs, API keys or other global values can be passed in this manner)
Use DataProviders for method level parameterization
See the code example for DataProviders


TestNG listeners are block of code which continuously listens to our code during execution and if anything fails
it invokes its method to handle it. "iTestListener" is the interface that we need to implement to our class inorder to
use testNG listeners. generate implemented interface's methods. tie listeners with testNG.xml file do that it knows
the particular class containing listeners

TestNG parallel execution - in xml each <test> executes one by one in sequential manner. if we add parallel attribute to
suite tab . <suite parallel="tests" thread-count="2">

<groups> ?
alwaysRun=true ?
if test-output folder is not displayed in intelliji idean go to edit configurations -> testNG -> listeners -> use default reporters

 */