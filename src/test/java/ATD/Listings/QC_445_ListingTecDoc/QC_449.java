package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Listing_page_Logic;
import Common.SetUp;
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

public class QC_449 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list10");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Checks additional listing on TecDoc listing")
    public void testPresenceAdditionalListingOnTecDoc(String route) {
        openPage(route);
        new Listing_page_Logic().checksThatProductsAtListingAreFitsForChosenCar("FORD C-Max II (DXA/CB7, DXA/CEU) 1.0 EcoBoost");
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
