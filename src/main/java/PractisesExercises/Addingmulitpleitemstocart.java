package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class Addingmulitpleitemstocart {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise");

        String [] veggies = {"Carrot", "Brinjal"};
        //convert the veggies to arraylist
        //reason storing the veggies in a array requires less space and in runtime we convert them to list
        List<String> vegetables = Arrays.asList(veggies);
        List<WebElement> addToCart= driver.findElements(By.cssSelector("[class='product-action'] button"));
        List<WebElement> productName= driver.findElements(By.cssSelector(".product-name"));
        int j=0;
        for(int i=0; i< productName.size(); i++){

            String [] productText = productName.get(i).getText().split("-");
            String formattedProduct= productText[0].trim();

            if(vegetables.contains(formattedProduct)){

               addToCart.get(i).click();
                j++;

                if(j== vegetables.size()){
                    break;
                }
            }
        }









    }

}
