package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Handlingbrokenlinks {
    public static void main (String[] args) throws IOException {

        //broken URL
        //Step 1 - IS to get all urls tied up to the links using Selenium
        //  Java methods will call URL's and gets you the status code
        //if status code >400 then that url is not working-> link which tied to url is broken

        //a[href*="soapui"]'
        WebDriver driver= new ChromeDriver();
        SoftAssert soft= new SoftAssert();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links= driver.findElements(By.cssSelector("li[class = 'gf-li'] a"));

        for(WebElement link : links){


            String url = link.getAttribute("href");
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int statusCode= conn.getResponseCode();

          //using soft assertions - so that it checks all the links rather than abrupting execution when one broken link is found
            soft.assertTrue(statusCode<400, "The link with Text"+link.getText()+" is broken with code" +statusCode);
            }

        soft.assertAll();
        }



    }

