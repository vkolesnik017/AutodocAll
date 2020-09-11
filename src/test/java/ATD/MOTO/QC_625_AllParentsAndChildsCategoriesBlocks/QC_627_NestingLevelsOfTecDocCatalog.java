package ATD.MOTO.QC_625_AllParentsAndChildsCategoriesBlocks;

import ATD.Moto_Catalog_model_page_Logic;
import ATD.Moto_Catalog_page_Logic;
import ATD.Moto_Categories_page_Logic;
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

public class QC_627_NestingLevelsOfTecDocCatalog {

  @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }
    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TecDoc catalog and his structure")
    public void testChecksPresenceOfTecDocCatalog(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .checkParentCategoriesOfTecDocCatalog();
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");

    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TecDoc catalog and his structure")
    public void testChecksPresenceOfTecDocCatalogModel(String route) {
        openPage(route);

        new Moto_Catalog_model_page_Logic()
                .checkParentCategoriesOfTecDocCatalog();
    }

    @DataProvider(name = "routesCatalogMoto", parallel = true)
    Object[] dataProviderCatalogMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routesCatalogMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TecDoc catalog and his structure")
    public void testChecksPresenceOfTecDocCatalogModMoto(String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .checkParentCategoriesOfTecDocCatalog();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
