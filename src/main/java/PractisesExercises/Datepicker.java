package PractisesExercises;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Datepicker {

    public static void main(String[] args) throws InterruptedException {

        /*
        is date input is editable prefer sending date inputs by sendKeys(). this is simple and fastest way
        if the datepicker is not editable try to find any matching class which differentiate day, month and year
        and try to use that to choose dates. Also try to use xpaths. see below.
         */

        String monthNumber = "6";
        String date = "15";
        String year = "2027";

        String[] expectedList = {monthNumber,date,year};
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.cssSelector(".react-date-picker__inputGroup")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.cssSelector(".react-calendar__navigation__label")).click();
        driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
        driver.findElements(By.cssSelector(".react-calendar__year-view__months__month")).get(Integer.parseInt(monthNumber)-1).click();
        driver.findElement(By.xpath("//abbr[text()='"+date+"']")).click();

        List<WebElement> actualList = driver.findElements(By.cssSelector(".react-date-picker__inputGroup__input"));

        //asserting the date entered is same as expected
        for(int i =0; i<actualList.size();i++)
        {
            System.out.println(actualList.get(i).getAttribute("value"));
            Assert.assertEquals(actualList.get(i).getAttribute("value"), expectedList[i]);

        }



        //handle scrolling
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        JavascriptExecutor js=(JavascriptExecutor) driver;
        //scrolls whole page down x-axis, y-axis.
        js.executeScript("window.scrollBy(0,500)");
        //scrolls whole page top
        js.executeScript("window.scrollBy(0,-500)");
        //scrolls to the specific element so that it becomes viewable
        js.executeScript("document.querySelector('.tableFixHead').scrollIntoView(true)");
        //scrolls the table element in a page
        js.executeScript("document.querySelector('.tableFixHead').scrollTop=5000");



        //simulate keyboard scrolling
        //driver.findElement(By.tagName("body")).sendKeys(Keys.PAGE_DOWN);

        //scrolling by simulating mouse movements using Actions class
        //Actions actions =new Actions(driver);
        //WebElement element =driver.findElement(By.id("footer"));
        //actions.moveToElement(element).perform();



        //Handling web tables
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> tableAmounts = driver.findElements(By.cssSelector(".tableFixHead td:nth-child(4)"));
        int sum=0;
        for(int i=0; i< tableAmounts.size();i++){

                Integer.parseInt(tableAmounts.get(i).getText());
                sum=sum+ Integer.parseInt(tableAmounts.get(i).getText());

        }

        int expectedSum = Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(":")[1].trim());
        Assert.assertEquals(sum,expectedSum);

        List<WebElement> rowContent= driver.findElements(By.cssSelector(".table-display tr"));
        System.out.println(rowContent.size());
        System.out.println("row size is" +rowContent.size());
        List<WebElement> columnHeaders = driver.findElements(By.cssSelector(".table-display th"));
        System.out.println("column size is" +columnHeaders.size());
        String secondRowContent= rowContent.get(2).getText();
        System.out.println("Second row content" +secondRowContent);


        //handling autosuggestive drop-downs
        driver.findElement(By.id("autocomplete")).sendKeys("ind");
        Thread.sleep(2000);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.DOWN);
        System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));

    }
}
