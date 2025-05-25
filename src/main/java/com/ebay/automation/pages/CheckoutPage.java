package com.ebay.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "emailConfirm")
    private WebElement confirmEmailInput;

    @FindBy(id = "btnContinue")
    private WebElement continueButton;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "addressLine1")
    private WebElement addressLine1Input;

    @FindBy(id = "city")
    private WebElement cityInput;

    @FindBy(id = "stateOrProvince")
    private WebElement stateDropdown;

    @FindBy(id = "zip")
    private WebElement zipCodeInput;

    @FindBy(id = "phoneNumber")
    private WebElement phoneNumberInput;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterGuestEmail(String email) {
        fillText(emailInput, email);
        
        // If email confirmation field is present, fill it too
        try {
            if (confirmEmailInput.isDisplayed()) {
                fillText(confirmEmailInput, email);
            }
        } catch (Exception e) {
            // Email confirmation field might not be present, so ignore if not found
        }
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public void fillShippingInformation(String firstName, String lastName, String address,
                                        String city, String state, String zipCode, String phoneNumber) {
        fillText(firstNameInput, firstName);
        fillText(lastNameInput, lastName);
        fillText(addressLine1Input, address);
        fillText(cityInput, city);
        
        // Select state from dropdown
        // For simplicity, we're using send keys, but in a real implementation, you might want to use Select class
        fillText(stateDropdown, state);
        
        fillText(zipCodeInput, zipCode);
        fillText(phoneNumberInput, phoneNumber);
    }

    public void proceedAsGuest(String email) {
        enterGuestEmail(email);
        clickContinue();
    }
}
