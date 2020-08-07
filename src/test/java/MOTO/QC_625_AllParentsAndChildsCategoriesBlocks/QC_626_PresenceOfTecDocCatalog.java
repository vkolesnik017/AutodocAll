package MOTO.QC_625_AllParentsAndChildsCategoriesBlocks;

import ATD.Moto_Catalog_page_Logic;
import ATD.Moto_Categories_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_626_PresenceOfTecDocCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TecDoc catalog and his structure")
    public void testChecksPresenceOfTecDocCatalog(String route)  {
        openPage(route);

        new Moto_Categories_page_Logic()
        .presenceOfTecDocCatalog();
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_catalog_model2");

    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TecDoc catalog and his structure")
    public void testChecksPresenceOfTecDocCatalogRoute(String route)  {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .presenceOfTecDocCatalog();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
