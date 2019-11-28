package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CommonMethods {

    public static String testMail = "test@gmail.com";
    static String testNumberThatPutOrderInTest = "200+002";
    public static String password = "atdtest";
    public static String ridex_82B0896 = "82B0896";
    public static String usualIdProduct = "8340509";
    public static String idProductTire = "8075786";
    public static String idProductMore35EUR = "1367459";

    public static void closeCookiesFooterMessage() {
        try {
            $(byXpath("//div[@class='block-cookies__close']")).click();
        } catch (UIAssertionError e) {
            System.out.println("Cookies block doesn't appear");
        }
    }

    public static String getCurrentShopFromJSVarInHTML() {
        String currentShop = executeJavaScript("return $siteSettings.lang");
        return currentShop.toUpperCase();
    }

    static String getShopFromRoute(String route) {
        String shop = null;
        String[] words = route.split("\\.");
        if (words.length == 4) {
            if (words[3].equals("no")) shop = "NO";
            else if (words[3].equals("uk")) shop = "EN";
        } else {
            if (words[2].equals("lu")) shop = "LD";
            else shop = words[2].toUpperCase();
        }
        return shop;
    }

    public static String getRandomNumber() {
        int n = (int) Math.round(Math.random() * 1000000);
        return String.valueOf(n);
    }

    public static String firstNameRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestFirstName" + random;
    }

    public static String secondNameRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestSecondName" + random;
    }

    public static String mailRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestMail" + random + "@test.com";
    }

    @Step("Get currency {nameLocator} and verify")
    public static void getCurrencyAndVerify(SelenideElement currencyLocator, String nameLocator, String shop, String expectedCurrency) {
        String actualCurrency;
        if (shop.equals("EN")) {
            actualCurrency = currencyLocator.getText().split("\\s")[0];
        } else {
            actualCurrency = currencyLocator.getText().split("\\s")[1];
        }
        assertEquals(actualCurrency, expectedCurrency, "Currency in " + nameLocator);
    }

    //Checks element clickability
    public static Condition clickable = and("can be clicked", visible, enabled);

    //Method checking follow url
    public void checkingUrl(String expectedUrl){
        String actualUrl = url();
        Assert.assertEquals(actualUrl, expectedUrl);
        back();
    }

    //Method checking follow url on new tab and close tab
    public void checkingUrlAndCloseTab(String expectedUrl){
        switchTo().window(1);
        String actualUrl = url();
        assertTrue(actualUrl.contains(expectedUrl));
        getWebDriver().close();
        switchTo().window(0);
    }

    @Step
    // Pulling prices from text of element
    public static Float getPriceFromElement(SelenideElement element) {
        return Float.parseFloat(element.text().replaceAll("[^0-9,]", "").replace(",", "."));
    }

    @Step("Choose car in selector")
    public void chooseCarInSelector(String brand, String model, String type, Boolean clickSearchButton) {
        $("#form_maker_id").selectOptionByValue(brand);
        $("#form_model_id").selectOptionByValue(model);
        $("#form_car_id").selectOptionByValue(type);
        if(clickSearchButton) {
            $(".search_button").click();
        }
    }

    @Step
    public static void clickOfBuyBtnAndGetHisIdForAllPages() {
        SelenideElement productBlockForHover = $(byCssSelector(".rec_products_block"));
        try {
            if (productBlockForHover.isDisplayed()) {
                productBlockForHover.hover();
            }
            $(byXpath("//a[contains(@class,'add_')]")).waitUntil(visible, 2000).click();
        } catch (ElementShould e) {
            $(byXpath("//div[@class='top-small-products__items']//a[contains(@class,'add_')]")).click();
        }
        new Product_page().firstProductPriceInPopupOfCart().shouldBe(visible);
    }

}
