package EcommTest;

import EcommTest.BaseTest;
import PageObjects.Cartpage;
import PageObjects.ProductCataloguePage;
import PageObjects.Checkoutpage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import PageObjects.LoginPage;

import java.io.IOException;
import java.util.List;

public class SubmitOrderStepDefinitions extends BaseTest {



        ProductCataloguePage productCataloguePage;
        Cartpage cartpage;
        Checkoutpage checkoutpage;
    LoginPage login;

    @Given("user lands on ecommerce platform")
    public void userLandsOnEcommercePlatform() throws IOException {
        driver = initializeDriver();
        login = new LoginPage(driver);
        login.goTo("https://rahulshettyacademy.com/client");
    }

        @Given("User logs in with email {string} and password {string}")
        public void userLogsInWithEmailAndPassword (String email, String password){
            productCataloguePage = login.loginIntoPage(email, password);
        }

        @When("User adds product {string} to the cart")
        public void userAddsProductToTheCart (String productName) throws InterruptedException {
            List<WebElement> products = productCataloguePage.getProducts();
            cartpage = productCataloguePage.addProductToCart(productName);
        }

        @And("checkout {string} and submit the order")
        public void checkoutAndSubmitTheOrder (String productName) throws InterruptedException {
            Assert.assertTrue(cartpage.verifyCartItem(productName));
            checkoutpage = cartpage.goToCheckout();
            checkoutpage.enterCheckoutInfo("IND", "India");
            checkoutpage.placeOrder();
        }

        @Then("order confirmation {string} should be displayed")
        public void orderConfirmationShouldBeDisplayed (String expectedMessage){
            String confirmMsg = checkoutpage.verifyOrder();
            Assert.assertEquals(confirmMsg, expectedMessage);
            driver.close();
        }

    }


