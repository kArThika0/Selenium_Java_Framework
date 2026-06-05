import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LandingpageDraft {

    @Test
    public void goToLandingpage() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Verify homepage loads successfully
        driver.get("https://www.amazon.com/");

        // Verify Amazon logo is displayed and clickable
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo-sprites")));
        Assert.assertTrue(driver.findElement(By.id("nav-logo-sprites")).isDisplayed());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role='alertdialog']")));
        WebElement dismissAlert = driver.findElement(By.xpath("//div[@role='alertdialog']//input[@data-action-type='DISMISS']"));
        dismissAlert.click();

        driver.findElement(By.cssSelector("#desktop-grid-1 a")).click();
        String resultsHeadingText = driver.findElement(By.cssSelector("span[data-component-type='s-messaging-widget-results-header'] h2")).getText();
        System.out.println(resultsHeadingText);
        Assert.assertEquals(resultsHeadingText, "Results");

        String redirectURL = driver.findElement(By.id("nav-logo-sprites")).getAttribute("href");
        driver.findElement(By.id("nav-logo-sprites")).click();
        String landingpageURL = driver.getCurrentUrl();
        Assert.assertEquals(landingpageURL, redirectURL);
        System.out.println("Landing page " + landingpageURL);

        // Verify search bar is present on homepage
        boolean searchbarVerify = driver.findElement(By.id("twotabsearchtextbox")).isDisplayed();
        Assert.assertTrue(searchbarVerify, "Search bar is displayed");
        String searchbarActualPlaceholder = driver.findElement(By.id("twotabsearchtextbox")).getAttribute("placeholder");
        Assert.assertEquals(searchbarActualPlaceholder, "Search Amazon");

        // in amazon website native select is not displayed for selenium so use the divs to open the dropdown and choose options
        WebElement categoryDropdown = driver.findElement(By.id("nav-search-dropdown-card"));
        categoryDropdown.click();
        Select dropdown = new Select(driver.findElement(By.id("searchDropdownBox")));
        List<WebElement> options = dropdown.getOptions();
        options.forEach(option -> System.out.println("Available options are " + option.getText()));
        String expectedOptions = "Books";
        WebElement booksOption = driver.findElement(By.xpath("//option[text()='" + expectedOptions + "']"));
        booksOption.click();

        // Verify navigation menu items are displayed
        driver.findElement(By.id("nav-hamburger-menu")).isDisplayed();
        driver.findElement(By.id("nav-hamburger-menu")).isEnabled();
        String allCategoryMenu = driver.findElement(By.cssSelector("#nav-hamburger-menu span")).getText();
        System.out.println(allCategoryMenu);
        Assert.assertEquals(allCategoryMenu, "All");

        // rest of nav bar menus
        List<WebElement> navBarMenus = driver.findElements(By.cssSelector("#nav-xshop a"));
        Assert.assertFalse(navBarMenus.isEmpty(), "Navbar menus should be present");
        //there is invisible navarbar menu
        int count = navBarMenus.size();
        System.out.println(count);

        String parentWindow = driver.getWindowHandle();
        Actions actions = new Actions(driver);

        navBarMenus.stream().filter(menu -> menu.isDisplayed()).forEach(menu -> {
            System.out.println(menu.getText());
            actions.moveToElement(menu).keyDown(Keys.CONTROL).click(menu).keyUp(Keys.CONTROL).build().perform();
            System.out.println("opening menu items in new tab");
        });

        // Get all windows and close child tabs
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println(allWindows.size());
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

        //Verify 'Hello, Sign in' link in

        driver.findElement(By.cssSelector("[data-csa-c-slot-id='nav-link-accountList']")).click();
        wait.until(ExpectedConditions.titleIs("Amazon Sign-In"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_login_form")));
        String signInPageHeading = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals(signInPageHeading, "Sign in or create account");
        driver.navigate().back();
        Assert.assertNotNull(landingpageURL);
        wait.until(ExpectedConditions.urlToBe(landingpageURL));


        //Verify cart icon in header is
        driver.findElement(By.id("nav-cart")).isDisplayed();
        String cartCount = driver.findElement(By.id("nav-cart-count")).getText();
        driver.findElement(By.id("nav-cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sc-empty-cart")));
        String cartpageHeading = driver.findElement(By.tagName("h3")).getText();
        if (cartCount.equals("0")) {
            Assert.assertTrue(cartpageHeading.contains("empty"));
        }
        driver.navigate().back();
        Assert.assertNotNull(landingpageURL);
        wait.until(ExpectedConditions.urlToBe(landingpageURL));


        //Verify 'Return & Orders' link in header
        driver.findElement(By.id("nav-orders")).click();
        //if not signed in it will navigate to sigin page
        wait.until(ExpectedConditions.titleIs("Amazon Sign-In"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ap_login_form")));
        driver.navigate().back();
        Assert.assertNotNull(landingpageURL);
        wait.until(ExpectedConditions.urlToBe(landingpageURL));


    }


    @Test
    public void handlingFooterLinks() {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Verify homepage loads successfully
        driver.get("https://www.amazon.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-logo-sprites")));
        Assert.assertTrue(driver.findElement(By.id("nav-logo-sprites")).isDisplayed());


        //Verify footer links are present
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('#navBackToTop').scrollIntoView(true)");
        WebElement footer = driver.findElement(By.cssSelector("#navBackToTop"));
       List<WebElement> footerLists = driver.findElements(By.cssSelector(".navFooterLinkCol a"));
       Assert.assertFalse(footerLists.isEmpty(), "Footer links should be present");
       Assert.assertEquals(footerLists.size(), 25);
        //add all 25 text tp expectedfooterLinks
        List<String> expectedfooterLinks = List.of("Careers", "Blog", "About Amazon", "Investor Relations", "Amazon Devices", "Amazon Science");
        List<String> actualfooterLinks = footerLists.stream().map(footerlist->footerlist.getText()).toList();

        expectedfooterLinks.forEach(expected ->
                Assert.assertTrue(actualfooterLinks.contains(expected), "Footer should contain link: " + expected)
        );



    }


}







