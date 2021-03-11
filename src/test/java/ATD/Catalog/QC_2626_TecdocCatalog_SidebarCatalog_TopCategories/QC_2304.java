package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.LKW_Categories_page_Logic;
import ATD.LKW_Error_page_Logic;
import ATD.LKW_FaqHash_page_Logic;
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
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2304 {
    private DataBase db = new DataBase("ATD");
    LKW_Categories_page_Logic lkwCategoriesPage = new LKW_Categories_page_Logic();
    LKW_maker_car_list_Logic lkwMakerCarListPage = new LKW_maker_car_list_Logic();
    LKW_Error_page_Logic lkwErrorPage = new LKW_Error_page_Logic();
    LKW_FaqHash_page_Logic lkwFaqHashPage = new LKW_FaqHash_page_Logic();

    List<String> childCategories = Arrays.asList("Frostschutz", "Getriebeöl", "Hydrauliköl", "Motoröl", "Schmierstoffe");

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
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategories(String route) throws SQLException {
        openPage(route);
        new LKW_Categories_page_Logic().presenceOfOilCategory()
                .clickOnOilParentCategory()
                .compareChildCategories(childCategories)
                .clickOnOilChildCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
    }

    @DataProvider(name = "routesMakerList", parallel = true)
    Object[] dataProviderMakerList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list10");
    }

    @Test(dataProvider = "routesMakerList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategoriesMakerList(String route) throws SQLException {
        openPage(route);
        lkwMakerCarListPage.presenceOfOilCategory()
                .clickOnOilParentCategory();
        lkwCategoriesPage.compareChildCategories(childCategories);
        lkwMakerCarListPage.clickOnOilChildCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category_car_list32"));
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list9");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategoriesCarList(String route) {
        openPage(route);
        lkwMakerCarListPage.presenceOfOilCategory()
                .clickOnOilParentCategory();
        lkwCategoriesPage.compareChildCategories(childCategories);
        lkwMakerCarListPage.clickOnOilChildCategoryWithOutMotor();
    }

    @DataProvider(name = "routesErrorPage", parallel = true)
    Object[] dataProviderErrorPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "404");
    }

    @Test(dataProvider = "routesErrorPage")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategoriesErrorPage(String route) throws SQLException {
        openPage(route);
        lkwErrorPage.presenceOfOilCategory()
                .clickOnOilParentCategory();
        lkwCategoriesPage.compareChildCategories(childCategories);
        lkwErrorPage.clickOnOilChildCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
    }

    @DataProvider(name = "routesFaq", parallel = true)
    Object[] dataProviderFaq() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_faqHash");
    }

    @Test(dataProvider = "routesFaq")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategoriesFaq(String route) throws SQLException {
        openPage(route);
        lkwFaqHashPage.presenceOfOilCategory()
                .clickOnOilParentCategory();
        lkwCategoriesPage.compareChildCategories(childCategories);
        lkwFaqHashPage.clickOnOilChildCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
