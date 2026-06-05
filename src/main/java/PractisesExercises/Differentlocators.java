package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class Differentlocators {
    public static void main(String[] args) throws InterruptedException {

        String userName = "Karthi";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        //**************login***************************
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/locatorspractice/");
        //driver.navigate().to("https://google.com");
        //driver.navigate().back();
        //driver.navigate().forward();
        driver.findElement(By.id("inputUsername")).sendKeys("xyz@gmail.com");
        driver.findElement(By.name("inputPassword")).sendKeys("xyz");
        driver.findElement(By.className("submit")).click();
        System.out.println(driver.findElement(By.cssSelector("p.error")).getText());

        //*****************Register***********************
        driver.findElement(By.linkText("Forgot your password?")).click();
        driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys("abc");
        driver.findElement((By.xpath("//input[@placeholder='Email']"))).sendKeys("abc@gmail.com");
        driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
        driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("john@gmail.com");
        driver.findElement(By.xpath("//form/input[3]")).sendKeys("9864353253");
        driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
        System.out.println(driver.findElement(By.cssSelector("form p")).getText());
        String passwordText = driver.findElement(By.cssSelector("form p")).getText();
        String password = getPassword(passwordText);
        driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
        Thread.sleep(1000);


        //****************Re-login
        driver.findElement(By.cssSelector("#inputUsername")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[type*='pass']")).sendKeys(password);
        driver.findElement(By.id("chkboxOne")).click();
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.tagName("p")).getText());
        Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
        Assert.assertEquals(driver.findElement(By.xpath("//div/h2")).getText(), "Hello " + userName + ",");
        driver.findElement(By.xpath("")).click();
        driver.close();//*[text()='Log Out']

    }


    public static String getPassword(String passwordText) {

        String pass = passwordText.split("'")[1];
        String password = pass.split("'")[0];
        return password;

    }
}