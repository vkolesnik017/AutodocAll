package EU;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CommonMethods {

    public static String getCurrentShopFromJSVarInHTML() {
        String currentShop = executeJavaScript("return $siteSettings.lang");
        return currentShop.toUpperCase();
    }
}
