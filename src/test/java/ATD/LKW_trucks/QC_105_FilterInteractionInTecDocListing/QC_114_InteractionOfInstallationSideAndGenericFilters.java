package ATD.LKW_trucks.QC_105_FilterInteractionInTecDocListing;

import ATD.LKW_Category_car_list_page_Logic;
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

public class QC_114_InteractionOfInstallationSideAndGenericFilters {

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
    @Description(value = "Test checks interaction of installation side and generic filters")
    public void testChecksInteractionInstallationSideAndGenericFilters(String route) throws SQLException {
        openPage(route);

        new LKW_Category_car_list_page_Logic()
                .selectGenericFilterInSideBar("lkw_category_car_list22", "402")
                .selectInstallationSide("lkw_category_car_list30")
                .checkOfPresenceSelectingGeneric("Bremsbelagsatz, Scheibenbremse")
                .checkOfPresenceInstallationSide();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
