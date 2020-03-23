package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
    public static String idPfandProduct = "1145093";
    public static String idProductWithDynamicChar = "2295352";

    @Step("{url} Open page with close popup")
    public static void openPage(String url) {
        open(url);
        closeCookiesFooterMessage();
    }

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

    @Step("Wait while route become expected {expected route}")
    public static void waitWhileRouteBecomeExpected(String expectedRoute) {
        try {
            Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals(expectedRoute));
        } catch (TimeoutException e) {
            Assert.fail("Current route: [" + getNameRouteFromJSVarInHTML() + "] don't equals expected route: " + expectedRoute);
        }

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
        return "autotest" + random + "@test.com";
    }

    @Step("Checking datenschutzerklarung link behavior")
    public void checkingDatenschutzerklarungLinkBehavior(SelenideElement datenschutzerklarungLink, String cssValue) {
        datenschutzerklarungLink.shouldHave(attribute("title", "Datenschutzerklärung"));
        datenschutzerklarungLink.shouldHave(cssValue("cursor", "pointer"));
        datenschutzerklarungLink.shouldHave(cssValue("text-decoration", cssValue));
        datenschutzerklarungLink.click();
        checkingUrlAndCloseTab("https://www.autodoc.de/services/datenschutz");
    }

    @Step("Generates random email on @mailinator.com")
    public static String mailRandomMailinator() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestMail" + random + "@mailinator.com";
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
    public void checkingUrl(String expectedUrl) {
        String actualUrl = url();
        Assert.assertEquals(actualUrl, expectedUrl);
        back();
    }

    @Step
    //Method for waiting while link become contains expected
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
    public void checkingUrlAndCloseTab(String expectedUrl) {
        switchTo().window(1);
        String actualUrl = url();
        assertTrue(actualUrl.contains(expectedUrl));
        getWebDriver().close();
        switchTo().window(0);
    }

    @Step
    // Pulling prices from text of element
    public static Float getPriceFromElement(SelenideElement element) {
        element.shouldBe(visible);
        return Float.parseFloat(element.text().replaceAll("[^0-9,]", "").replace(",", "."));
    }

    public static SelenideElement universalElementOfBuyBtnForAllPages() {
        return $(byXpath("//a[contains(@class,'add_')]"));
    }

    @Step("Close any popup by click overlay")
    public static void closeAnyPopupByClickOverlay() {
        By overlay = (byXpath("//div[@class='overlay black']"));
        $(overlay).click(1, 1);
        $(overlay).shouldBe(not(visible));
    }

    @Step
    // method for test adding product to basket from all routes
    public static void clickOfBuyBtnForAllPages() {
        SelenideElement productBlockForHover = $(byCssSelector(".rec_products_block"));
        try {
            sleep(2000); // TODO try delete this sleep if fixed SITES-2830
            if (productBlockForHover.isDisplayed()) {
                productBlockForHover.hover();
            }
            sleep(3000); // TODO try delete this sleep if fixed SITES-2830
            universalElementOfBuyBtnForAllPages().waitUntil(visible, 2000).click();
        } catch (ElementShould e) {
            try {
                $(byXpath("//div[@class='top-small-products__items']//a[contains(@class,'add_')]")).waitUntil(visible, 3000).click();
            } catch (ElementNotFound e2) {
                // for tires listing
                $(byXpath("(//*[@data-ga-action='Add_to_basket'])[5]")).click();
            }
        }
        sleep(4000); // TODO try delete this sleep if fixed SITES-2830
    }

    // methods and locators for block of top products
    private SelenideElement titleOfBlockOfTopProducts() {
        return $x("//*[@class='title_list'] | //*[@class='top-small-products__title']");
    }

    private SelenideElement arrowRightBtnInTopProductsBlock() {
        return $(byXpath("(//*[@type='button'])[2]"));
    }

    public SelenideElement detailsButtonInTopProductsBlock() {
        return $(".linkShowPopup ");
    }

    // fits for all pages
    private SelenideElement grayBtn() {
        return $(byXpath("//*[contains(@class,'not_active')]/a"));
    }

    private ElementsCollection miniCardsOfProducts() {
        return $$(byXpath("//*[contains(@class,'product-list__item ')]"));
    }

    private By recoveryCharacteristicInBlockOfTopProducts = By.cssSelector(".default_ul_li_class");

    @Step("Scrolling to block of top products")
    public CommonMethods scrollToBlockOfTopProducts() {
        titleOfBlockOfTopProducts().scrollTo();
        return this;
    }

    @Step
    public void checksProductsNotInStockInBlockOfTopProducts() {
        universalElementOfBuyBtnForAllPages().shouldBe(visible);
        if (arrowRightBtnInTopProductsBlock().isDisplayed()) {
            while (arrowRightBtnInTopProductsBlock().attr("aria-disabled").equals("false")) {
                grayBtn().shouldBe(not(visible));
                arrowRightBtnInTopProductsBlock().click();
            }
        }
        grayBtn().shouldBe(not(visible));
    }

    @Step
    // method for checks output recovery characteristic in block of top products for QASYS_536 (TEST-1)
    public void checksOutputRecoveryCharacteristicInBlocksOfTopProducts(String expectedChar) {
        ArrayList<String> actualCharacteristics = new ArrayList<>();
        scrollToBlockOfTopProducts();
        ElementsCollection miniCardsInTopBlock = miniCardsOfProducts().filter(visible).shouldHaveSize(4);
        for (SelenideElement el : miniCardsInTopBlock) {
            el.hover();
            String text = el.$(recoveryCharacteristicInBlockOfTopProducts).shouldBe(visible).getText().replaceAll("\n", "");
            actualCharacteristics.add(text);
            titleOfBlockOfTopProducts().hover();
        }
        arrowRightBtnInTopProductsBlock().click();
        ElementsCollection miniCardsInTopBlockTwoSlide = miniCardsOfProducts().filter(visible).shouldHaveSize(4);
        for (SelenideElement el : miniCardsInTopBlockTwoSlide) {
            el.hover();
            String text = el.$(recoveryCharacteristicInBlockOfTopProducts).shouldBe(visible).getText().replaceAll("\n", "");
            actualCharacteristics.add(text);
            titleOfBlockOfTopProducts().hover();
        }
        assertTrue(actualCharacteristics.contains(expectedChar), "not in a single product is not output the recovery characteristic " + expectedChar + " in the block of top product");
    }

    @Step("Method for checks elements in mini card in block of top products")
    public void checksPresenceElementsInMiniCardInBlocksOfTopProducts() {

        By sticker = (byCssSelector(".product-list__item__promotion"));
        By oldPrice = (byCssSelector(".product-list__item__old-price"));
        By image = (byCssSelector(".ovVisLi_image"));
        By productName = (byCssSelector(".product-list__item__title"));
        By articleNumber = (byCssSelector(".product-list__item__nummer"));
        By price = (byCssSelector(".product-list__item__price"));
        By infoVatAndDelivery = (byCssSelector(".product-list__item__info"));
        SelenideElement sliderPrev = $x("//button[@class='slick-prev slick-arrow']");
        SelenideElement sliderNext = $x("//button[@class='slick-next slick-arrow']");

        ElementsCollection miniCardsOfProducts = miniCardsOfProducts().filterBy(visible).shouldHaveSize(4);
        for (SelenideElement miniCardFirsSlide : miniCardsOfProducts) {
            miniCardFirsSlide.$(sticker).should(visible);
            miniCardFirsSlide.$(oldPrice).should(visible);
            miniCardFirsSlide.$(image).should(visible);
            miniCardFirsSlide.$(productName).should(visible);
            miniCardFirsSlide.$(articleNumber).should(visible);
            miniCardFirsSlide.$(price).should(visible);
            miniCardFirsSlide.$(infoVatAndDelivery).should(visible);
        }
        sliderNext.click();
        sleep(2000);
        for (SelenideElement miniCardSecondSlide : miniCardsOfProducts) {
            miniCardSecondSlide.$(sticker).should(visible);
            miniCardSecondSlide.$(oldPrice).should(visible);
            miniCardSecondSlide.$(image).should(visible);
            miniCardSecondSlide.$(productName).should(visible);
            miniCardSecondSlide.$(articleNumber).should(visible);
            miniCardSecondSlide.$(price).should(visible);
            miniCardSecondSlide.$(infoVatAndDelivery).should(visible);
        }
        sliderPrev.click();
    }

    @Step("Comparing actual and expected characteristics")
    //The method gets characteristics from ElementsCollection and compare their with characteristics from ArrayList
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

    //Methods for checking Counter Product
    @Step("Checking counter increase of paired product quantity")
    void checkingCounterIncreaseForPaired(String startCount, SelenideElement value, SelenideElement counterPlus) {
        value.shouldHave(value(startCount));
        counterPlus.click();
        String countAfterIncrease = String.valueOf(Integer.parseInt(startCount) + 2);
        value.shouldHave(value(countAfterIncrease));
        sleep(2000);
    }

    @Step("Checking counter decrease of paired product quantity")
    void checkingCounterDecreaseForPaired(String startCount, SelenideElement value, SelenideElement counterMinus) {
        value.shouldHave(value(startCount));
        counterMinus.click();
        String countAfterDecrease = String.valueOf(Integer.parseInt(startCount) - 2);
        value.shouldHave(value(countAfterDecrease));
        sleep(2000);
    }

    @Step("Checking counter increase on {increaseCount} of product quantity")
    void checkingCounterIncrease(int increaseCount, SelenideElement counterValue, SelenideElement counterPlus) {
        int startValue = Integer.parseInt(counterValue.getValue());
        for (int i = 1; i <= increaseCount; i++) {
            counterPlus.click();
            int valueAfterIncrease = startValue + i;
            counterValue.shouldHave(value(String.valueOf(valueAfterIncrease)));
        }
        int valueAfterAllIncrease = startValue + increaseCount;
        counterValue.shouldHave(value(String.valueOf(valueAfterAllIncrease)));
    }

    @Step("Checking counter decrease on {decreaseCount} of product quantity")
    void checkingCounterDecrease(int decreaseCount, SelenideElement counterValue, SelenideElement counterMinus) {
        int startValue = Integer.parseInt(counterValue.getValue());
        for (int i = 1; i <= decreaseCount; i++) {
            counterMinus.click();
            int valueAfterDecrease = startValue - i;
            counterValue.shouldHave(value(String.valueOf(valueAfterDecrease)));
        }
        int valueAfterAllDecrease = startValue - decreaseCount;
        counterValue.shouldHave(value(String.valueOf(valueAfterAllDecrease)));
    }

    @Step("Waiting until element will be visible")
    void waitingElementVisibility(SelenideElement element, int minute) {
        minute = (minute * 60 * 1000) / 5;
        for (int i = 1; i <= 5; i++) {
            try {
                element.waitUntil(Condition.visible, minute);
            } catch (ElementNotFound e) {
                System.out.println("Retry number " + i + ". Element doesn't visible");
                refresh();
                if (i == 5) Assert.fail("After 5 retrying " + element);
            }
        }
    }
}
