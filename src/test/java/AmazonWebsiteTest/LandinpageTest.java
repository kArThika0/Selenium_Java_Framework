package AmazonWebsiteTest;

import AmazonWebsite.Pages.Cartpage;
import AmazonWebsite.Pages.Signinpage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LandinpageTest extends BaseTest {
    String resultsExpected = "Results";
    String allMenuExpected = "All";
    String landingpageURL = "https://www.amazon.com/";

    @DataProvider(name = "categoryOptions")
    public Object[][] getCategoryTestData() throws IOException {
        List<HashMap<String, String>> data = jsonToHashmap(
                System.getProperty("user.dir") + "/src/test/java/AmazonWebsiteTest/LandingpageTestData.json");
        return data.stream()
                .map(row -> new Object[]{row.get("categoryOption")})
                .toArray(Object[][]::new);
    }

    @Test(dataProvider = "categoryOptions")
    public void testHeaderStaticElements(String categoryOption) {

        WebElement logo = landingpage.getLogo();

        Assert.assertTrue(logo.isDisplayed(), "Logo should be displayed");
        Assert.assertTrue(logo.isEnabled(), "Logo should be enabled");
        String resultsHeadingText = landingpage.clickFirstProductCard();
        Assert.assertEquals(resultsHeadingText, resultsExpected, "Search results heading mismatch");
        String redirectURL = landingpage.clickLogo();
        landingpageURL = landingpage.getLandingpageURL();
        Assert.assertEquals(landingpageURL, redirectURL, "Logo should redirect to landing page");
        WebElement searchbar = landingpage.getSearchBar();
        Assert.assertTrue(searchbar.isDisplayed(), "Search bar should be displayed");
        String searchbarActualPlaceholder = landingpage.getSearchPlaceholder();
        Assert.assertEquals(searchbarActualPlaceholder, "Search Amazon", "Search bar placeholder mismatch");
        String actualOption = landingpage.chooseCategory(categoryOption);
        Assert.assertEquals(actualOption, categoryOption, "Selected category mismatch: " + categoryOption);
        String allMenuText = landingpage.getAllMenu();
        Assert.assertEquals(allMenuText, allMenuExpected, "All menu text mismatch");
    }

    @Test (groups = {"TestJenkins"})
    public void testHeaderNavigation() {
        Signinpage signInPage = new Signinpage(getDriver());
        System.out.println(
                "TEST HEADER Thread = " +
                        Thread.currentThread().getId() +
                        " Driver = " + getDriver().hashCode());
       // List<WebElement> navBarMenus = landingpage.getNavbarMenu();
        //Assert.assertFalse(navBarMenus.isEmpty(), "Navbar menus should be present");
        landingpage.clickNavBarMenus();
        landingpage.clickSignInLink();
        String signInPageHeading = signInPage.getsiginPageHeading();
        Assert.assertEquals(signInPageHeading, "Sign in or create account");
        landingpage.navigateBacktoLandingPage(landingpageURL);
    }

    @Test(retryAnalyzer = Retry.class, groups = {"TestJenkins"})
    public void testCartHeader() {
        Cartpage cartpage = new Cartpage(getDriver());
        System.out.println(
                "TEST FOOTER Thread = " +
                        Thread.currentThread().getId() +
                        " Driver = " + getDriver().hashCode());
        Assert.assertTrue(landingpage.cartIconLink().isDisplayed(), "Cart icon should be visible");
        cartpage.getNoOfProductsInCart();
        landingpage.cartIconLink().click();
        cartpage.verifyEmptyCart();
        landingpage.navigateBacktoLandingPage(landingpageURL);
        landingpage.clickReturOrdersLink();
        landingpage.navigateBacktoLandingPage(landingpageURL);
    }

    @DataProvider(name = "footerLinks")
    public Object[][] getFooterTestData() throws IOException {
        List<HashMap<String, String>> data = jsonToHashmap(
                System.getProperty("user.dir") + "/src/test/java/AmazonWebsiteTest/FooterTestData.json");
        return data.stream()
                .map(row -> new Object[]{row.get("expectedFooterLink")})
                .toArray(Object[][]::new);
    }

    @Test(retryAnalyzer = Retry.class)
    public void testFooterStructure() {

        landingpage.focusTobackToTopBtn();
        List<WebElement> footerLists = landingpage.getFooterLinks();
        Assert.assertFalse(footerLists.isEmpty(), "Footer links should be present");
        Assert.assertEquals(footerLists.size(), 25, "Footer link count mismatch");
        landingpage.clickFooterLinks();
    }

    @Test(dataProvider = "footerLinks")
    public void testFooterLinkPresence(String expectedFooterLink) {
        landingpage.focusTobackToTopBtn();
        List<String> actualFooterLinks = landingpage.getFooterLinksNames();
        Assert.assertTrue(actualFooterLinks.contains(expectedFooterLink),
                "Footer is missing expected link: " + expectedFooterLink);
    }

}


/*
Comments - maven test run
-P stands for profile
1) mvn test -P "Test Jenkins"
2)
 */
