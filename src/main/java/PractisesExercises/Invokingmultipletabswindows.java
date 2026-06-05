package PractisesExercises;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class Invokingmultipletabswindows {

    @Test
    public void invokingMultipleTabsAndWindows() throws IOException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        //below step adds new window or new tab to our current window. use switch to switch to the window that we need.
        driver.switchTo().newWindow(WindowType.WINDOW);
        //driver.switchTo().newWindow(WindowType.TAB);
        Set<String> handles=driver.getWindowHandles();
        Iterator<String> it=handles.iterator();
        String parentWindowId = it.next();
        String childWindow =it.next();
        driver.switchTo().window(childWindow);
        driver.get("https://rahulshettyacademy.com/");
        String courseName = driver.findElements(By.cssSelector("a[href*='https://courses.rahulshettyacademy.com/p']"))
                .get(1).getText();

        driver.switchTo().window(parentWindowId);
        WebElement name=driver.findElement(By.cssSelector("[name='name']"));
        name.sendKeys(courseName);

//Screenshot - specific element

        File file=name.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("logo.png"));


//driver.quit();


//Get Height & Width - used mostly for UI testing

        System.out.println(name.getRect().getDimension().getHeight());
        System.out.println(name.getRect().getDimension().getWidth());
    }

}
