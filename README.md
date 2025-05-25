# eBay Mobile Phone Purchase Automation

This project automates the purchase flow for a mobile phone on eBay.com using Selenium WebDriver and TestNG.

## Project Structure

```
ebay-selenium-assignment/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── ebay/
│   │               └── automation/
│   │                   ├── pages/
│   │                   │   ├── BasePage.java
│   │                   │   ├── HomePage.java
│   │                   │   ├── FilterPage.java
│   │                   │   ├── ProductPage.java
│   │                   │   ├── CartPage.java
│   │                   │   └── CheckoutPage.java
│   │                   └── utils/
│   │                       ├── WebDriverManager.java
│   │                       └── TestDataProvider.java
│   └── test/
│       └── java/
│           └── com/
│               └── ebay/
│                   └── automation/
│                       └── tests/
│                           └── EbayPurchaseTest.java
├── pom.xml
├── testng.xml
└── log4j2.xml
```

## Features

- Page Object Model (POM) design pattern
- Cross-browser testing with Chrome, Firefox, and Edge
- Test data parameterization
- Explicit waits for better test stability
- Parallel test execution
- Comprehensive logging

## Prerequisites

- Java JDK 11 or higher
- Maven 3.6.3 or higher
- Chrome, Firefox, and Edge browsers

## Running the Tests

### Command Line

```bash
# Run all tests
mvn clean test

# Run specific test groups
mvn clean test -Dgroups=navigation,verification

# Run with specific browser
mvn clean test -Dbrowser=chrome
```

### IDE

1. Import the project as a Maven project in your IDE
2. Right-click on `testng.xml` and select "Run As TestNG Suite"

## Test Scenarios

1. **Basic Navigation**: Launch browser, navigate to eBay, verify URL
2. **Category & Product Selection**: Navigate to electronics, Cell Phones & Smartphones, apply screen size filter
3. **Product Details & Cart Verification**: Select a product, get details, add to cart, verify details
4. **Checkout Process**: Proceed to checkout, enter guest email, fill shipping information
5. **End-to-End Test**: Complete flow from navigation to checkout
6. **Cross-Browser Testing**: Run tests across Chrome, Firefox, and Edge

## Note

This automation stops at the shipping information page as per requirements, without completing the purchase.
