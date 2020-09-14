package ATD.Tyres.QC_1367_TyresProductPage;


import Common.SetUp;
import ATD.TyresProduct_page_Logic;
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

public class QC_1413_TestDeliveryMethodsBlockPresenceOnTyresProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Delivery Methods Block Presence On Tyres Product Page")
    public void testDeliveryMethodsBlockPresenceOnTyresProductPage(String route) {
        openPage(route);
        new TyresProduct_page_Logic().checkDeliveryMethodsBlockVisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
