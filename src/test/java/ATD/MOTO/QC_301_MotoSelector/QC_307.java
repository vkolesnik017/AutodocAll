package ATD.MOTO.QC_301_MotoSelector;

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
import static com.codeborne.selenide.Selenide.*;

public class QC_307 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_category_car_list2,moto_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelector (String route) throws SQLException {
        openPage(route);

        new Moto_Category_car_list_page_Logic()
                .resetOfMotoSelector()
                .checkCurrentUrl("moto_category");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCategory(String route) throws SQLException {
        openPage(route);

        new Moto_Category_page_Logic()
                .selectBrandOfMoto("4081").resetOfMotoSelector()
                .checkCurrentUrl("moto_category")
                .presenceOfEmptyValuesInSelector();

    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderCategoryMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorMain(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .selectBrandOfMoto("4081").resetOfMotoSelector()
                .checkCurrentUrl("moto_main")
                .presenceOfEmptyValuesInSelector();
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorMakers(String route) throws SQLException {
        openPage(route);

        new Moto_makers_page_Logic()
                .selectBrandOfMoto("4081").resetOfMotoSelector()
                .checkCurrentUrl("moto_makers")
                .presenceOfEmptyValuesInSelector();

    }

     @DataProvider(name = "routesParentCategory", parallel = true)
     Object[] dataProviderParentCategory() throws SQLException {
         return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
     }

     @Test(dataProvider = "routesParentCategory")
     @Flaky
     @Owner(value = "Kolesnik")
     @Description(value = "Test checks reset values in motorcycle selector ")
     public void testChecksResetValuesInSelectorParentCategory(String route) throws SQLException {
         openPage(route);

         new Moto_Parent_Category_page_Logic()
                 .selectBrandOfMoto("4081")
                 .resetOfMotoSelector()
                 .presenceOfEmptyValuesInSelector()
                 .checkCurrentUrl("moto_parent_category");

     }
    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderParentCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_page_Logic()
                .selectBrandOfMoto("4081")
                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_categories");
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_Catalog_page_Logic()

                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_categories");
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCatalogModel(String route) throws SQLException {
        open(route);

        new Moto_Catalog_model_page_Logic()

                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_categories");
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCategoriesMaker(String route) throws SQLException {
        open(route);

        new Moto_Categories_maker_page_Logic()

                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_categories");
    }

    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorProduct(String route) throws SQLException {
        openPage(route);

        new Moto_Product_page_Logic()
                .selectBrandOfMoto("4081")
                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_product");
    }

    @DataProvider(name = "routesParentCategoryMaker", parallel = true)
    Object[] dataProviderParentCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesParentCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorParentCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .resetOfMotoSelector()
                .presenceOfEmptyValuesInSelector()
                .checkCurrentUrl("moto_parent_category");
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
