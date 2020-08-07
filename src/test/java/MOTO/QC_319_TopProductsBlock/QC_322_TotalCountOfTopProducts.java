package MOTO.QC_319_TopProductsBlock;

import ATD.*;
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

public class QC_322_TotalCountOfTopProducts {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main,moto_categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProducts(String route)  {
        openPage(route);

        new Moto_main_page_Logic()
                .checkCountOfTopProducts();
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2,moto_catalog2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsCatalog(String route)  {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .checkCountOfTopProducts();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsCategoriesMaker(String route)  {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .checkCountOfTopProducts();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsCategory(String route)  {
        openPage(route);

        new Moto_Category_page_Logic()
                .checkCountOfTopProducts();
    }
    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsParentCategory(String route)  {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .checkCountOfTopProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
