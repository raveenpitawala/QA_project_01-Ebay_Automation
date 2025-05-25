package com.ebay.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterPage extends BasePage {

    @FindBy(xpath = "//button[.//span[@class='filter-label' and text()='Filter']]")
    private WebElement filterbtn;

    @FindBy(xpath = "//button[.//h3[text()='Screen Size']]")
    private WebElement screenSizeFilter;
    
    @FindBy(xpath = "//button[contains(.,'Apply')]")
    private WebElement applyButton;
    
    @FindBy(css = ".bsig__title__wrapper")
    private List<WebElement> searchResults;
    
    @FindBy(css = ".s-item__title")
    private List<WebElement> itemTitles;

    public FilterPage(WebDriver driver) {
        super(driver);
    }

    public void clickFilterButton() {
        clickElement(filterbtn);
    }

    public void clickScreenSizeFilter() {
        scrollToElement(screenSizeFilter);
        clickElement(screenSizeFilter);
    }

    public void selectScreenSize(String screenSize) {
        // Use the passed screenSize to build the XPath
        String screenSizeXpath = String.format("//span[contains(.,'%s')]", screenSize);
        WebElement screenSizeOption = waitForElementClickable(By.xpath(screenSizeXpath));
        clickElement(screenSizeOption);
    }

    public void applyFilters() {
        clickElement(applyButton);
    }

    public ProductPage selectFirstProduct() {
        // Wait for search results to load
        WebElement firstProduct = waitForElementClickable(searchResults.get(0));
        clickElement(firstProduct);
        
        // Switch to new tab/window if the product opens in a new tab
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        return new ProductPage(driver);
    }

    public FilterPage applyScreenSizeFilter(String screenSize) {
        clickFilterButton();
        clickScreenSizeFilter();
        selectScreenSize(screenSize);
        applyFilters();
        return this;
    }
}
