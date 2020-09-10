package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import Common.DataBase;
import ATD.LKW_Categories_maker_page_Logic;
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

public class QC_2344_UpdatingOfPageWithSelectedTruckInGaragePopUp {

    private String email = "QC_2344_TransitionToMyGarage@mailinator.com";
    private DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker,lkw_maker_car_list11,lkw_maker_car_list12");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUp(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_maker_car_list13"));
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpCategory(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_category_car_list35"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand2,lkw_category_model_brand,lkw_category_maker");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpCategoryMaker(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE", "lkw_maker_car_list13"));
    }
    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list33,lkw_category_car_list34,404");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpCarList(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_maker_car_list13"));
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpParentCategory(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE","lkw_parent_category"));
    }

    @DataProvider(name = "routesSearch", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search11");
    }

    @Test(dataProvider = "routesSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpSearch(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_search11"));
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpMain(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_maker_car_list13"));
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected truck in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedTruckInGaragePopUpMoto(String route) throws SQLException {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("1009583");
        checkingContainsUrl(db.getRouteByRouteName("DE","lkw_maker_car_list13"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
