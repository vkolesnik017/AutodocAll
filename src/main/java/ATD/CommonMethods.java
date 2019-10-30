package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.and;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

class CommonMethods {

    static String testMail = "test@gmail.com";
    static String testNumberThatPutOrderInTest = "200+002";
    static String password = "atdtest";
    static String ridex_82B0896 = "82B0896";

    static void closeCookiesFooterMessage() {
        try {
            $(By.xpath("//div[@class='block-cookies__close']")).click();
        } catch (Exception e) {
            System.out.println("Cookies block doesn't appear");
        }
    }

    static String firstNameRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestFirstName" + random;
    }

    static String secondNameRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestSecondName" + random;
    }

    static String mailRandom() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt();
        return "autotestMail" + random + "@test.com";
    }

    //Checks element clickability
    static Condition clickable = and("can be clicked", visible, enabled);
}
