package com.ebay.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    @FindBy(css = ".item-title")
    private WebElement cartItemTitle;
    
    @FindBy(css = ".item-price span")
    private WebElement cartItemPrice;
    
    @FindBy(css = ".cart-summary-line-item .text-display-span span")
    private WebElement estimatedTotal;
    
    @FindBy(xpath = "//button[text()='Go to checkout']")
    private WebElement checkoutButton;
    
    @FindBy(xpath = "//button[text()='Continue as guest']")
    private WebElement guestcheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getCartItemName() {
        return getText(cartItemTitle);
    }

    public String getCartItemPrice() {
        return getText(cartItemPrice);
    }

    public String getEstimatedTotal() {
        String total = getText(estimatedTotal);
        System.out.println("Estimated Total: " + total);
        return total;
    }

    public CheckoutPage proceedToCheckout() {
        clickElement(checkoutButton);
        clickElement(guestcheckoutButton);
        return new CheckoutPage(driver);
    }
}
