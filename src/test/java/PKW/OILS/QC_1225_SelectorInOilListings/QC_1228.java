package PKW.OILS.QC_1225_SelectorInOilListings;

import Common.DataBase;
import PKW.Car_parts_motoroil_page_Logic;
import PKW.Motoroil_Release_page_Logic;
import PKW.Motoroil_Search_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1228 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release,motoroil,motoroil_viscosity,motoroil_viscosity_brand,motoroil_specification,motoroil_brand,motoroil_maker,motoroil_maker_group,motoroil_chemical_type");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in selector")
    public void testChecksResetOfValuesInSelector(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .presenceOfSelector()
                .selectMarkeInSelector("121")
                .visibilityOfResetButtonOfSelector()
                .resetOfSelector();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil"));
    }


    @DataProvider(name = "routesSearch", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_search");
    }

    @Test(dataProvider = "routesSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in selector")
    public void testChecksResetOfValuesInSelectorSerch(String route) throws SQLException {
        openPage(route);
        new Motoroil_Search_page_Logic()
                .presenceOfSelector()
                .selectMarkeInSelector("121")
                .visibilityOfResetButtonOfSelector()
                .resetOfSelector();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil_search"));
    }

    @DataProvider(name = "routesCarParts", parallel = true)
    Object[] dataProviderCarParts() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts_motoroil");
    }

    @Test(dataProvider = "routesCarParts")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of values in selector")
    public void testChecksResetOfValuesInSelectorCarParts(String route) throws SQLException {
        openPage(route);
        new Car_parts_motoroil_page_Logic()
                .presenceOfSelector()
                .selectMarkeInSelector("121")
                .visibilityOfResetButtonOfSelector()
                .resetOfSelector();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "makers"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
