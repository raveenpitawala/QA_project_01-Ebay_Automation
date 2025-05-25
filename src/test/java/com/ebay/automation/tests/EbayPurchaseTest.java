package com.ebay.automation.tests;

import com.ebay.automation.pages.*;
import com.ebay.automation.utils.TestDataProvider;
import com.ebay.automation.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EbayPurchaseTest {

    private WebDriver driver;
    private HomePage homePage;
    private FilterPage filterPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = WebDriverManager.getDriver("chrome");
        homePage = new HomePage(driver);
    }

    @Test(description = "Basic Navigation Test", groups = {"navigation"})
    public void testBasicNavigation() {
        homePage.navigateToHomePage();
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.ebay.com/", "URL verification failed.");
    }

    @Test(description = "Category & Product Selection Test", 
          groups = {"selection"},
          dataProviderClass = TestDataProvider.class,
          dataProvider = "filterData")
    public void testCategoryAndProductSelection(String screenSize) {
        homePage.navigateToHomePage();
        filterPage = homePage.navigateToCellPhoneFilterPage();
        filterPage.applyScreenSizeFilter("4.0 - 4.4 in");
    }

    @Test(description = "Product Details & Cart Verification Test", groups = {"verification"})
    public void testProductDetailsAndCartVerification() {
        homePage.navigateToHomePage();
        filterPage = homePage.navigateToCellPhoneFilterPage();
        filterPage.applyScreenSizeFilter("4.0 - 4.4 in");
        
        productPage = filterPage.selectFirstProduct();
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        
        cartPage = productPage.addProductToCartAndNavigateToCart();
        
        // Assert product details in cart match those from the product page
        Assert.assertEquals(cartPage.getCartItemName(), productName, "Item name in cart does not match.");
        
        // Price comparison might need some cleanup due to formatting differences
        String cartPrice = cartPage.getCartItemPrice().replace("$", "").trim();
        String productPriceClean = productPrice.replace("$", "").trim();
        Assert.assertEquals(cartPrice, productPriceClean, "Item price in cart does not match.");
    }

    @Test(description = "Checkout Process Test", groups = {"checkout"},
      dataProviderClass = TestDataProvider.class,
      dataProvider = "checkoutData")
    public void testCheckoutProcess(String email, String firstName, String lastName, 
                                  String address, String city, String state, 
                                  String zipCode, String phoneNumber) {
        homePage.navigateToHomePage();
        filterPage = homePage.navigateToCellPhoneFilterPage();
        filterPage.applyScreenSizeFilter("4.0 - 4.4 in");
        
        productPage = filterPage.selectFirstProduct();
        cartPage = productPage.addProductToCartAndNavigateToCart();
        
        cartPage.getEstimatedTotal();
        checkoutPage = cartPage.proceedToCheckout();
        
        checkoutPage.proceedAsGuest(email);
        checkoutPage.fillShippingInformation(firstName, lastName, address, city, state, zipCode, phoneNumber);
    }

    @Test(description = "Full End-to-End Purchase Flow Test", 
          groups = {"e2e"}, 
          dataProviderClass = TestDataProvider.class, 
          dataProvider = "checkoutData")
    public void testEndToEndPurchaseFlow(String email, String firstName, String lastName, 
                                       String address, String city, String state, 
                                       String zipCode, String phoneNumber) {
        // Step 1: Navigate to eBay
        homePage.navigateToHomePage();
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.ebay.com/", "URL verification failed.");
        
        // Step 2: Navigate to Cell Phones & Smartphones and apply filters
        filterPage = homePage.navigateToCellPhoneFilterPage();
        filterPage.applyScreenSizeFilter("6.0 - 6.4 in");
        
        // Step 3: Select first product and capture details
        productPage = filterPage.selectFirstProduct();
        String productName = productPage.getProductName();
        String productPrice = productPage.getProductPrice();
        
        // Step 4: Add to cart and verify details
        cartPage = productPage.addProductToCartAndNavigateToCart();
        Assert.assertEquals(cartPage.getCartItemName(), productName, "Item name in cart does not match.");
        
        // Price comparison might need some cleanup due to formatting differences
        String cartPrice = cartPage.getCartItemPrice().replace("$", "").trim();
        String productPriceClean = productPrice.replace("$", "").trim();
        Assert.assertEquals(cartPrice, productPriceClean, "Item price in cart does not match.");
        
        // Step 5: Get estimated total and proceed to checkout
        String total = cartPage.getEstimatedTotal();
        checkoutPage = cartPage.proceedToCheckout();
        
        // Step 6: Enter guest email and shipping information
        checkoutPage.proceedAsGuest(email);
        checkoutPage.fillShippingInformation(firstName, lastName, address, city, state, zipCode, phoneNumber);
    }

    @Test(description = "Cross Browser Testing", 
          groups = {"crossBrowser"}, 
          dataProviderClass = TestDataProvider.class, 
          dataProvider = "browserData")
    public void testCrossBrowser(String browser) {
        // Close current driver and create new one with specified browser
        if (driver != null) {
            driver.quit();
        }
        
        driver = WebDriverManager.getDriver(browser);
        homePage = new HomePage(driver);
        
        homePage.navigateToHomePage();
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.ebay.com/", "URL verification failed with browser: " + browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
