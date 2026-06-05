package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Assignmentquestion {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("checkBoxOption1")).click();
        Assert.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
        driver.findElement(By.id("checkBoxOption1")).click();
        Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());


        List<WebElement> checkBoxOptions= driver.findElements(By.cssSelector("label input[type='checkbox']"));
        System.out.println(checkBoxOptions.size());



        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.findElement(By.name("name")).sendKeys("karthi");
        driver.findElement(By.name("email")).sendKeys(("carthi@gmail.com"));
        driver.findElement(By.cssSelector("[type='password']")).sendKeys("password");
        driver.findElement(By.id("exampleCheck1")).click();
        WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select dropdown=new Select(genderDropdown);
        dropdown.selectByIndex(1);
        driver.findElement(By.cssSelector("[value='option1']")).click();
        driver.findElement(By.cssSelector("[type='date']")).sendKeys("08-06-1999");
        driver.findElement(By.cssSelector(".btn.btn-success")).click();
        Thread.sleep(1000);
        System.out.println(driver.findElement(By.cssSelector(".alert.alert-success.alert-dismissible")).getText());




        //Assignment child window handling
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.cssSelector("[href='/windows']")).click();
        driver.findElement(By.cssSelector("[href='/windows/new']")).click();
        Set<String> windows= driver.getWindowHandles();
        Iterator<String> it= windows.iterator();
        String parentWindow= it.next();
       String childWindow= it.next();
       System.out.println(driver.getWindowHandle());
       driver.switchTo().window(childWindow);
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.findElement(By.cssSelector("h3")).getText());
        driver.switchTo().window(parentWindow);
        System.out.println(driver.findElement(By.cssSelector("h3")).getText());



        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("checkBoxOption2")).click();
       String optionValue =  driver.findElement(By.id("checkBoxOption2")).getDomAttribute("value");
       System.out.println(optionValue);
       Select selectDropDown= new Select(driver.findElement(By.id("dropdown-class-example")));
       selectDropDown.selectByValue(optionValue);
       driver.findElement(By.id("name")).sendKeys(optionValue);
       driver.findElement(By.id("alertbtn")).click();


    }


}
