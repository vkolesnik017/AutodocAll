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

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1508_ConformityHeaderCatalogAndTecDocCatalogWithTruck_lkw {
    private LKW_main_page_Logic mainPage = new LKW_main_page_Logic();

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
        open(route);
        LKW_Catalog_page_Logic catalogPage = mainPage.selectTruckInSelector();
        catalogPage.getCategoryInTecDocCatalog();
        catalogPage.getCategoryInHeaderCatalog();
        catalogPage.comparisonTecDocAndInHeaderCatalogs();
    }
}
