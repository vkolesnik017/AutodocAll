package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

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

public class QC_2810 {
    private DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category15");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategories(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category8"));
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker7");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesMaker(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category_maker5"));
    }

    @DataProvider(name = "routesMakerSecond", parallel = true)
    Object[] dataProviderMakerSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker8");
    }

    @Test(dataProvider = "routesMakerSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesMakerSecond(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category_maker6"));
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model7");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCarList(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category_car_list_model6"));
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category16");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategory(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category9"));
    }

    @DataProvider(name = "routesCategorySecond", parallel = true)
    Object[] dataProviderCategorySecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category17");
    }

    @Test(dataProvider = "routesCategorySecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategorySecond(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category10"));
    }

    @DataProvider(name = "routesCategoryThird", parallel = true)
    Object[] dataProviderCategoryThird() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category18");
    }

    @Test(dataProvider = "routesCategoryThird")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategoryThird(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category11"));
    }

    @DataProvider(name = "routesCategoryFourth", parallel = true)
    Object[] dataProviderCategoryFourth() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category19");
    }

    @Test(dataProvider = "routesCategoryFourth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategoryFourth(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category12"));
    }

    @DataProvider(name = "routesCategoryFifth", parallel = true)
    Object[] dataProviderCategoryFifth() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category20");
    }

    @Test(dataProvider = "routesCategoryFifth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategoryFifth(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category13"));
    }

    @DataProvider(name = "categoryRoutes", parallel = true)
    Object[] dataProviderCategoryRoutes() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category21");
    }

    @Test(dataProvider = "categoryRoutes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for the absence of duplicate categories in the motorcycle catalog")
    public void testCheckingAbsenceOfDuplicateCategoriesCategoryRoutes(String route) throws SQLException {
        openPage(route);
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category14"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
