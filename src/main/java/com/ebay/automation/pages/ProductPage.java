package com.ebay.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "h1.x-item-title__mainTitle span")
    private WebElement productTitle;
    
    @FindBy(css = "div.x-price-primary span")
    private WebElement productPrice;
    
    @FindBy(xpath = "//a[@id='atcBtn_btn_1' and @data-testid='ux-call-to-action']")
    private WebElement addToCartButton;
    
    private String itemName;
    private String itemPrice;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        itemName = getText(productTitle);
        System.out.println("Item Name: " + itemName);
        return itemName;
    }

    public String getProductPrice() {
        itemPrice = getText(productPrice);
        System.out.println("Item Price: " + itemPrice);
        return itemPrice;
    }

    public void addToCart() {
        scrollToElement(addToCartButton);
        clickElement(addToCartButton);
    }

    public CartPage navigateToCart() {
        return new CartPage(driver);
    }

    public CartPage addProductToCartAndNavigateToCart() {
        getProductName();
        getProductPrice();
        addToCart();
        return navigateToCart();
    }

    public String getStoredItemName() {
        return itemName;
    }

    public String getStoredItemPrice() {
        return itemPrice;
    }
}
