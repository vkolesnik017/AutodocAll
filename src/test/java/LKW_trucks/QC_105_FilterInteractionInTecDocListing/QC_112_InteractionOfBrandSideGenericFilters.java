package LKW_trucks.QC_105_FilterInteractionInTecDocListing;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.SetUp;
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

public class QC_112_InteractionOfBrandSideGenericFilters {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list12");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filter interaction by brand by side by generic")
    public void testChecksFilterInteractionByBrandSideGeneric(String route) throws SQLException {
        openPage(route);

        new LKW_Category_car_list_page_Logic()
                .selectBrandFromFilterOfBrands("lkw_category_car_list21", "39")
                .selectGenericFilter("lkw_category_car_list24", "407")
                .checkOfPresenceInstallationSide()
                .checkOfPresenceSelectedBrand("TEXTAR")
                .checkOfPresenceSelectingGeneric("Warnkontakt, Bremsbelagverschleiß");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
