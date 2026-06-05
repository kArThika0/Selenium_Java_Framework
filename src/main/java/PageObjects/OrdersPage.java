package PageObjects;

import ReusableUtilis.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends AbstractComponents {
    WebDriver driver;

    public OrdersPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    By ordersPageHeading= By.cssSelector("h1");

    @FindBy(css="h1")
    WebElement confirmationMsg;

    @FindBy(css="[scope='row']")
    List<WebElement> orderDetails;

    public WebElement verifyOrdersPage(){
        waitForElementsToAppear(ordersPageHeading);
       return confirmationMsg;

    }

    public boolean verifyOrdersByOrderNumber(String orderNumber){

        boolean orderMatch = orderDetails.stream().anyMatch(s-> s.getText().equals(orderNumber));
        return orderMatch;
    }
}
