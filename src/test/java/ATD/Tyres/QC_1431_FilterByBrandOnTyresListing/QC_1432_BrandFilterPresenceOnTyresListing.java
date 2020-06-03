package ATD.Tyres.QC_1431_FilterByBrandOnTyresListing;


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
import static com.codeborne.selenide.Selenide.close;

public class QC_1432_BrandFilterPresenceOnTyresListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension2,tyres_dimension3,tyres_dimension5,tyres_dimension4," +
                "tyres_size6,tyres_size7,tyres_size4,tyres_size5," +
                "tyre_form,tyre_form2,tyre_form3,tyre_form4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Brand Filter Presence On Tyres Listing")
    public void testBrandFilterPresenceOnTyresListing(String route) throws SQLException {
        openPage(route);
        new TyresListing_page_Logic().checkBrandFilterVisibility();

    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
