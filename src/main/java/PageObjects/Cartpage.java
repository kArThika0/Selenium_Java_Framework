package PageObjects;

import ReusableUtilis.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Cartpage extends AbstractComponents {

    WebDriver driver;
    public Cartpage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }

    //List<WebElement> cartItems= driver.findElements(By.cssSelector(".infoWrap h3"));

    @FindBy(css=".infoWrap h3")
    List<WebElement> cartItems;

    @FindBy(css=".totalRow button")
    WebElement checkOutBtn;

    public boolean verifyCartItem(String productAddedToCart){

        return cartItems.stream().anyMatch(cartItem ->cartItem.getText().equalsIgnoreCase(productAddedToCart));

    }

    public Checkoutpage goToCheckout(){
        checkOutBtn.click();
        Checkoutpage checkoutPage=new Checkoutpage(driver);
        return checkoutPage;

    }
}

