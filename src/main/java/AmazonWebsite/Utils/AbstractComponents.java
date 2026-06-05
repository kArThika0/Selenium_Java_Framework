package AmazonWebsite.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AbstractComponents {

    WebDriver driver;
    protected AbstractComponents(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver,this);
    }

    By locationAlertModal = By.cssSelector("[role='alertdialog']");

    @FindBy (css= "[role='alertdialog']")
    WebElement locationAlert;

    @FindBy(xpath="//div[@role='alertdialog']//input[@data-action-type='DISMISS']")
    WebElement alertDismissBtn;

public void waitForElementsLocated(By findBy){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}



public void dismissLocationAlert(){
   try {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(ExpectedConditions.visibilityOfElementLocated(locationAlertModal));
       alertDismissBtn.click();
   }
   catch (Exception e){
       // Modal did not appear, continue normally
   }
}

public void waitForAppearanceOfPageTitle(String title){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.titleIs(title));
}

public List<String> focusChildWindowAndClose(String parentWindow){
    Set<String> allWindows = driver.getWindowHandles();
    List<String> childWindowTitles = new ArrayList<>();
    for (String window : allWindows) {
        if (!window.equals(parentWindow)) {
            driver.switchTo().window(window);
            String childwindow=driver.getTitle();
            childWindowTitles.add(childwindow);
            driver.close();
        }
    }
    driver.switchTo().window(parentWindow);
    return childWindowTitles;
}
public void waitForURLtoBe(String url){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.urlToBe(url));
}


public void navigateBacktoLandingPage(String url){
    driver.navigate().back();
    Assert.assertNotNull(url);
    waitForURLtoBe(url);
}
}
