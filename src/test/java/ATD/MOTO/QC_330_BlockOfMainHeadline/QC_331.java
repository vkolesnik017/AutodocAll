package ATD.MOTO.QC_330_BlockOfMainHeadline;

import ATD.*;
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

public class QC_331 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers,moto_parent_category,moto_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of main headline block")
    public void testChecksPresenceOfMainHeadlineBlock(String route) {
        openPage(route);

        new Moto_makers_page_Logic()
                .presenceOfMainHeadlineBlock();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of main headline block")
    public void testChecksPresenceOfMainHeadlineBlockCategoriesMaker(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .presenceOfMainHeadlineBlock();
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_category_car_list2,moto_category_maker");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of main headline block")
    public void testChecksPresenceOfMainHeadlineBlockCategoryMaker(String route) {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .presenceOfMainHeadlineBlock();
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of main headline block")
    public void testChecksPresenceOfMainHeadlineBlockCatalog(String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .presenceOfMainHeadlineBlock();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of main headline block")
    public void testChecksPresenceOfMainHeadlineBlockCategories(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .presenceOfMainHeadlineBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
