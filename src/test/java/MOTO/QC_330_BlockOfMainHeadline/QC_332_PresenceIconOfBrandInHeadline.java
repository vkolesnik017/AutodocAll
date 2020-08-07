package MOTO.QC_330_BlockOfMainHeadline;

import ATD.Moto_Catalog_page_Logic;
import ATD.Moto_Categories_maker_page_Logic;
import ATD.Moto_Category_maker_page_Logic;
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

public class QC_332_PresenceIconOfBrandInHeadline {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of brand icon in headline")
    public void testChecksPresenceIconOfBrandInHeadline(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .presenceOfBrandIconInHeadline();
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of brand icon in headline")
    public void testChecksPresenceIconOfBrandInHeadlineCategoryMaker(String route) {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .presenceOfBrandIconInHeadline();
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_catalog_model2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of brand icon in headline")
    public void testChecksPresenceIconOfBrandInHeadlineCatalog(String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .presenceOfBrandIconInHeadline();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
