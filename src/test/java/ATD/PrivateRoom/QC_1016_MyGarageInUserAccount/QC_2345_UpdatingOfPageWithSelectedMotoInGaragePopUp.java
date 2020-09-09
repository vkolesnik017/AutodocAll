package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import Common.DataBase;
import ATD.Moto_Categories_maker_page_Logic;
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

public class QC_2345_UpdatingOfPageWithSelectedMotoInGaragePopUp {

    String email = "QC_2345TransitionToMyGarage@mailinator.com";
    DataBase db = new DataBase();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker,moto_catalog");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected motorcycle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedMotoInGaragePopUp(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("101396");
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_catalog8"));
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model6,moto_category_car_list_model2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected motorcycle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedMotoInGaragePopUpCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("101396");
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_catalog8"));
    }

    @DataProvider(name = "routesListOfModel", parallel = true)
    Object[] dataProviderListOfModel() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesListOfModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected motorcycle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedMotoInGaragePopUpListOfModel(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("101396");
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_catalog8"));
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2,moto_category");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks updating of page with selected motorcycle in Garage pop-up")
    public void testChecksUpdatingOfPageWithSelectedMotoInGaragePopUpCarList(String route) throws SQLException {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .loginToProfilePlusPageAndBack(email).updateOfPage().checkCountOfVehicleInIconOfGarage("3")
                .clickOnGarageIconInHeader()
                .selectMotoInGaragePopUp("101396");
        checkingContainsUrl(db.getRouteByRouteName("DE", "moto_category_car_list13"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
