package PKW.OILS.QC_1249_MainHeadlineBlockInListings;

import PKW.*;
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

public class QC_1251_PresenceOfCharacteristicInMainHeadline {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand,motoroil_maker,motoroil_chemical_type,motoroil_specification"); //motoroil_maker_group,
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of characteristic in main Headline")
    public void testChecksPresenceOfMainHeadlineInListings(String route) throws SQLException {
        openPage(route);

        new Motoroil_Brand_page_Logic()
                .presenceOfCharacteristicInMainHeadline();
    }

    @DataProvider(name = "routesViscosity", parallel = true)
    Object[] dataProviderViscosity() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity");
    }

    @Test(dataProvider = "routesViscosity")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of characteristic in main Headline")
    public void testChecksPresenceOfMainHeadlineInListingsViscosity(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_page_Logic()
                .presenceOfCharacteristicInMainHeadline("0W-30");
    }

    @DataProvider(name = "routesViscosityBrand", parallel = true)
    Object[] dataProviderViscosityBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand");
    }

    @Test(dataProvider = "routesViscosityBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of characteristic in main Headline")
    public void testChecksPresenceOfMainHeadlineInListingsViscosityBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_brand_page_Logic()
                .presenceOfCharacteristicInMainHeadline("MOBIL 0W-30");
    }

    @DataProvider(name = "routesMotoroilRelease", parallel = true)
    Object[] dataProviderMotoroilRelease() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release");
    }

    @Test(dataProvider = "routesMotoroilRelease")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of characteristic in main Headline")
    public void testChecksPresenceOfMainHeadlineInListingsMotoroilRelease(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .presenceOfCharacteristicInMainHeadline("BMW LONGLIFE-01");
    }

    @DataProvider(name = "routesMotoroil", parallel = true)
    Object[] dataProviderMotoroil() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts_motoroil");
    }

    @Test(dataProvider = "routesMotoroil")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of characteristic in main Headline")
    public void testChecksPresenceOfMainHeadlineInListingsMotoroil(String route) throws SQLException {
        openPage(route);

        new Car_parts_motoroil_page_Logic()
                .presenceOfCharacteristicInMainHeadline("VW Caddy IV Kastenwagen (SAA, SAH) 1.6TDI 75 PS AB 05.2015");
    }
    @AfterMethod
    public void close() {
        closeWebDriver(); }
}
