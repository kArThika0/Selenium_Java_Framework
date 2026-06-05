package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class Handlingdifferentelements {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        //Handling static dropdown
        WebElement staticDropdown= driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdowns = new Select(staticDropdown);
        //select by index
        dropdowns.selectByIndex(3);
        System.out.println(dropdowns.getFirstSelectedOption().getText());
        //select by value
        dropdowns.selectByValue("AED");
        System.out.println(dropdowns.getFirstSelectedOption().getText());
        //select by visible text
        dropdowns.selectByVisibleText("INR");
        System.out.println(dropdowns.getFirstSelectedOption().getText());


        //Handling different dropdown (+ , -)
        driver.findElement(By.id("divpaxinfo")).click();
        //printing default selected option
        System.out.println("Default adults selected "+driver.findElement(By.id("divpaxinfo")).getText());
        Thread.sleep(2000);
       boolean dropDownOptionVisibility=driver.findElement(By.id("divpaxOptions")).isDisplayed();

        if(dropDownOptionVisibility){
            for(int i=0; i<4; i++) {
                driver.findElement((By.id("hrefIncAdt"))).click();
            }
            System.out.println("after adding adults " +driver.findElement(By.id("divpaxinfo")).getText());

        }




        //handling dynamic dropdowns

        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='BLR']")).click();
        Thread.sleep(2000);
        //This means:
        //Find all matching elements
        //Then pick the 2nd one globally -> different to //a[@value='BLR'][2] which means select second position to parent.
        driver.findElement(By.xpath("(//a[@value='MAA'])[2]")).click(); //dynamic selection based on index

        //another way to pick dynamic selection is Parent-child relationship locator
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']"));



        //auto-suggestive drop-downs

        driver.findElement(By.id("autosuggest")).click();
        driver.findElement(By.id("autosuggest")).sendKeys("Ind");
        Thread.sleep(2000);
        List<WebElement> options=driver.findElements(By.cssSelector("[class='ui-menu-item'] a"));

        for(WebElement a:options){
            if(a.getText().equalsIgnoreCase("India")){
                a.click();
                break;
            }
        }




        //Handling checkboxes
        Assert.assertFalse(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
//Assert.assertFalse(true);System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        System.out.println(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).isSelected());


    //handling radio buttons
        // System.out.println(driver.findElement(By.name("ctl00$mainContent$view_date2")).isEnabled());
        //isEnabled() will not work in some circumstance. in that situation see for any style attribute dynamically changing based on click and pick that using
        //getDomAttribute() to assert and see whether a webelement is enabled or disabled
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        System.out.println(driver.findElement(By.id("Div1")).getAttribute("style"));
        if(driver.findElement(By.id("Div1")).getDomAttribute("style").contains("1"))
        {
            System.out.println("its enabled");
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertTrue(false);
        }

        //handling alerts
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("name")).sendKeys("Karthi");
        driver.findElement(By.id("alertbtn")).click();
        System.out.println( driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmbtn")).click();
        driver.switchTo().alert().dismiss();

    }
}
