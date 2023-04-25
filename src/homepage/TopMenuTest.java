package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup(){
        openBrowser(baseUrl);
    }


    /**
     * Create method with name "selectMenu" it has one parameter name "menu" of type string
     * This method should click on the menu whatever name is passed as parameter
     */
    public void selectMenu(String menu){
        //Generic X-path for menu navigation
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='" + menu + "']"));
    }

    /**
     * verifyPageNavigation.use selectMenu method to select the Menu and click on it and verify the page navigation.
     */
    @Test
    public void verifyPageNavigation (){
        String menuName = "Electronics";
        String expectedTextOfTheItem = "Electronics";
        selectMenu(menuName);
        String actualTextOfTheItem = getTextFromElement(By.xpath("//h1"));
        verifyText("User is not navigated to expected menu page", expectedTextOfTheItem, actualTextOfTheItem);
    }

    @After
    public void tearDown(){
        closeBrowser();
    }

}
