package electronics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() throws InterruptedException {

        //Mouse Hover on “Electronics” Tab
        actions().moveToElement(element(By.xpath("//ul[@class='top-menu notmobile']" +
                "//a[normalize-space()='Electronics']"))).build().perform();
        Thread.sleep(3000);
        //Mouse Hover on “Cell phones” and click
        actions().moveToElement(element(By.xpath("//ul[@class='top-menu notmobile']" +
                "//a[normalize-space()='Cell phones']"))).click().build().perform();

        /**
         * Verify the text “Cell phones”
         */
        String expectedText = "Cell phones";
        String actualTxt = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyText("The expected text 'Cell phones' is not displayed", expectedText, actualTxt);


    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        //Mouse Hover on “Electronics” Tab
        actions().moveToElement(element(By.xpath("//ul[@class='top-menu notmobile']" +
                "//a[normalize-space()='Electronics']"))).build().perform();
        Thread.sleep(1000);
        //Mouse Hover on “Cell phones” and click
        actions().moveToElement(element(By.xpath("//ul[@class='top-menu notmobile']" +
                "//a[normalize-space()='Cell phones']"))).click().build().perform();
        /**
         * Verify the text “Cell phones”
         */
        String expectedMessage = "Cell phones";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Cell phones']"));
        verifyText("Expected text is not displayed", expectedMessage, actualMessage);

        //Click on List View Tab
        clickOnElement(By.xpath("//a[normalize-space()='List']"));
        Thread.sleep(1000);
        //Click on product name “Nokia Lumia 1020” link
        clickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));

        /**
         * Verify the text “Nokia Lumia 1020”
         */
        String expectedText = "Nokia Lumia 1020";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));
        verifyText("The expected text 'Nokia Lumia 1020' is not displayed", expectedText, actualText);

        /**
         * Verify the price “$349.00”
         */
        String expectedPrice = "$349.00";
        String actualPrice = getTextFromElement(By.xpath("//span[@id='price-value-20']"));
        verifyText("The expected price '$349.00' is not displayed", expectedPrice, actualPrice);

        //Change quantity to 2
        sendTextToElement(By.xpath("(//input[@id='product_enteredQuantity_20'])[1]"), "2");

        //Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));
        Thread.sleep(1000);

        /**
         * Verify the Message "The product has been added to your shopping cart" on Top
         * green Bar
         */
        String expMessage = "The product has been added to your shopping cart";
        String actMessage = getTextFromElement(By.xpath("//div[@id='bar-notification']"));
        verifyText("Items is not added to the cart", expMessage, actMessage);

        // After that close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@title='Close']"));

        Thread.sleep(1000);

        //Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        WebElement element1 = element(By.xpath("(//span[@class='cart-label'])[1]"));
        actions().moveToElement(element1).build().perform();
        WebElement element2 = element(By.xpath("//button[normalize-space()='Go to cart']"));
        actions().moveToElement(element2).click().build().perform();
        Thread.sleep(1000);

        /**
         * Verify the message "Shopping cart"
         */
        String expectedMsg = "Shopping cart";
        String actualMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        verifyText("Shopping cart is not displayed", expectedMsg, actualMsg);

        /**
         * Verify the quantity is 2
         */
        String expectedQuantity = "2";
        String actualQuantity = getAttributeValueOfElement(By.xpath("//input[contains(@id,'itemquantity')]"));
        verifyText("Expected quantity is not displayed", expectedQuantity, actualQuantity);

        /**
         * Verify the Total $698.00
         */
        String expectedTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Expected price is not displayed", expectedTotal, actualTotal);

        //click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        /**
         * Click on “CHECKOUT”
         */
        String expectedTxt = "Welcome, Please Sign In!";
        String actualTxt = getTextFromElement(By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));
        verifyText("Expected msg is not displayed.", expectedTxt, actualTxt);

        //Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[normalize-space()='Register']"));

        /**
         * Verify the text “Register”
         */
        String expectedT = "Register";
        String actualT = getTextFromElement(By.xpath("//h1[normalize-space()='Register']"));
        verifyText("Expected text is not displayed.", expectedT, actualT);

        //Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Michael");
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Vine");
        sendTextToElement(By.xpath("//input[@id='Email']"), "michale11@gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "Asdf1234");
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "Asdf1234");

        // Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        // Verify the message “Your registration completed”
        expectedMessage = "Your registration completed";
        actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        verifyText("Expected text is not displayed", expectedMessage, actualMessage);
        // Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));


        System.out.println("-----------------Shopping cart is showing empty------------------");

        expectedMessage = "Shopping cart";
        actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Shopping cart']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));
        Thread.sleep(1000);
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Prime");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Testing");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "primetesting@gmail.com");
        selectByVisibleText(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "India");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Mehsana");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "Tintodan");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "382865");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "9898989898");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        Thread.sleep(1000);

        selectRadioButton(By.xpath("//input[@id='shippingoption_2']"));
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));
        selectRadioButton(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));
        Thread.sleep(1000);

        selectByVisibleText(By.xpath("//select[@id='CreditCardType']"), "Visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Prime testing");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5573615091331588");
        selectByVisibleText(By.xpath("//select[@id='ExpireMonth']"), "05");
        selectByVisibleText(By.xpath("//select[@id='ExpireYear']"), "2025");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "123");
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));
        Thread.sleep(1000);

        expectedMessage = "Credit Card";
        actualMessage = getTextFromElement(By.xpath("//span[normalize-space()='Credit Card']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        expectedMessage = "2nd Day Air";
        actualMessage = getTextFromElement(By.xpath("//span[normalize-space()='2nd Day Air']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        expectedMessage = "$698.00";
        actualMessage = getTextFromElement(By.xpath("//span[@class='product-subtotal']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        String expectedMessage9 = "Thank you";
        String actualMessage9 = getTextFromElement(By.xpath("//h1[normalize-space()='Thank you']"));
        verifyText("Expected text is not matching", expectedMessage9, actualMessage9);


        expectedMessage = "Your order has been successfully processed!";
        actualMessage = getTextFromElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        expectedMessage = "Welcome to our store";
        actualMessage = getTextFromElement(By.xpath("//h2[normalize-space()='Welcome to our store']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

        expectedMessage = "Log out";
        actualMessage = getTextFromElement(By.xpath("//a[normalize-space()='Log out']"));
        verifyText("Expected text is not matching", expectedMessage, actualMessage);

    }


    @After
    public void tearDown() {
        closeBrowser();
    }
}
