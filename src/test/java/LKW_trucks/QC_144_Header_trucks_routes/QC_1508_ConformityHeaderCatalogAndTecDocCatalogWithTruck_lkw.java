package LKW_trucks.QC_144_Header_trucks_routes;

import ATD.LKW_Catalog_page_Logic;
import ATD.LKW_main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1508_ConformityHeaderCatalogAndTecDocCatalogWithTruck_lkw {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check compliance Of Header_Catalog and TecDoc catalog with selecting truck")
    public void testCheckComplianceOfHeaderCatalogAndTecDocCatalog(String route) {
        openPage(route);
        new LKW_Catalog_page_Logic().comparisonTecDocAndInHeaderCatalogs();

    }
}
