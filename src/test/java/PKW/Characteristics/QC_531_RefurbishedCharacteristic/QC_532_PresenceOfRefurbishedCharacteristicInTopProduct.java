package PKW.Characteristics.QC_531_RefurbishedCharacteristic;

import PKW.Parts_group_page_Logic;
import PKW.Supplier_brand_parts_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_532_PresenceOfRefurbishedCharacteristicInTopProduct {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "parts_group,supplier_brand_parts");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic in mini cart of TOP product")
    public void testChecksPresenceOfRefurbishedCharacteristicInTopProduct(String route) {
        openPage(route);
        new Parts_group_page_Logic().presenceRefurbishedCharacteristicInTopProduct("Zustand:  Wiederaufbereitet");
    }

    @DataProvider(name = "routesArticle", parallel = true)
    Object[] dataProviderArticle() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "parts_group2");
    }

    @Test(dataProvider = "routesArticle")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic in mini cart of TOP product")
    public void testChecksPresenceOfRefurbishedCharacteristicInTopProductArticle(String route) {
        openPage(route);

        new Parts_group_page_Logic().presenceRefurbishedCharacteristicInTopProductWithArticle("Zustand:  Wiederaufbereitet", "R");
    }

    @DataProvider(name = "routesArticleBrand", parallel = true)
    Object[] dataProviderArticleBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_parts2");
    }

    @Test(dataProvider = "routesArticleBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic in mini cart of TOP product")
    public void testChecksPresenceOfRefurbishedCharacteristicInTopProductArticleBrand(String route) {
        openPage(route);

        new Supplier_brand_parts_page_Logic().presenceRefurbishedCharacteristicInTopProductWithArticle("Zustand:  Wiederaufbereitet", "R");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
