package PKW.OILS.QC_1225_SelectorInOilListings;

import PKW.DataBase;
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
import static ATD.SetUp.setUpBrowser;
import static PKW.CommonMethods.checkingContainsUrl;

public class QC_1229_TransitionAtSearchVehicleInSelector {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_search,motoroil_release,motoroil_viscosity,motoroil_viscosity_brand,motoroil_chemical_type,car_parts_motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelector(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .selectVehicleInSelector("5", "36928", "121494");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "car_parts_motoroil2"));
    }

    @DataProvider(name = "routesSpecification", parallel = true)
    Object[] dataProviderSpecification() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil,motoroil_specification,motoroil_brand,motoroil_maker,motoroil_maker_group");
    }

    @Test(dataProvider = "routesSpecification")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition at search vehicle in selector")
    public void testChecksResetOfValuesInSelectorSpecification(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .selectVehicleInSelector("5", "36928", "121494");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "car_parts_motoroil2"));
    }
}
