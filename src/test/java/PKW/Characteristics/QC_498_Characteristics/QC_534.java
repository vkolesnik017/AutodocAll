package PKW.Characteristics.QC_498_Characteristics;

import PKW.Product_page_Logic;
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

public class QC_534 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Refurbished characteristic in basket")
    public void testChecksPresenceOfRefurbishedCharacteristicInBasket(String route) {
        openPage(route);

        new Product_page_Logic().addProductToBasket().cartClick().presenceOfAddedProductList().presenceOfExpectedCharacteristic("Zustand: Wiederaufbereitet");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
