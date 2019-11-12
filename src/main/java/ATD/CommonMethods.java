package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.testng.Assert.assertEquals;

public class CommonMethods {

    static String testMail = "test@gmail.com";
    static String testNumberThatPutOrderInTest = "200+002";
    public static String password = "atdtest";
    public static String ridex_82B0896 = "82B0896";

    public static void closeCookiesFooterMessage() {
        try {
            $(By.xpath("//div[@class='block-cookies__close']")).click();
        } catch (Exception e) {
            System.out.println("Cookies block doesn't appear");
        }
    }

    public static String getCurrentShopFromJSVarInHTML() {
        String currentShop = executeJavaScript("return $siteSettings.lang");
        return currentShop.toUpperCase();
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
}
