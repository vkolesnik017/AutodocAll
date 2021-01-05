package ATD.LKW_trucks.QC_18_SideBarBlocksOfParentCategoriesAndLinkingChildCategory;

import ATD.LKW_Category_page_Logic;
import ATD.LKW_Parent_Category_page_Logic;
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

public class QC_25 {
    private DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBar(String route) throws SQLException {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfCatalogBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_parent_category5"));
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBarCategory(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().presenceChildCategoriesBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category5"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBarCategoryMaker(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().presenceChildCategoriesBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_maker4"));
    }

    @DataProvider(name = "routesCategoryBrand", parallel = true)
    Object[] dataProviderCategoryBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand");
    }

    @Test(dataProvider = "routesCategoryBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBarCategoryBrand(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().presenceChildCategoriesBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_brand4"));
    }

    @DataProvider(name = "routesCategoryMakerBrand", parallel = true)
    Object[] dataProviderCategoryMakerBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMakerBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBarCategoryMakerBrand(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().presenceChildCategoriesBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_maker_brand5"));
    }

    @DataProvider(name = "routesCategoryList", parallel = true)
    Object[] dataProviderCategoryList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list38");
    }

    @Test(dataProvider = "routesCategoryList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on TOP subcategory element in sidebar")
    public void testChecksTransitionByClickOnTopSubCategoryElementInSideBarList(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().presenceChildCategoriesBlockInSidebar().clickOnChildCategory(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_car_list39"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
