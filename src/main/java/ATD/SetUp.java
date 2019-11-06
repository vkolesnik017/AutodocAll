package ATD;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.ArrayList;
import java.util.List;

public class SetUp {

    private String shopFromJenkins = System.getenv("ShopFromJenkins");

    public static void setUpBrowser(Boolean Selenoid, String browser, String browserVersion) {
        Configuration.browser = (browser);
        Configuration.browserVersion = (browserVersion);
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = false;
        Configuration.timeout = 10000;
        if (Selenoid) {
            Configuration.remote = "http://192.168.99.100:4444/wd/hub";
//            Configuration.driverManagerEnabled = false;
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            Configuration.browserCapabilities = capabilities;
        }
    }

    public Object[] setUpShop(String chooseTestOrProd, String shopFromTest) {
        String testOrProd = null;
        String shop;
        if (!(shopFromJenkins == null)) {
            shop = shopFromJenkins;
        } else {
            shop = shopFromTest;
        }
        if (chooseTestOrProd.equalsIgnoreCase("test")) {
            testOrProd = "https://test.";
        } else if (chooseTestOrProd.equalsIgnoreCase("prod")) {
            testOrProd = "https://www.";
        }
        List<String> finalRouteList = new ArrayList<>();
        try {
            List<String> routeFromDB = new DataBase().getRouteForMain(shop);
            for (String aRouteFromDB : routeFromDB) {
                finalRouteList.add(testOrProd + aRouteFromDB);
            }
        } catch (Exception e) {
            System.out.println("setUpShop failed...");
        }
        return finalRouteList.toArray();
    }

    public Object[] setUpShopWithListParam(String chooseTestOrProd, String shopFromTest, String[] list) {
        Object[] shop = setUpShop(chooseTestOrProd, shopFromTest);
        List<String> shopList = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        for (Object shopPars : shop) {
            shopList.add(String.valueOf(shopPars));
        }
        for (String aList : list) {
            for (String aShopList : shopList) {
                finalList.add(aShopList + aList);
            }
        }
        return finalList.toArray();
    }

}
