package ATD.Tyres.QC_1253_PaymentsAndDeliveryBlockOnTyresListing;


import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1271_TestPresencePaymentsTypeBlockOnTyresListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_season6,tyres_season7,tyres_season_size2," +
                "tyres_dimension2,tyres_size6,tyres_brand5,tyre_form," +
                "tyres_season,tyres_season8,tyres_season9,tyres_season_size3,tyres_dimension3,tyres_brand2," +
                "tyres_season5,tyres_season10,tyres_season11,tyres_season_size4,tyres_dimension5,tyres_brand3," +
                "tyres_season12,tyres_season4,tyres_season_size5,tyres_dimension4,tyres_brand4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Presence Payments Type Block On Tyres Listing")
    public void testPresencePaymentsTypeBlockOnTyresListing(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkPaymentsBlockVisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
