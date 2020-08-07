package LKW_trucks.QC_82_TruckSelectorBlock;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.LKW_maker_car_list_Logic;
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

public class QC_85_TitleOfTruckSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderMakerCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list4");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of truck selector at maker_carl_list route")
    public void testChecksHeadlineOfTruckSelector(String routeMakerCarList) {
        openPage(routeMakerCarList);
        new LKW_maker_car_list_Logic()
                .goToMainPage()
                .checkSuccessfullyLKWPageLoading()
                .clickOnImageOfParentCategory()
                .checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/filter")
                .visibilityOfHeadLineSelectorAndIconOfTruck("DAF 45 FA 45.120-035 (85 kW / 116 PS)");
    }


    @DataProvider(name = "routesCategoryCarList", parallel = true)
    Object[] dataProviderCategoryCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "routesCategoryCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of truck selector at Category_car_list route")
    public void testChecksHeadlineOfTruckSelectorCategoryCarList(String routeCategoryCarList) {
        openPage(routeCategoryCarList);
        new LKW_Category_car_list_page_Logic()
                .visibilityOfHeadLineSelectorAndIconOfTruck("MERCEDES-BENZ ACTROS 1831 AK (230 kW / 313 PS)");
    }


    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of truck selector at maker_car_list")
    public void testChecksHeadlineOfTruckSelectorMakerCarList(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic()
                .visibilityOfSelectorInOpenConditionСatalogByModel()
                .visibilityOfHeadLineSelectorAndIconOfTruck("MERCEDES-BENZ ACTROS 1831 AK (230 kW / 313 PS)");
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list5");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of truck selector at maker_car_list route")
    public void testChecksHeadlineOfTruckSelectorCarList(String routeCarList) {
        openPage(routeCarList);
        new LKW_maker_car_list_Logic()
                .goToMainPage()
                .checkSuccessfullyLKWPageLoading()
                .goToAllTruckBrandsPage()
                .visibilityOfHeadLineSelectorAndIconOfTruck("Elite (154 kW / 210 PS)");
    }

    @DataProvider(name = "routesMakerCarListAndMainPage", parallel = true)
    Object[] dataProviderMakerCarListAndMainPage() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list3");
    }

    @Test(dataProvider = "routesMakerCarListAndMainPage")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of truck selector at maker_carl_list route")
    public void testChecksHeadlineOfTruckSelectorMakerCarListAndMainPage(String routeMakerCarList) {
        openPage(routeMakerCarList);
        new LKW_maker_car_list_Logic()
                .goToMainPage()
                .visibilityOfHeadLineSelectorAndIconOfTruck("ASKAM (FARGO/DESOTO) AS 950 Super TL (176 kW / 240 PS)");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
