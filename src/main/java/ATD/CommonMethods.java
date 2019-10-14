package ATD;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

class CommonMethods {

    static String testMail = "test@gmail.com";
    static String testNumberThatPutOrderInTest = "200+002";

    static void setUpBrowser(Boolean Selenoid, String browser, String browserVersion) {
        Configuration.browser = (browser);
        Configuration.browserVersion = (browserVersion);
        Configuration.startMaximized = true;
        Configuration.pollingInterval = 1000;
        if (Selenoid) {
            Configuration.remote = "http://192.168.99.100:4444/wd/hub";
//            Configuration.driverManagerEnabled = false;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            Configuration.browserCapabilities = capabilities;
        }
    }

    Object[] setUpShop(String chooseTestOrProd, String shopFromTest) {
        String testOrProd = null;
        if (chooseTestOrProd.equalsIgnoreCase("test")) {
            testOrProd = "https://test.";
        } else if (chooseTestOrProd.equalsIgnoreCase("prod")) {
            testOrProd = "https://www.";
        }
        List<String> finalRouteList = new ArrayList<>();
        try {
            List<String> routeFromDB = new DataBase().getRoute(shopFromTest);
            for (String aRouteFromDB : routeFromDB) {
                finalRouteList.add(testOrProd + aRouteFromDB);
            }
        } catch (Exception e) {
            System.out.println("setUpShop failed...");
        }
        return finalRouteList.toArray();
    }


    static void closeCookiesFooterMessage() {
        try {
            $(By.xpath("//div[@class='block-cookies__close']")).click();
        } catch (Exception e) {
            System.out.println("Cookies block doesn't appear");
        }
    }
}
