package ATD.LKW_trucks.QC_2306_OilsAndLiquidsCategories;

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

public class QC_2304_PresenceOfOilAndLiquidCategories {
    private DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
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
        new LKW_maker_car_list_Logic().presenceOfOilCategory()
                .clickOnOilParentCategory()
                .clickOnOilChildCategory();
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
    public void testChecksPresenceOgOilAndLiquidCategoriesCarList(String route){
        openPage(route);
        new LKW_maker_car_list_Logic().presenceOfOilCategory()
                .clickOnOilParentCategory()
                .clickOnOilChildCategoryWithOutMotor();
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
        new LKW_Error_page_Logic().presenceOfOilCategory()
                .clickOnOilParentCategory()
                .clickOnOilChildCategory();
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
        new LKW_FaqHash_page_Logic().presenceOfOilCategory()
                .clickOnOilParentCategory()
                .clickOnOilChildCategory();
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_category4"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
