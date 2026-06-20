package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class windowAuthPopup {
    public static void main(String[] args) throws InterruptedException, IOException {

    /* · Handling Window Authentication Pop Up
         http://Username:Password@SiteURL

USING AUTOIT
autoit script to upload file
ControlFocus("Open","","Edit1")
ControlSetText("Open","","Edit1","C:\Users\nagar\Documents\check\visit.pdf")
ControlClick("Open","","Button1")

     */
        String downloadPath=System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver","C:\\work\\chromedriver.exe");
        //code to set download directory
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        //passing the hashmap to chromeOptions
        ChromeOptions options=new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        //passing chromeoptions to Webdriver
        WebDriver driver=new ChromeDriver(options);
        driver.get("https://altoconvertpdftojpg.com/");
        driver.findElement(By.cssSelector("[class*='btn--choose']")).click();
        Thread.sleep(3000);
        //fileupload using autoit script
        Runtime.getRuntime().exec("C:\\Users\\nagar\\Documents\\check\\fileupload.exe");
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='medium']")));
        driver.findElement(By.cssSelector("button[class*='medium']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));
        driver.findElement(By.linkText("Download Now")).click();
        Thread.sleep(5000);

        File f=new File(downloadPath+"/converted.zip");
        if(f.exists())
        {
            Assert.assertTrue(f.exists());
            if(f.delete())
                System.out.println("file deleted");
        }
    }

}
