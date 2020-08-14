package PKW.OILS.QC_1269_RelinkingBlockInListings;

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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1279_TransitionByClickOnValueInRelinkingBlocks {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocks(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_brand_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }

   @DataProvider(name = "routesSpecification", parallel = true)
    Object[] dataProviderSpecification() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_specification");
    }

    @Test(dataProvider = "routesSpecification")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocksSpecification(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }

    @DataProvider(name = "routesRelease", parallel = true)
    Object[] dataProviderRelease() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release");
    }

    @Test(dataProvider = "routesRelease")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocksRelease(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocksMaker(String route) throws SQLException {
        openPage(route);

        new Motoroil_Maker_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }


    @DataProvider(name = "routesMakerGroup", parallel = true)
    Object[] dataProviderMakerGroup() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker_group");
    }

    @Test(dataProvider = "routesMakerGroup")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocksMakerGroup(String route) throws SQLException {
        openPage(route);

        new Motoroil_Maker_Group_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }

    @DataProvider(name = "routesChemical", parallel = true)
    Object[] dataProviderChemical() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_chemical_type");
    }

    @Test(dataProvider = "routesChemical")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on value in Relinking blocks")
    public void testChecksTransitionByClickOnValueInRelinkingBlocksChemical(String route) throws SQLException {
        openPage(route);

        new Motoroil_Chemical_Type_page_Logic()
                .checkTransitionByClickInRelinkingBlock();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
