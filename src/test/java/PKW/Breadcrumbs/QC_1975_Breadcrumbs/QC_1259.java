package PKW.Breadcrumbs.QC_1975_Breadcrumbs;

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

public class QC_1259 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinks(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesViscosityBrand", parallel = true)
    Object[] dataProviderViscosityBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand");
    }

    @Test(dataProvider = "routesViscosityBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksViscosityBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_brand_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesSpecification", parallel = true)
    Object[] dataProviderSpecification() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_specification");
    }

    @Test(dataProvider = "routesSpecification")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksSpecification(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesRelease", parallel = true)
    Object[] dataProviderRelease() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release");
    }

    @Test(dataProvider = "routesRelease")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksRelease(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesBrand", parallel = true)
    Object[] dataProviderBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_brand");
    }

    @Test(dataProvider = "routesBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_Brand_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksMaker(String route) throws SQLException {
        openPage(route);

        new Motoroil_Maker_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @DataProvider(name = "routesChemicalType", parallel = true)
    Object[] dataProviderChemicalType() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_chemical_type");
    }

    @Test(dataProvider = "routesChemicalType")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksChemicalType(String route) throws SQLException {
        openPage(route);

        new Motoroil_Chemical_Type_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

/*    @DataProvider(name = "routesMakerGroup", parallel = true)
    Object[] dataProviderMakerGroup() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker_group");
    }

    @Test(dataProvider = "routesMakerGroup")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksMakerGroup(String route) throws SQLException {
        openPage(route);

        new Motoroil_Maker_Group_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }*/

    @DataProvider(name = "routesCarParts", parallel = true)
    Object[] dataProviderCarParts() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts_motoroil");
    }

    @Test(dataProvider = "routesCarParts", enabled = false)
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on breadCrumbs links")
    public void testChecksTransitionByClickOnBreadCrumbsLinksCarParts(String route) throws SQLException {
        openPage(route);

        new Car_parts_motoroil_page_Logic()
                .checkTransitionByClickOnLinksOfBreadCrumbs();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
