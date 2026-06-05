package AmazonWebsite.Pages;

import AmazonWebsite.Utils.AbstractComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Landingpage extends AbstractComponents {

    WebDriver driver;

    public Landingpage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "nav-logo-sprites")
    WebElement logo;
    @FindBy(css = "#desktop-grid-1 a")
    WebElement productcardFirst;
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchbar;
    @FindBy(id = "twotabsearchtextbox")
    WebElement searchPlaceholder;
    @FindBy(id="nav-search-dropdown-card")
    WebElement categoryDropdown;
    @FindBy(id="searchDropdownBox")
    WebElement searchDrownBox;
    @FindBy(id="nav-hamburger-menu")
    WebElement allMenu;
    @FindBy(css="#nav-hamburger-menu span")
    WebElement allMenuSpan;
    @FindBy(css="[data-csa-c-slot-id='nav-link-accountList']")
    WebElement signInLink;
    @FindBy(id="nav-cart")
    WebElement cartIconLink;
    @FindBy(id="nav-orders")
    WebElement orderLink;
    @FindBy(css="#navBackToTop")
    WebElement backToTopBtn;
    @FindBy(css=".navFooterLinkCol a")
    List<WebElement> footerLinks;

    private final By logo_amazon = By.id("nav-logo-sprites");
    By locationAlert = By.cssSelector("[role='alertdialog']");
    By searchResultHeading = By.cssSelector("span[data-component-type='s-messaging-widget-results-header'] h2");
    By signInForm=By.id("ap_login_form");
    private final By navbarMenus = By.cssSelector("#nav-xshop a");


    public void goToLandingpage(String url){
        driver.get(url);
    }

    public String getLandingpageURL(){
        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }
    public WebElement getLogo() {
        waitForElementsLocated(logo_amazon);
        return logo;
    }

    public String clickFirstProductCard() {
        productcardFirst.click();
        waitForElementsLocated(searchResultHeading);
        String resultsHeadingText = driver.findElement(searchResultHeading).getText();
        return resultsHeadingText;
    }

    public String clickLogo() {
        waitForElementsLocated(logo_amazon);   // wait for logo to be interactable after navigation
        String redirectURL = logo.getAttribute("href");
        logo.click();
        return redirectURL;
    }

    public WebElement getSearchBar() {
        return searchbar;

    }

    public String getSearchPlaceholder() {
        String placeholder = searchPlaceholder.getAttribute("placeholder");
        return placeholder;
    }

    public void getAllCategoryOptions(){
        Select dropdown = new Select(searchDrownBox);
        List<WebElement> options = dropdown.getOptions();
        options.forEach(option -> System.out.println("Available options are " + option.getText()));
    }

    public String chooseCategory(String expectedOptions){

        categoryDropdown.click();
        WebElement booksOption = driver.findElement(By.xpath("//option[text()='" + expectedOptions + "']"));
        booksOption.click();
        return booksOption.getText();
    }

    public String getAllMenu(){
        allMenu.isDisplayed();
        allMenu.isEnabled();
        String allMenuText = allMenuSpan.getText();
        return allMenuText;
    }

    //public List<WebElement> getNavbarMenu(){
       // return navbarMenus;
   // }

    public void clickNavBarMenus(){
        By navbarLocator = By.cssSelector("#nav-xshop a");
        List<WebElement> menus = driver.findElements(navbarLocator);
        for (WebElement menu : menus) {
            if(menu.isDisplayed() && menu.isEnabled()) {
                new Actions(driver).keyDown(Keys.CONTROL).click(menu).keyUp(Keys.CONTROL).perform();
            }
        }

    }

    public void clickSignInLink(){

        signInLink.click();
        waitForAppearanceOfPageTitle("Amazon Sign-In");
        waitForElementsLocated(signInForm);
}

    public WebElement cartIconLink(){
    return cartIconLink;

    }

public void clickReturOrdersLink(){
   orderLink.click();

    //if not signed in it will navigate to signin page
    waitForAppearanceOfPageTitle("Amazon Sign-In");
    waitForElementsLocated(signInForm);

    //condiiton for when signed-in


}

public void focusTobackToTopBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", backToTopBtn);


    }

    public List<WebElement> getFooterLinks(){
        return footerLinks;
    }

    public List<String> getFooterLinksNames(){
       List<String> footerLinksNames= getFooterLinks().stream().map(WebElement::getText).toList();
       return footerLinksNames;
    }

    public void clickFooterLinks(){
        String parentWindow = driver.getWindowHandle();
        Actions actions = new Actions(driver);
        By footerLinkLocator = By.cssSelector(".navFooterLinkCol a");
        int totalLinks = driver.findElements(footerLinkLocator).size();
        for (int i = 0; i < totalLinks; i++) {
            try {
                WebElement link = driver.findElements(footerLinkLocator).get(i);
                if (link.isDisplayed() && link.isEnabled()) {
                    actions.moveToElement(link).keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
                }
            } catch (StaleElementReferenceException e) {
                // DOM mutated after Ctrl+click opened a tab — skip this link
            }
        }
        List<String> childWindowTitles = focusChildWindowAndClose(parentWindow);
    }


}