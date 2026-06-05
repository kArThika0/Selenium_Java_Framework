package ReusableUtilis;

import PageObjects.Cartpage;
import PageObjects.OrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {
    WebDriver driver;
    public AbstractComponents(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);


    }

    @FindBy(css="[routerlink *='cart']")
    WebElement cart;

    @FindBy(css="button[routerlink*='myorders']")
    WebElement ordersPage;

    public void waitForElementsToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitToDisappear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));

    }
    public void waitForElementsToDisappear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }

    public void waitForTextToAppear(By locator, String text) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public Cartpage goToCart(){

        cart.click();
        Cartpage cartPage=new Cartpage(driver);
        return cartPage;
    }

    public OrdersPage goToOrdersPage(){
        ordersPage.click();
        OrdersPage ordersPage = new OrdersPage(driver);
        return ordersPage;

    }
}
