package PractisesExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Actionsframeschildwindows {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver =new ChromeDriver();
        driver.get("https://www.amazon.com/");
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(3));
        Actions a=new Actions(driver);
        //cursor moves to the element. to test any hovers or options displaying dynamically
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-nav-ref='nav_ya_signin']")));
        a.moveToElement(driver.findElement(By.cssSelector("a[data-nav-ref='nav_ya_signin']")))
                .build().perform();
        //right clicking the element
        a.contextClick(driver.findElement(By.cssSelector("a[data-nav-ref='nav_ya_signin']"))).build().perform();
        //sending product name in capital letters
        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("Shoes");


        //child window handling
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
        driver.findElement(By.cssSelector("[href*='documents-request']")).click();
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> s= windows.iterator();
        String parentWindow= s.next();
        String childWindow= s.next();
        driver.switchTo().window(childWindow);
        System.out.println("Current window" +driver.getWindowHandle());
        System.out.println(driver.findElement(By.cssSelector(".im-para.red")).getText());
        driver.findElement(By.cssSelector(".im-para.red")).getText();
        String emailId= driver.findElement(By.cssSelector(".im-para.red")).getText().split("at")[1].trim().split(" ")[0];
        driver.switchTo().window(parentWindow);
        System.out.println("Current window" +driver.getWindowHandle());
        driver.findElement(By.id("username")).sendKeys(emailId);

//frames

        driver.get("https://jqueryui.com/droppable/");
        WebElement frame= driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(frame);
        Actions act = new Actions(driver);
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target= driver.findElement(By.id("droppable"));
        act.dragAndDrop(source, target).build().perform();
        driver.switchTo().defaultContent();
        System.out.println(driver.findElement(By.cssSelector("p[class='desc']")).getText());
        //we can also switch to frames using indexes. finding out avaliblaae iframes in a page and switch based on indexes
        //System.out.println(driver.findElement(By.className("demo-frame")).size());
        // driver.switchTo().frame(0);



        //Handling links
        //1. Give me the count of links on the page.
        //2. Count of footer section-

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        System.out.println(driver.findElements(By.tagName("a")).size());
        WebElement footerdriver=driver.findElement(By.id("gf-BIG"));// Limiting webdriver scope
        System.out.println(footerdriver.findElements(By.tagName("a")).size());
        //3-
        WebElement coloumndriver=footerdriver.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        System.out.println(coloumndriver.findElements(By.tagName("a")).size());

       String parentWindow1=  driver.getWindowHandle();
        //4- click on each link in the coloumn and check if the pages are opening-
        for(int i=1;i<coloumndriver.findElements(By.tagName("a")).size();i++)
        {

            String clickonlinkTab=Keys.chord(Keys.CONTROL,Keys.ENTER);
            //Actions actions = new Actions(driver);
            // actions.keyDown(Keys.CONTROL)
            //               .click(link)
            //               .keyUp(Keys.CONTROL)
            //               .perform();
            coloumndriver.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
            Thread.sleep(5000L);

        }// opens all the tabs
        Set<String> abc=driver.getWindowHandles();//4
        Iterator<String> it=abc.iterator();
        while(it.hasNext())
        {
            String childWindow1 = it.next();
            driver.switchTo().window(childWindow1);
            System.out.println(driver.getTitle());
            if( !childWindow1.equals(parentWindow1)){
                driver.close();
            }
        }

        driver.switchTo()
                .window(parentWindow);


    }


}
