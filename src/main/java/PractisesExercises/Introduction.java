package PractisesExercises;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Introduction {

    public static void main( String[] args){

        //allows us to use WebDriver methods to work for all the browsers like chrome, firefox and safari

        //WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        WebDriver driver = new EdgeDriver();

        //we need to invoke chrome driver so that it can run in chrome browser.
        //selenium manager takes care of this invocation without us writing steps. it's inbuilt
        //in earlier time we need to download chromedriver exe and set property so that invocation happens


        //System.setProperty("webdriver.chrome.driver","<exe path>.exe");
        //System.setProperty("webdriver.gecko.driver","<exe path>.exe");
        //System.setProperty("webdriver.edge.driver","<exe path>.exe");


        driver.get("https://rahulshettyacademy.com/");
        System.out.println(driver.getTitle());
        String currentURL = driver.getCurrentUrl();
        if(currentURL.equals("https://rahulshettyacademy.com/")){
            System.out.println(currentURL);
        }
        else{
            System.out.println("Unknown URL");
        }
        driver.close();     // closes the present tab
        //driver.quit();   //quits all associated tabs

    }

}
