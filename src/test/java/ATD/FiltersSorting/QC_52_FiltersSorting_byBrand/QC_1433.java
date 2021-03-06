package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.TyresListing_page_Logic;
import Common.DataBase;
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

public class QC_1433 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension2 ,tyres_dimension3,tyres_dimension5,tyres_dimension4," +
                "tyres_size6,tyres_size7,tyres_size4,tyres_size5," +
                "tyre_form,tyre_form2,tyre_form3");

    }

    //TODO отключен из за дефекта SEO-2379

    @Test(dataProvider = "routes", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Brand Filter Applying On Tyres Listing")
    public void testBrandFilterApplyingOnTyresListing(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkBrandFilterApplying();
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Brand Filter Applying On Tyres Listing Moto Route")
    public void testBrandFilterApplyingOnTyresListingMotoRoute() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyre_form4"));
        new TyresListing_page_Logic().checkBrandFilterApplyingMotoRoute();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
