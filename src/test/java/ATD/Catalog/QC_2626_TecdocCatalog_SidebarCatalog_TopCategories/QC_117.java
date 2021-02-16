package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.LKW_Categories_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import ATD.LKW_Error_page_Logic;
import ATD.LKW_maker_car_list_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_117 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks of all parent and child categories block in TecDoc and model catalogs")
    public void testChecksParentAndChildCategoriesBlockInTecDocCatalog(String route) throws SQLException {
        openPage(route);
        new LKW_Categories_page_Logic()
                .presenceOfParentCategoriesBlock(30)
                .checkParentCategoriesOfTecDocCatalog()
                .transitionToChildCategory(1, 0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_category3"));
    }

    @DataProvider(name = "routesError", parallel = true)
    Object[] dataProviderError() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "404");
    }

    @Test(dataProvider = "routesError")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks of all parent and child categories block in TecDoc and model catalogs")
    public void testChecksParentAndChildCategoriesBlockInTecDocCatalogError(String route) throws SQLException {
        openPage(route);
        new LKW_Error_page_Logic()
                .presenceOfParentCategoriesBlock(30)
                .checkParentCategoriesOfTecDocCatalog()
                .transitionToChildCategory(1, 0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_category3"));
    }


    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderMakerCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks of all parent and child categories block in TecDoc and model catalogs")
    public void testChecksParentAndChildCategoriesBlockInTecDocCatalogMakerCarList(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic()
                .presenceOfParentCategoriesBlock(0)
                .checkParentCategoriesOfTecDocCatalog()
                .selectChildCategoryWithOutAllValuesInSelector(1, 1)
                .appearanceOfSelector();
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks of all parent and child categories block in TecDoc and model catalogs")
    public void testChecksParentAndChildCategoriesBlockInTecDocCatalogCarList(String route) throws SQLException {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .presenceOfParentCategoriesBlock(0)
                .checkParentCategoriesOfTecDocCatalog()
                .transitionToChildCategory(1, 0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_category_car_list27"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
