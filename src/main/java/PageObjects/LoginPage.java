package PageObjects;

import ReusableUtilis.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    //driver.findElement(By.id("userEmail")).sendKeys("karthiTest@outlook.com");
    //driver.findElement(By.id("userPassword")).sendKeys("karthiTest@123");
    //driver.findElement(By.id("login")).click();

    @FindBy(id="userEmail")
    WebElement emailId;

    @FindBy(id="userPassword")
    WebElement pwd;

    @FindBy(id="login")
    WebElement loginBtn;

    By toastMsg= By.cssSelector("#toast-container");


    By loginErrorToast =By.cssSelector("div[aria-label='Incorrect email or password.']");

    public void goTo(String url){
        driver.get(url);
    }

    public ProductCataloguePage loginIntoPage(String email, String password){

        emailId.sendKeys(email);
        pwd.sendKeys(password);
        loginBtn.click();
        waitForTextToAppear(toastMsg,"Login Successfully");
        waitForElementsToDisappear(toastMsg);
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        return productCataloguePage;
    }

    public String loginErrorVerify(String email, String password){

        emailId.sendKeys(email);
        pwd.sendKeys(password);
        loginBtn.click();
        waitForElementsToAppear(loginErrorToast);
       return driver.findElement(loginErrorToast).getText();
    }
}
