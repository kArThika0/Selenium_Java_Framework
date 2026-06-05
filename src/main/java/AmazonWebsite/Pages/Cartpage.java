package AmazonWebsite.Pages;

import AmazonWebsite.Utils.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class Cartpage extends AbstractComponents {

    WebDriver driver;

    public Cartpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

   By cartSection= By.id("sc-empty-cart");
    @FindBy(css = "#sc-empty-cart h3")
    WebElement cartSectionHeading;
    @FindBy(id="nav-cart-count")
    WebElement cartCount;

    public String getNoOfProductsInCart(){
        return  cartCount.getText();
    }
    public void verifyEmptyCart(){
        waitForElementsLocated(cartSection);
        String cartpageHeading = cartSectionHeading.getText();
        if (cartCount.getText().equals("0")) {
            Assert.assertTrue(
                    cartpageHeading.contains("Your Amazon Cart is empty"));
    }

    }
}

