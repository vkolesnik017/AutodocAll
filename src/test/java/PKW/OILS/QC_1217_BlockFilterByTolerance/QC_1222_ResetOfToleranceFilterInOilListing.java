package PKW.OILS.QC_1217_BlockFilterByTolerance;

import PKW.Motoroil_viscosity_brand_page_Logic;
import PKW.Motoroil_viscosity_page_Logic;
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
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1222_ResetOfToleranceFilterInOilListing {

    private Motoroil_viscosity_page_Logic viscosityPage = new Motoroil_viscosity_page_Logic();
    private Motoroil_viscosity_brand_page_Logic viscosityBrandPage = new Motoroil_viscosity_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker,motoroil_maker_group,motoroil_viscosity,motoroil_brand2,motoroil_maker,motoroil_maker_group,motoroil_chemical_type,motoroel-search,car_parts_motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of Tolerance filter in Oil listing")
    public void testChecksResetOfToleranceFilterInOilListing(String route) {
        openPage(route);
        String currentUrl = viscosityPage.getCurrentUrl();
        viscosityPage.selectFilterByTolerance("BMW Longlife-01", "?release%5B%5D=bmw-longlife-01")
                .clickOnToleranceFilter("BMW Longlife-01");
        checkingContainsUrl(currentUrl);
        viscosityPage.selectFilterByTolerance("BMW Longlife-01", "?release%5B%5D=bmw-longlife-01")
                .clickOnToleranceFilter("Alle");
        checkingContainsUrl(currentUrl);
    }

    @DataProvider(name = "routesBrand", parallel = true)
    Object[] dataProviderBrand() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand,motoroil_specification");
    }

    @Test(dataProvider = "routesBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of Tolerance filter in Oil listing")
    public void testChecksResetOfToleranceFilterInOilListingBrand(String route) {
        openPage(route);
        String currentUrl = viscosityBrandPage.getCurrentUrl();
        viscosityBrandPage.selectFilterByTolerance("Ford WSS-M2C913-A", "?release%5B%5D=ford-wss-m2c913-a")
                .clickOnToleranceFilter("Ford WSS-M2C913-A");
        checkingContainsUrl(currentUrl);
        viscosityBrandPage.selectFilterByTolerance("Ford WSS-M2C913-A", "?release%5B%5D=ford-wss-m2c913-a")
                .clickOnToleranceFilter("Alle");
        checkingContainsUrl(currentUrl);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}