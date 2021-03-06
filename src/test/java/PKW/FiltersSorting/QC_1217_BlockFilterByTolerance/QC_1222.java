package PKW.FiltersSorting.QC_1217_BlockFilterByTolerance;

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
import static Common.SetUp.setUpBrowser;
import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1222 {

    private Motoroil_viscosity_page_Logic viscosityPage = new Motoroil_viscosity_page_Logic();
    private Motoroil_viscosity_brand_page_Logic viscosityBrandPage = new Motoroil_viscosity_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker_group,motoroil_maker,motoroil_viscosity,motoroil_brand2,motoroil_chemical_type,motoroel-search,car_parts_motoroil");
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
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand");
    }

    @Test(dataProvider = "routesBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of Tolerance filter in Oil listing")
    public void testChecksResetOfToleranceFilterInOilListingBrand(String route) {
        openPage(route);
        String currentUrl = viscosityBrandPage.getCurrentUrl();
        viscosityBrandPage.selectFilterByTolerance("BMW Longlife-12 FE", "?release%5B%5D=bmw-longlife-12-fe")
                .clickOnToleranceFilter("BMW Longlife-12 FE");
        checkingContainsUrl(currentUrl);
        viscosityBrandPage.selectFilterByTolerance("BMW Longlife-12 FE", "?release%5B%5D=bmw-longlife-12-fe")
                .clickOnToleranceFilter("Alle");
        checkingContainsUrl(currentUrl);
    }

    @DataProvider(name = "routesBrandSpecific", parallel = true)
    Object[] dataProviderBrandSpecific() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_specification");
    }

    @Test(dataProvider = "routesBrandSpecific")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset of Tolerance filter in Oil listing")
    public void testChecksResetOfToleranceFilterInOilListingBrandSpecific(String route) {
        openPage(route);
        String currentUrl = viscosityBrandPage.getCurrentUrl();
        viscosityBrandPage.selectFilterByTolerance("BMW Longlife-14 FE+", "?release%5B%5D=bmw-longlife-14-fe")
                .clickOnToleranceFilter("BMW Longlife-14 FE+");
        checkingContainsUrl(currentUrl);
        viscosityBrandPage.selectFilterByTolerance("BMW Longlife-14 FE+", "?release%5B%5D=bmw-longlife-14-fe")
                .clickOnToleranceFilter("Alle");
        checkingContainsUrl(currentUrl);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
