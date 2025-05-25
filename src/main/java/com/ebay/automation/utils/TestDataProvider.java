package com.ebay.automation.utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "browserData")
    public static Object[][] getBrowserData() {
        return new Object[][] {
            {"chrome"},
            {"firefox"},
            {"edge"}
        };
    }

    @DataProvider(name = "checkoutData")
    public static Object[][] getCheckoutData() {
        return new Object[][] {
            {
                "test_user@example.com", 
                "John", 
                "Doe", 
                "123 Main Street", 
                "New York", 
                "NY", 
                "10001", 
                "1234567890"
            }
        };
    }

    @DataProvider(name = "filterData")
    public static Object[][] getFilterData() {
        return new Object[][] {
            {"4.0 - 4.4 in"}
        };
    }
}
