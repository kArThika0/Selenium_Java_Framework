package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class Synchronization {
    public static void main(String[] args) throws InterruptedException {

/* implicit waits - applies globally to all the tests .
waits for specified n of seconds before throwing exception. waits for an element to be visible before throwing
element not found exception.
 keeps listening to DOM and if element is visible it comes out of wait.
 */

   /*
    explicit wait - two ways to implement (webdriver wait and fluent wait). target a specific test and make it wait.
    */

        /*
        Thread.sleep() -  halt execution . part of java not selenium webdriver. does not listen to DOM. compulsary wait fo
        specified time.
         */

        /*
        Fluent waits - fluent waits finds the webelement repeatedly at regular intervals of time until the timeout
        or till the object gets found
         */

        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise");
        //implicit wait
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));

        String [] veggies = {"Carrot", "Brinjal"};
        addProductsToCart(driver,veggies);
        driver.findElement(By.cssSelector(".cart-icon img" )).click();
        driver.findElement(By.cssSelector("div[class='cart-preview active'] button[type='button']")).click();

        //here after click there is a split half second delay for the item to be visible. so using wait will resolve
        //explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
        driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
        driver.findElement(By.cssSelector(".promoBtn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
        Assert.assertTrue(driver.findElement(By.cssSelector(".promoInfo")).isDisplayed());
        System.out.println(driver.findElement(By.cssSelector(".promoInfo")).getText());



        //FLUENT WAITS

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();
        Wait<WebDriver> fluWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                if (driver.findElement(By.cssSelector("[id='finish'] h4")).isDisplayed())
                {
                    return driver.findElement(By.cssSelector("[id='finish'] h4"));
                }
            else
                return null;
            }
        });
    }

    public static void addProductsToCart(WebDriver driver, String[] veggies) {

        //convert the veggies to arraylist
        //reason storing the veggies in a array requires less space and in runtime we convert them to list
        List<String> vegetables = Arrays.asList(veggies);
        List<WebElement> addToCart = driver.findElements(By.cssSelector("[class='product-action'] button"));
        List<WebElement> productName = driver.findElements(By.cssSelector(".product-name"));
        int j = 0;
        for (int i = 0; i < productName.size(); i++) {

            String[] productText = productName.get(i).getText().split("-");
            String formattedProduct = productText[0].trim();

            if (vegetables.contains(formattedProduct)) {

                addToCart.get(i).click();
                j++;

                if (j == vegetables.size()) {
                    break;
                }
            }
        }

    }
}









