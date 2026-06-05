package EcommTest;

import PageObjects.Cartpage;
import PageObjects.OrdersPage;
import PageObjects.ProductCataloguePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidations extends BaseTest {

    @Test(groups = {"negative"})
    public void loginErrorValidations(){

        String msg= login.loginErrorVerify("karthiTest@outlook.com", "dummyPass");
        Assert.assertEquals(msg, "Incorrect email or password.");

    }


@Test (groups = {"negative"})
public void searchAddedProductErrorValidation() throws InterruptedException {

    ProductCataloguePage productCataloguePage = login.loginIntoPage("karthiTest@outlook.com", "karthiTest@123");
    List<WebElement> products = productCataloguePage.getProducts();
    // Add products to cart
    Cartpage cartpage = productCataloguePage.addProductToCart("ADIDAS ORIGINAL");
    boolean match = cartpage.verifyCartItem("ADIDAS ORIGINAL Dummy");
    Assert.assertFalse(match);
}



}
