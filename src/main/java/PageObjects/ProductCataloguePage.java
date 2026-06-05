package PageObjects;

import ReusableUtilis.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponents {

    WebDriver driver;
    public ProductCataloguePage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }




    @FindBy(css = ".mb-3")
    List<WebElement> products;

    By productsBy = By.cssSelector(".mb-3 b");

   @FindBy(css=".ng-animating")
   WebElement loadingSpinner;

    By toastMsg= By.cssSelector("#toast-container");
    By addToCartBtn = By.xpath(".//button[text()=' Add To Cart']");

    //@FindBy(xpath = "//button[text()=' Add To Cart']")
    //WebElement addToCartBtn;


    public List<WebElement> getProducts(){

        waitForElementsToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName){
        WebElement prod = getProducts().stream()
                .filter(product ->
                        product.findElement(productsBy)
                                .getText()
                                .equalsIgnoreCase(productName)
                )
                .findFirst()
                .orElse(null);
        return prod;
    }
    public Cartpage addProductToCart(String productName) throws InterruptedException {

        WebElement prod= getProductByName(productName);
        assert prod != null;
        prod.findElement(addToCartBtn).click();
        waitForTextToAppear(toastMsg,"Product Added To Cart");
        waitToDisappear(loadingSpinner);
        Cartpage cartpage= goToCart();
        Thread.sleep(2000);
        return cartpage;


    }
}
