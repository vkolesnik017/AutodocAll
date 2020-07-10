package PKW;

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

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CommonMethods {

    public static String password = "pkwtest";



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
}
