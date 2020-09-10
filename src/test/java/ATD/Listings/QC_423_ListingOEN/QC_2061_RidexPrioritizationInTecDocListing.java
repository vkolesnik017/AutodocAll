package ATD.Listings.QC_423_ListingOEN;

import ATD.Category_car_list_page_Logic;
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

public class QC_2061_RidexPrioritizationInTecDocListing {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list31");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "kolesnik")
    @Description(value = "Checks Ridex prioritization  and another products in TecDoc listing")
    public void testCheckRidexPrioritizationInTecDocListing(String route) {
        openPage(route);
                new Category_car_list_page_Logic()
                .checkTecDocListing();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
