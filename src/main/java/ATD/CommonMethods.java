package ATD;

import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

    public static String getNameRouteFromJSVarInHTML() {
        return executeJavaScript("return $siteSettings.route");
    }

    public static String getShopFromRoute(String route) {
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

    @Step
    //Method for checking for contains URL
    public static void checkingContainsUrl(String expectedContainsUrl) {
        try {
            Wait().until(webDriver -> url().contains(expectedContainsUrl));
        } catch (TimeoutException e) {
            System.out.println(url());
            Assert.fail("Url doesn't contains: " + expectedContainsUrl);
        }
    }

    @Step
    //Method for waiting while link become expected
    public static void waitingWhileLinkBecomeExpected(String expectedEqualsUrl) {
        try {
            Wait().until(webDriver -> url().equals(expectedEqualsUrl));
        } catch (TimeoutException e) {
            System.out.println(url());
            Assert.fail("Url doesn't equals: " + expectedEqualsUrl);
        }
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

    static SelenideElement universalElementOfBuyBtnForAllPages() {
        return $(byXpath("//a[contains(@class,'add_')]"));
    }

    // open vertical selector if it hidden
    public static void openVerticalSelectorIfItHidden() {
        SelenideElement hiddenVerticalSelector = $(".catalog-title__change-car");
        if (hiddenVerticalSelector.isDisplayed()) {
            hiddenVerticalSelector.click();
        }
    }

    @Step
    // method for test adding product to basket from all routes
    public static void clickOfBuyBtnForAllPages() {
        SelenideElement productBlockForHover = $(byCssSelector(".rec_products_block"));
        try {
            if (productBlockForHover.isDisplayed()) {
                productBlockForHover.hover();
            }
            sleep(5000); // TODO try delete this sleep after fixed SITES-2830
            universalElementOfBuyBtnForAllPages().waitUntil(visible, 2000).click();
        } catch (ElementShould e) {
            $(byXpath("//div[@class='top-small-products__items']//a[contains(@class,'add_')]")).click();
        }
        sleep(3000); // TODO try delete this sleep after fixed SITES-2830
    }

    @Step
    public static void scrollToBlockOfTopProducts() {
        SelenideElement titleOfBlockOfTopProducts = $(byXpath("//*[@class='title_list'] | //*[@class='top-small-products__title']"));
        titleOfBlockOfTopProducts.scrollTo();
        universalElementOfBuyBtnForAllPages().shouldBe(visible);
    }

    @Step
    // method for block of top products
    public static void checksProductsNotInStockInBlockOfTopProducts() {
        SelenideElement arrowRightBtnInTopProductsBlock = $(byXpath("(//*[@type='button'])[2]"));
        SelenideElement grayBtn = $(byXpath("//*[contains(@class,'not_active')]/a"));
        universalElementOfBuyBtnForAllPages().shouldBe(visible);
        if (arrowRightBtnInTopProductsBlock.isDisplayed()) {
            while (arrowRightBtnInTopProductsBlock.attr("aria-disabled").equals("false")) {
                grayBtn.shouldBe(not(visible));
                arrowRightBtnInTopProductsBlock.click();
            }
        }
        grayBtn.shouldBe(not(visible));
    }

    @Step
    // method for checks elements in block of top products
    public static void checksPresenceElementsInMiniCardInBlocksOfTopProducts() {

        By sticker = (byCssSelector(".product-list__item__promotion"));
        By oldPrice = (byCssSelector(".product-list__item__old-price"));
        By image = (byCssSelector(".ovVisLi_image"));
        By productName = (byCssSelector(".product-list__item__title"));
        By articleNumber = (byCssSelector(".product-list__item__nummer"));
        By price = (byCssSelector(".product-list__item__price"));
        By infoVatAndDelivery = (byCssSelector(".product-list__item__info"));

        ElementsCollection miniCardsOfProducts = $$(byXpath("//*[contains(@class,'product-list__item ')]")).filterBy(visible).shouldHaveSize(4);
        for(SelenideElement miniCard : miniCardsOfProducts) {
            miniCard.$(sticker).should(visible);
            miniCard.$(oldPrice).should(visible);
            miniCard.$(image).should(visible);
            miniCard.$(productName).should(visible);
            miniCard.$(articleNumber).should(visible);
            miniCard.$(price).should(visible);
            miniCard.$(infoVatAndDelivery).should(visible);
        }
    }

    @Step("Comparing actual and expected characteristics")
    @Description("The method gets characteristics from ElementsCollection and compare their with characteristics from ArrayList")
    public void compareCharacteristics(ElementsCollection actualCharacteristics, List<String> expectedCharacteristics) {
        for (int a = 0; a < expectedCharacteristics.size(); a++) {
            actualCharacteristics.get(a).shouldHave(matchText(expectedCharacteristics.get(a)));
        }
    }

    public void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file");
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }

}
