package PKW.OILS.QC_1255_BreadCrumbsBlockInListing;

import PKW.Car_parts_motoroil_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1257_StructureOfBreadCrumbsBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity,motoroil_specification,motoroil_release,motoroil_brand,motoroil_maker,motoroil_chemical_type");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of breadCrumbs block")
    public void testChecksStructureOfBreadCrumbsBlock(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_page_Logic()
                .presenceOfBreadCrumbsBlock()
                .countOfLinksInBreadCrumbsBlock(3);
    }

    @DataProvider(name = "routesBrand", parallel = true)
    Object[] dataProviderBrand() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_viscosity_brand,motoroil_maker_group");
    }


    @Test(dataProvider = "routesBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of breadCrumbs block")
    public void testChecksStructureOfBreadCrumbsBlockBrand(String route) throws SQLException {
        openPage(route);

        new Motoroil_viscosity_brand_page_Logic()
                .presenceOfBreadCrumbsBlock()
                .countOfLinksInBreadCrumbsBlock(4);
    }

    @DataProvider(name = "routesCarPartMotoroil", parallel = true)
    Object[] dataProviderCarPartMotoroil() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts_motoroil");
    }


    @Test(dataProvider = "routesCarPartMotoroil")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of breadCrumbs block")
    public void testChecksStructureOfBreadCrumbsBlockCarPartMotoroil(String route) throws SQLException {
        openPage(route);

        new Car_parts_motoroil_page_Logic()
                .presenceOfBreadCrumbsBlock()
                .countOfLinksInBreadCrumbsBlock(5);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
