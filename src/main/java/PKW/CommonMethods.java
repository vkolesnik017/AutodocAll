package PKW;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;

public class CommonMethods {

    public static String password = "pkwtest";
    static String testNumberThatPutOrderInTest = "200+002";


    @Step("Generates random mail")
    public static String mailRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotest" + random + "@mailinator.com";
    }

    @Step("{url} Open page with close popup")
    public static void openPage(String url) {
        System.out.println(url);
        open(url);
        closeCookiesFooterMessage();
    }

    private static void closeCookiesFooterMessage() {
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

    public void writerInFile(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file");
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }

    @Step("Method for waiting while link become contains expected")
    public static void checkingContainsUrl(String expectedContainsUrl) {
        try {
            Wait().until(webDriver -> url().contains(expectedContainsUrl));
        } catch (TimeoutException e) {
            System.out.println(url());
            Assert.fail("Url doesn't contains: " + expectedContainsUrl);
        }
    }

    @Step("Checking counter increase on {increaseCount} of product quantity")
    void checkingCounterIncrease(int increaseCount, SelenideElement counterValue, SelenideElement counterPlus) {
        int startValue = Integer.parseInt(counterValue.getValue());
        for (int i = 1; i <= increaseCount; i++) {
            counterPlus.click();
            sleep(2000);
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
            sleep(2000);
            int valueAfterDecrease = startValue - i;
            counterValue.shouldHave(value(String.valueOf(valueAfterDecrease)));
        }
        int valueAfterAllDecrease = startValue - decreaseCount;
        counterValue.shouldHave(value(String.valueOf(valueAfterAllDecrease)));
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

    @Step("Checking follow url on new tab and close tab")
    public void checkingUrlAndCloseTab(String expectedUrl) {
        switchTo().window(1);
        String actualUrl = url();
        assertTrue(actualUrl.contains(expectedUrl));
        getWebDriver().close();
        switchTo().window(0);
    }

    @Step("Checking datenschutzerklarung link behavior")
    public void checkingDatenschutzerklarungLinkBehavior(SelenideElement datenschutzerklarungLink, String cssValue) {
        datenschutzerklarungLink.shouldHave(attribute("title", "Datenschutzerklärung"));
        datenschutzerklarungLink.shouldHave(cssValue("cursor", "pointer"));
        datenschutzerklarungLink.shouldHave(cssValue("text-decoration", cssValue));
        datenschutzerklarungLink.click();
        checkingUrlAndCloseTab("/datenschutz");
    }

    @Step("Checking Privacy Policy link behavior for shop EN")
    public void checkingPrivacyPolicyLinkBehavior(SelenideElement PrivacyPolicyLink, String cssValue) {
        PrivacyPolicyLink.shouldHave(attribute("title", "Privacy Policy"));
        PrivacyPolicyLink.shouldHave(cssValue("cursor", "pointer"));
        PrivacyPolicyLink.shouldHave(cssValue("text-decoration", cssValue));
        PrivacyPolicyLink.click();
        checkingUrlAndCloseTab("/privacy-policy");
    }

    @Step("Wait while route contains expected condition {expected route}")
    public static void waitWhileRouteContainsExpectedCondition(String expectedCondition) {
        try {
            Wait().until(WebDriver -> getCurrentUtl().contains(expectedCondition));
        } catch (TimeoutException e) {
            Assert.fail("Current route: [" + getCurrentUtl() + "] don't contains expected condition: " + expectedCondition);
        }
    }

    public static String getCurrentUtl() {
        return url();
    }
}
