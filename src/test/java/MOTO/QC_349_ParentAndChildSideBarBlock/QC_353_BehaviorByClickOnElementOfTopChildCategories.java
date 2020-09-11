package MOTO.QC_349_ParentAndChildSideBarBlock;

import ATD.*;
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

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_353_BehaviorByClickOnElementOfTopChildCategories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategories(String route) throws SQLException {
        openPage(route);

        new Moto_Category_car_list_model_page_Logic()
                .clickOnChildCategoryInSidebar(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list_model4"));
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2");

    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategoriesCarList(String route) throws SQLException {
        openPage(route);

        new Moto_Category_car_list_page_Logic()
                .clickOnChildCategoryInSidebar(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list12"));
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category");

    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategoriesCategory(String route) throws SQLException {
        openPage(route);

        new Moto_Category_page_Logic()
                .clickOnChildCategoryInSidebar(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category3"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker");

    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategoriesCategoryMaker(String route) throws SQLException {
        openPage(route);
           String mainHeadline =   new Moto_Category_maker_page_Logic().getTitleFromMainHeadline();
        new Moto_Category_maker_page_Logic()
                .clickOnChildCategoryInSidebar(0).checkOfChangeAtMainHeadline(mainHeadline);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_maker3"));
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");

    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategoriesParentCategory(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .clickOnChildCategoryInSidebar(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category6"));
    }

    @DataProvider(name = "routesParentCategoryMaker", parallel = true)
    Object[] dataProviderParentCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");

    }

    @Test(dataProvider = "routesParentCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior when clicking on the element of the list of TOP child categories in the sidebar")
    public void testChecksBehaviorByClickOnElementOfTopChildCategoriesParentCategoryMaker(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .clickOnChildCategoryInSidebar(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_maker4"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
