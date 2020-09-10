package PKW.OILS.QC_1225_SelectorInOilListings;

import PKW.Motoroil_Maker_page_Logic;
import PKW.Motoroil_Release_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1227_MandatorySelectionOfAllSelectorFields {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release,motoroil_viscosity,motoroil_viscosity_brand,motoroil_specification,motoroil_chemical_type,motoroel-search,motoroil,motoroil_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks mandatory selection of all selector fields")
    public void testChecksMandatorySelectionOfAllSelectorFields(String route) {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .presenceOfSelector()
                .visibilityErrorToolTipForMarkeFieldOfSelector()
                .visibilityErrorToolTipForModelFieldOfSelector("121")
                .visibilityErrorToolTipForMotorFieldOfSelector("4644");
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker,motoroil_maker_group,car_parts_motoroil");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks mandatory selection of all selector fields")
    public void testChecksMandatorySelectionOfAllSelectorFieldsMaker(String route) {
        openPage(route);

        new Motoroil_Maker_page_Logic()
                .presenceOfSelector()
                .visibilityErrorToolTipForMarkeFieldOfSelector()
                .visibilityErrorToolTipForModelFieldOfSelector("121")
                .visibilityErrorToolTipForMotorFieldOfSelector("4644");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
