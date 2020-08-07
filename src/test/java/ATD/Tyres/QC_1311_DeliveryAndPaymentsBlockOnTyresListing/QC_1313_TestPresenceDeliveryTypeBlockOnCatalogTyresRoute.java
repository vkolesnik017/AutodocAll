package ATD.Tyres.QC_1311_DeliveryAndPaymentsBlockOnTyresListing;


import ATD.SetUp;
import ATD.TyresListing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1313_TestPresenceDeliveryTypeBlockOnCatalogTyresRoute {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list,tyres_type_list2,tyres_type_list3,tyres_type_list4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Presence Delivery Type Block On Catalog Tyres Route")
    public void testPresenceDeliveryTypeBlockOnCatalogTyresRoute(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkDeliveryBlockVisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
