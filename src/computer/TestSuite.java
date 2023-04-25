package computer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }


    /**
     * Create method with name "selectMenu" it has one parameter name "menu" of type string
     * This method should click on the menu whatever name is passed as parameter
     */
    public void selectMenu(String menu) {
        //Generic X-path for menu navigation
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }

    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {

        String menuName = "Computers";
        //Click on Computer Menu.
        selectMenu(menuName);
        //Click on Desktop
        clickOnElement(By.xpath("//img[@title='Show products in category Desktops']"));
        Select select = new Select(driver.findElement(By.xpath("//select[@id='products-orderby']")));
        //Select Sort By position "Name: Z to A"
        select.selectByVisibleText("Name: Z to A");

        /**
         * Verify the Product will arrange in Descending order.
         */
        List<String> expectedOrderOfDesktopDisplayed = new ArrayList<>();
        expectedOrderOfDesktopDisplayed.add("Lenovo IdeaCentre 600 All-in-One PC");
        expectedOrderOfDesktopDisplayed.add("Digital Storm VANQUISH 3 Custom Performance PC");
        expectedOrderOfDesktopDisplayed.add("Build your own computer");
        List<WebElement> desktopNames = driver.findElements(By.xpath("//a/parent::h2[@class = 'product-title']"));
        List<String> actualDesktopsNames = new ArrayList<>();
        for (WebElement element : desktopNames) {
            actualDesktopsNames.add(element.getText());
        }
        actualDesktopsNames.sort(Collections.reverseOrder());
        verifyText("Displayed items are not according to reverse order",
                actualDesktopsNames.toString(), expectedOrderOfDesktopDisplayed.toString());

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        String menuName = "Computers";
        //Click on Computer Menu.
        selectMenu(menuName);
        //Click on Desktop
        clickOnElement(By.xpath("//img[@title='Show products in category Desktops']"));
        Select select = new Select(element(By.xpath("//select[@id='products-orderby']")));
        //Select Sort By position "Name: A to Z"
        select.selectByVisibleText("Name: A to Z");
        Thread.sleep(1000);
        //Click on "Add To Cart"
        clickOnElement(By.xpath("(//button[@type='button'][normalize-space()='Add to cart'])[1]"));
        String expectedText = "Build your own computer";
        //Verify the Text "Build your own computer"
        String actualTxt = getTextFromElement(By.xpath("//h1[normalize-space()=" +
                "'Build your own computer']"));
        verifyText("The expected text 'Build your own computer' is not diaplayed", expectedText, actualTxt);
        //Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        clickOnElement(By.xpath("//select[@id='product_attribute_1']"));
        selectByVisibleText(By.id("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        Thread.sleep(1000);
        //Select "8GB [+$60.00]" using Select class.
        clickOnElement(By.xpath("//select[@id='product_attribute_2']"));
        selectByVisibleText(By.id("product_attribute_2"), "8GB [+$60.00]");

        Thread.sleep(1000);

        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));

        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));

        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        clickOnElement(By.xpath("(//input[@id='product_attribute_5_12'])[1]"));

        //Verify the price "$1,475.00"
        String expectedPrice = "$1,475.00";
        String actualPrice = getTextFromElement(By.xpath("(//span[text()='$1,475.00'])[1]"));
        verifyText("Expected price is not displayed", expectedPrice, actualPrice);

        //Click on "ADD TO CARD" Button.
        clickOnElement(By.xpath("(//button[normalize-space()='Add to cart'])[1]"));
        Thread.sleep(1000);

        /**
         * Verify the Message "The product has been added to your shopping cart" on Top
         * green Bar
         */
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = getTextFromElement(By.xpath("//div[@class='bar-notification success']"));
        verifyText("Items is not added to the cart", expectedMessage, actualMessage);

        //After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@title='Close']"));

        Thread.sleep(1000);

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement element1 = element(By.xpath("//span[@class='cart-label']"));
        actions().moveToElement(element1).build().perform();
        WebElement element2 = element(By.xpath("//button[normalize-space()='Go to cart']"));
        actions().moveToElement(element2).click().build().perform();

        //Verify the message "Shopping cart"
        expectedMessage = "Shopping cart";
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        verifyText("Expected msg is not displayed", expectedMessage, actualMessage);

        //Change the Qty to "2" and Click on "Update shopping cart"
        Thread.sleep(1000);
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");

        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //Verify the Total"$2,950.00"
        String expectedTotal = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Total is not matching with expected total", expectedTotal, actualTotal);

        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[@id='checkout']"));

        /**
         * Fill the all mandatory field
         * Click on “CONTINUE”
         */
        String expectedMsg = "Welcome, Please Sign In!";
        String actualMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        verifyText("Expected text is not displayed", expectedMsg, actualMsg);

        //Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Michale");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Vine");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "michael12@gmail.com");
        selectByVisibleText(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United States");
        selectByVisibleText(By.xpath("//select[@id='BillingNewAddress_StateProvinceId']"), "Alabama");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Texas");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "123 Abc Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "73301");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "07733558899");
        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        clickOnElement(By.xpath("(//input[@id='shippingoption_1'])[1]"));
        clickOnElement(By.xpath("(//button[@class='button-1 shipping-method-next-step-button'])[1]"));
        clickOnElement(By.xpath("(//input[@id='paymentmethod_1'])[1]"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        Thread.sleep(1000);
        selectByVisibleText(By.xpath("//select[@id='CreditCardType']"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Michael Vine");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5573615091331588");
        selectByVisibleText(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectByVisibleText(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "657");

        // Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        Thread.sleep(1000);

        /**
         * Verify “Payment Method” is “Credit Card”
         */
        String expectedPaymentMethod = "Credit Card";
        String actualPaymentMethod = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        verifyText("Expected payment method is not matching", expectedPaymentMethod, actualPaymentMethod);

        /**
         * Verify “Shipping Method” is “Next Day Air”
         */
        String expectedShippingMethod = "Next Day Air";
        String actualShippingMethod = getTextFromElement(By.xpath("//span[normalize-space()='Next Day Air']"));
        verifyText("Expected payment method is not matching", expectedShippingMethod, actualShippingMethod);

        /**
         * Verify Total is “$2,950.00”
         */
        String expectedFinalTotal = "$2,950.00";
        String actualFinalTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Total is not matching with expected total", expectedFinalTotal, actualFinalTotal);

        //Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        /**
         * Verify the Text “Thank You”
         */
        String expectedTxt = "Thank you";
        String actualTx = getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        verifyText("Expected text is not displayed", expectedTxt, actualTx);

        /**
         * Verify the message “Your order has been successfully processed!”
         */
        String expectedMse = "Your order has been successfully processed!";
        String actualMse = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has " +
                "been successfully processed!']"));
        verifyText("Expected message is not displayed", expectedMse, actualMse);

        //Click on “CONTINUE”
        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        /**
         * Verify the text “Welcome to our store”
         */
        String expectedHomeMsg = "Welcome to our store";
        String actualHomeMsg = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome " +
                "to our store']"));
        verifyText("Expected Homepage message is not displayed", expectedHomeMsg, actualHomeMsg);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
