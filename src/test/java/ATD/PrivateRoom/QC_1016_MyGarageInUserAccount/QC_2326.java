package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

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

public class QC_2326 {
    String email = "QC_2326TransitionToMyGarage@mailinator.com";
    DataBase db = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesCheck", parallel = true)
    Object[] dataProviderCheck() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyres_size11,index_instruments,category_maker_brand4");
    }

    @Test(dataProvider = "routesCheck")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleCheck(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "index_accessories,listing_accessories5,tyres");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicle(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesInstruments", parallel = true)
    Object[] dataProviderInstruments() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "listing_instruments4,staticAboutUs,staticImpressum");
    }

    @Test(dataProvider = "routesInstruments")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleInstruments(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesPlus", parallel = true)
    Object[] dataProviderPlus() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_maker5,category_maker_body2");
    }

    @Test(dataProvider = "routesPlus")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehiclePlus(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesProfilePlus", parallel = true)
    Object[] dataProviderProfilePlus() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "profile_plus");
    }

    @Test(dataProvider = "routesProfilePlus")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleProfilePlus(String route) throws SQLException {
        openPage(route);

        new Profile_plus_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesName", parallel = true)
    Object[] dataProviderName() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_name_brand6");
    }

    @Test(dataProvider = "routesName")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleName(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list42"));
    }


    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_maker6,category_maker_body3,category_maker_drive2");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleMaker(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesGroup", parallel = true)
    Object[] dataProviderGroup() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_group2,category_group_body2,category_group_drive2,category_group_fuel2");
    }

    @Test(dataProvider = "routesGroup")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleGroup(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesGroupYear", parallel = true)
    Object[] dataProviderGroupYear() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_group_year2,category_group_brand4,category_model2");
    }

    @Test(dataProvider = "routesGroupYear")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleGroupYear(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleLKW(String route) throws SQLException {
        openPage(route);

        new LKW_main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");

    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleMoto(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "maker_car_list14"));
    }

    @DataProvider(name = "routesSearch", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "search19");
    }

    @Test(dataProvider = "routesSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleSearch(String route) throws SQLException {
        openPage(route);

        new Search_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "search19"));
    }

    @DataProvider(name = "routesCategoryName", parallel = true)
    Object[] dataProviderCategoryName() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_name7");
    }

    @Test(dataProvider = "routesCategoryName")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleCategoryName(String route) throws SQLException {
        openPage(route);

        new Category_name_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list37"));
    }

    @DataProvider(name = "routesCategoryNameBrand", parallel = true)
    Object[] dataProviderCategoryNameBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_name_brand5");
    }

    @Test(dataProvider = "routesCategoryNameBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleCategoryNameBrand(String route) throws SQLException {
        openPage(route);

        new Category_name_brand_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list38"));
    }

    @DataProvider(name = "routesOen", parallel = true)
    Object[] dataProviderOen() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_oen15");
    }

    @Test(dataProvider = "routesOen")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleOen(String route) throws SQLException {
        openPage(route);

        new Category_oen_Page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_oen15"));
    }

    @DataProvider(name = "routesList", parallel = true)
    Object[] dataProviderList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "category_car_list36");
    }

    @Test(dataProvider = "routesList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleList(String route) throws SQLException {
        openPage(route);

        new Category_car_list_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list39"));
    }

    @DataProvider(name = "routesBrake", parallel = true)
    Object[] dataProviderBrake() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "brake_system");
    }

    @Test(dataProvider = "routesBrake")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected vehicle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedVehicleBrake(String route) throws SQLException {
        openPage(route);

        new Category_name_parent_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectVehicleInGaragePopUp("6926");
        checkingContainsUrl(db.getRouteByRouteName("DE", "category_car_list41"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
