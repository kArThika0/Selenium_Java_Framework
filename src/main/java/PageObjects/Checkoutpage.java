package PageObjects;

import ReusableUtilis.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Checkoutpage extends AbstractComponents {
    WebDriver driver;
    public Checkoutpage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

     //driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("Ind");
    @FindBy(css="[placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(css=".ta-results .ta-item")
    List<WebElement> countryOptions;

    By selectCountry= By.cssSelector(".ta-results");

    @FindBy(css=".action__submit")
    WebElement placeOrderBtn;

   By orderConfirmation= By.cssSelector(".hero-primary");

   @FindBy(css="tr[class='ng-star-inserted'] label")
   List<WebElement> order;


    public void enterCheckoutInfo(String countryName, String expectedCountry) throws InterruptedException {

        countryDropdown.sendKeys(countryName);
        waitForElementsToAppear(By.cssSelector(".ta-results"));
        WebElement expectedOption = countryOptions.stream().filter(option -> option.getText().equalsIgnoreCase(expectedCountry)).findFirst().orElse(null);
        assert expectedOption != null;
        expectedOption.click();
    }

    public void placeOrder(){
        placeOrderBtn.click();
        waitForElementsToAppear(orderConfirmation);
    }

    public String verifyOrder(){
        String confirmMsg= driver.findElement(orderConfirmation).getText();
        return confirmMsg;
    }

    public String getOrderNumber(){

      int noOfOrders=order.size();
        String orderNoStr= order.get(0).getText();
        String orderNumber= orderNoStr.split("\\|")[1].trim();
        return orderNumber;
    }


}
