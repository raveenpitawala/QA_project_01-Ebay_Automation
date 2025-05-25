package com.ebay.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private final String BASE_URL = "https://www.ebay.com/";

    @FindBy(linkText = "Electronics")
    private WebElement electronicsLink;

    @FindBy(xpath = "//a[text()='Electronics']")
    private WebElement electronicsLinkBtn;

    @FindBy(linkText = "Cell Phones & Smartphones")
    private WebElement cellPhonesSmartphonesLinkText;

    @FindBy(xpath = "//a[text()='Cell Phones & Accessories']")
    private WebElement cellPhonesAndAccessoriesLink;

    @FindBy(xpath = "//a[text()='Cell Phones & Smartphones']")
    private WebElement cellPhonesSmartphonesLink;

    @FindBy(xpath = "//a/span[contains(text(), 'See All')]")
    private WebElement seeAllLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage navigateToHomePage() {
        driver.get(BASE_URL);
        return this;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void navigateToElectronics() {
        Actions actions = new Actions(driver);
        actions.moveToElement(waitForElementVisible(electronicsLink)).perform();
        clickElement(electronicsLinkBtn);
    }

    public void selectCellPhonesAndAccessories() {
        wait.until(ExpectedConditions.elementToBeClickable(cellPhonesAndAccessoriesLink));
        clickElement(cellPhonesAndAccessoriesLink);
    }

    public void selectCellPhonesAndSmartphones() {
        wait.until(ExpectedConditions.elementToBeClickable(cellPhonesSmartphonesLink));
        clickElement(cellPhonesSmartphonesLink);
    }

    public void selectElectronics() {
        wait.until(ExpectedConditions.elementToBeClickable(electronicsLinkBtn));
        clickElement(electronicsLinkBtn);
    }

    public void clickSeeAll() {
        wait.until(ExpectedConditions.elementToBeClickable(seeAllLink));
        clickElement(seeAllLink);
    }

    public FilterPage navigateToCellPhoneFilterPage() {
        navigateToElectronics();
        selectCellPhonesAndAccessories();
        selectCellPhonesAndSmartphones();
        return new FilterPage(driver);
    }
}
