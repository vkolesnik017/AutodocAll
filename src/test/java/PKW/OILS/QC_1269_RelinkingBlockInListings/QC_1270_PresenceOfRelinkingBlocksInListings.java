package PKW.OILS.QC_1269_RelinkingBlockInListings;

import PKW.Motoroil_Chemical_Type_page_Logic;
import PKW.Motoroil_Maker_page_Logic;
import PKW.Motoroil_specification_page_Logic;
import PKW.Motoroil_viscosity_brand_page_Logic;
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

public class QC_1270_PresenceOfRelinkingBlocksInListings {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand,motoroil_release");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Relinking blocks in listings")
    public void testChecksPresenceOfRelinkingBlocksInListings(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_brand_page_Logic()
                .presenceOfRelinkingBlocks(4)
                .checkElementsOfRelinkingBlocks();
    }

    @DataProvider(name = "routesSpecification", parallel = true)
    Object[] dataProviderSpecification() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_specification,motoroil_maker_group");
    }

    @Test(dataProvider = "routesSpecification")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Relinking blocks in listings")
    public void testChecksPresenceOfRelinkingBlocksInListingsSpecification(String route) throws SQLException {
        openPage(route);

        new Motoroil_specification_page_Logic()
                .presenceOfRelinkingBlocks(3)
                .checkElementsOfRelinkingBlocks();
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_maker");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Relinking blocks in listings")
    public void testChecksPresenceOfRelinkingBlocksInListingsMaker(String route) throws SQLException {
        openPage(route);

        new Motoroil_Maker_page_Logic()
                .presenceOfRelinkingBlocks(2)
                .checkElementsOfRelinkingBlocks();
    }

    @DataProvider(name = "routesChemical", parallel = true)
    Object[] dataProviderChemical() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil_chemical_type");
    }

    @Test(dataProvider = "routesChemical")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Relinking blocks in listings")
    public void testChecksPresenceOfRelinkingBlocksInListingsChemical(String route) throws SQLException {
        openPage(route);

        new Motoroil_Chemical_Type_page_Logic()
                .presenceOfRelinkingBlocks(1)
                .checkElementsOfRelinkingBlocks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}