package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

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

    public static String getRandomNumber() {
        int n = (int) Math.round(Math.random()*1000000);
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

    //Checks element clickability
    public static Condition clickable = and("can be clicked", visible, enabled);
}
