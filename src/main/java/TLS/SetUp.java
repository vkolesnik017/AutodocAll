package TLS;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetUp {

    private DataBase db = new DataBase();
    private String shopFromJenkins = System.getenv("ShopFromJenkins");
    private String envFromJenkins = System.getenv("EnvFromJenkins");
    private String shopsDesktop = "DE,AT,CH,DK,ES,FI,FR,IT,NL,NO,PT,SE";
    public String getShopsDesktop() {
        return shopsDesktop;
    }


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

    public Object[] setUpShop(String envFromTest, String shopFromTest) {
        String shop;
        if (!(shopFromJenkins == null)) shop = shopFromJenkins;
        else shop = shopFromTest;
        if (!(envFromJenkins == null)) envFromTest = envFromJenkins;
        String env = getEnv(envFromTest);
        List<String> finalRouteList = new ArrayList<>();
        try {
            List<String> routeFromDB = new DataBase().getRouteListForMain(shop);
            for (String aRouteFromDB : routeFromDB) {
                finalRouteList.add(env + aRouteFromDB);
            }
        } catch (Exception e) {
            System.out.println("setUpShop failed...");
        }
        return finalRouteList.toArray();
    }

    // Return list routes By Shops and route setUpShopWithRoute("prod", "AT,DE,CH", "lkw_main")
    public Object[] setUpShopsWithMainRoute(String envFromTest, String shopFromTest, String routeName) {
        String shop;
        if (!(shopFromJenkins == null)) shop = shopFromJenkins;
        else shop = shopFromTest;
        if (!(envFromJenkins == null)) envFromTest = envFromJenkins;
        String env = getEnv(envFromTest);
        List<String> finalRouteList = new ArrayList<>();
        try {
            List<String> routeFromDB = new DataBase().getRouteListByRouteName(shop, routeName);
            for (String aRouteFromDB : routeFromDB) {
                finalRouteList.add(env + aRouteFromDB);
            }
        } catch (Exception e) {
            System.out.println("setUpShop failed...");
        }
        return finalRouteList.toArray();
    }

    // Return list Shop + subroutes By Shop, main route and list subroutes ("prod", "DE", "lkw_main", "lkw_category_car_list,lkw_category_car_list2")
    public Object[] setUpShopWithSubroutes(String envFromTest, String shopFromTest, String routeName, String subRoutes) throws SQLException {
        String env = getEnv(envFromTest);
        List<String> mainRouteList = new ArrayList<>(db.getRouteListByRouteName(shopFromTest, routeName));
        List<String> subRoutesList = new ArrayList<>();
        List<String> finalSubRoutesList = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        String[] subRoute = subRoutes.split("\\,");
        //Adding String subRoutes in list subRoutesList
        Collections.addAll(subRoutesList, subRoute);
        for (String subRoutesParce : subRoutesList) {
            //Adding subRoutes in list getSubRoutesList
            List<String> getSubRoutesList = db.getRouteListByRouteName(shopFromTest, subRoutesParce);
            if (subRoutesParce.contains("main")) getSubRoutesList = Collections.singletonList("");
            finalSubRoutesList.addAll(getSubRoutesList);
        }
        for (String aSubRoutesList : finalSubRoutesList) {
            for (String aMainRouteList : mainRouteList) {
                finalList.add(env + aMainRouteList + "/" + aSubRoutesList);
            }
        }
        return finalList.toArray();
    }

    // Return list Shops + subroute By Shops, main route and subroute ("prod", "DE", "lkw_main", "lkw_category_car_list,lkw_category_car_list2")
    public Object[] setUpShopsWithSubroute(String envFromTest, String shopFromTest, String routeName, String subRoutes) throws SQLException {
        String env = getEnv(envFromTest);
        List<String> mainRouteList = new ArrayList<>(db.getRouteListByRouteName(shopFromTest, routeName));
        List<String> subRoutesList = new ArrayList<>(db.getRouteListByRouteName(shopFromTest, subRoutes));
        List<String> finalList = new ArrayList<>();
        for (int i = 0; i < mainRouteList.size(); i++) {
            String route = env + mainRouteList.get(i) + "/" + subRoutesList.get(i);
            finalList.add(route);
        }
        return finalList.toArray();
    }

    // Return list Shop_param By Shops and String[] list setUpShopWithListParam("prod", "AT,DE,CH", list[])
    public Object[] setUpShopWithListParam(String envFromTest, String shopFromTest, String[] list) {
        Object[] shop = setUpShop(envFromTest, shopFromTest);
        List<String> shopList = new ArrayList<>();
        List<String> finalList = new ArrayList<>();
        for (Object shopPars : shop) {
            shopList.add(String.valueOf(shopPars));
        }
        for (String aList : list) {
            for (String aShopList : shopList) {
                finalList.add(aShopList + "_" + aList);
            }
        }
        return finalList.toArray();
    }


    String getEnv(String envFromTest) {
        String env = null;
        switch (envFromTest) {
            case ("test"):
                env = "https://test.";
                break;
            case ("prod"):
                env = "https://www.";
                break;
            case ("subprod"):
                env = "https://";
                break;
            case ("mob"):
                env = "https://m.";
                break;
        }
        return env;
    }
}
