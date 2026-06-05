package PractisesExercises;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Arrays;

public class Misc {

    public static void main(String[] args)  {
        //Handling SSL certificates issue

        ChromeOptions options= new ChromeOptions();
        FirefoxOptions options1=new FirefoxOptions();
        EdgeOptions options2=new EdgeOptions();
        //before accepting SSL certificate
        options.setAcceptInsecureCerts(true);
        //after accepting SSL certificate
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        System.out.println(driver.findElement(By.tagName("h1")).getText());


        //if we want to set extensions to the broweser. download the extension file and provide the path in argument
        //options.addExtensions("<PATH TO EXTENSION FILE");

/*
        //adding Proxies to your browser
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("ipaddress:4444");
        options.setCapability("proxy", proxy);


        //setting the chrome downloads to specific directory
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "/directory/path");
        options.setExperimentalOption("prefs", prefs);

*/
        //Disabling pop-ups. some website show pop-ups like enable location at the top. below code will handle and remove it
        options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));


        //window maximizing and minimizing
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        //deleting cookies. if all cookies are deleted the session should logout for user is a valid scenario
        driver.manage().deleteAllCookies();
        //deletes specific cookie.
        driver.manage().deleteCookieNamed("ts");
        driver.get("https://eventhub.rahulshettyacademy.com/");


        //Taking screenshots - whole page
       // File src= (( TakesScreenshot)driver)).getScreenshotAs(OutputType.FILE);
       //FileUtils.copyFile(src,new File("C:\\Users\\nagar\\OneDrive\\Pictures\\Screenshots\\test.png"));

//Screenshot - specific element
       // File file=name.getScreenshotAs(OutputType.FILE);
       // FileUtils.copyFile(file, new File("logo.png"));



    }


}
