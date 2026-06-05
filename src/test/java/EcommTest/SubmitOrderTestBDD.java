package EcommTest;

import PageObjects.Cartpage;
import PageObjects.Checkoutpage;
import PageObjects.OrdersPage;
import PageObjects.ProductCataloguePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTestBDD extends BaseTest{
    String orderNumber;
    Checkoutpage checkoutpage;

    @Test (dataProvider = "getData", groups = {"positive"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {


        //login to ecomm website  using valid credentials
        ProductCataloguePage productCataloguePage = login.loginIntoPage(input.get("email"), input.get("password"));

        List<WebElement> products = productCataloguePage.getProducts();
        // Add products to cart
        Cartpage cartpage = productCataloguePage.addProductToCart(input.get("productName"));
        boolean match = cartpage.verifyCartItem(input.get("productName"));
        Assert.assertTrue(match);
        //click cart -> checkout
        checkoutpage = cartpage.goToCheckout();
        checkoutpage.enterCheckoutInfo("IND", "India");
        //Place order
        checkoutpage.placeOrder();
        String confirmMsg = checkoutpage.verifyOrder();
        Assert.assertEquals(confirmMsg, "THANKYOU FOR THE ORDER.");
    }



@DataProvider
public Object[][] getData() throws IOException {

List<HashMap<String,String>> data=jsonToHashmap(System.getProperty("user.dir")+"/productPurchaseTestData.json");
return new Object[][] {{data.get(0)},{data.get(1)}};



      /* return new Object[][] {
               {"karthiTest@outlook.com","karthiTest@123", "ADIDAS ORIGINAL" },
               {"carthi@gmail.com","N@ruto1999","iphone 13 pro"}
       };

       */

    /*sending testdata using hashmap
    HashMap<String, String> map = new HashMap<String, String>();
    map.put("email", "karthiTest@outlook.com");
    map.put("password", "karthiTest@123");
    map.put("productName", "ADIDAS ORIGINAL");

    HashMap<String, String> map1 = new HashMap<String, String>();
    map1.put("email", "carthi@gmail.com");
    map1.put("password", "N@ruto1999");
    map1.put("productName", "iphone 13 pro");

    return new Object[][]{{map},{map1}};


     */
}

}
