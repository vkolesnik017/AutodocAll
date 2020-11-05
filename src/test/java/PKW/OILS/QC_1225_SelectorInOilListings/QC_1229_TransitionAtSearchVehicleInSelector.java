package PKW.OILS.QC_1225_SelectorInOilListings;

import Common.DataBase;
import PKW.Motoroil_Release_page_Logic;
import PKW.Motoroil_specification_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.checkingContainsUrl;

public class QC_1229_TransitionAtSearchVehicleInSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_search,motoroil_release,motoroil_viscosity");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelector(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .selectVehicleInSelectorAfterDefaultValues("121", "7873", "33030");
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "car_parts_motoroil5"));
    }

    @DataProvider(name = "routesBrand", parallel = true)
    Object[] dataProviderBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand,motoroil_chemical_type,car_parts_motoroil");
    }

    @Test(dataProvider = "routesBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelectorBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .selectVehicleInSelectorAfterDefaultValues("121", "7873", "33030");
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "car_parts_motoroil5"));
    }

    @DataProvider(name = "routesSpecification", parallel = true)
    Object[] dataProviderSpecification() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil,motoroil_specification,motoroil_brand");
    }

    @Test(dataProvider = "routesSpecification")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelectorSpecification(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .selectVehicleInSelectorAfterDefaultValues("121", "7873", "33030");
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "car_parts_motoroil5"));
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker,motoroil_maker_group");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelectorMaker(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .selectVehicleInSelectorAfterDefaultValues("121", "7873", "33030");
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "car_parts_motoroil5"));
    }
}
