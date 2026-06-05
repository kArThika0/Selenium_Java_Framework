package AmazonWebsite.Pages;

import AmazonWebsite.Utils.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Signinpage extends AbstractComponents {

        WebDriver driver;

        public Signinpage (WebDriver driver) {
            super(driver);
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        //driver.findElement(By.tagName("h1")).getText();
    @FindBy(tagName = "h1")
        WebElement signInHeading;

        public String getsiginPageHeading() {
            return signInHeading.getText();
        }




}
