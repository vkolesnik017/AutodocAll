package MOTO.QC_385_BlockBrandsAndModelsOfAutoManufacturers;

import ATD.Moto_Categories_maker_page_Logic;
import ATD.Moto_Categories_page_Logic;
import ATD.Moto_makers_page_Logic;
import Common.SetUp;
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

public class QC_388_ComponentsOfBrandAndModelBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_category_maker3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks components of the brand and model block")
    public void testChecksComponentsOfBrandAndModelBlock(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic().checkComponentsOfModelBlock();
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks components of the brand and model block")
    public void testChecksComponentsOfBrandAndModelBlockMakers(String route) {
        openPage(route);

        new Moto_makers_page_Logic().checkComponentsOfBrandsBlock();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks components of the brand and model block")
    public void testChecksComponentsOfBrandAndModelBlockCategories(String route) {
        openPage(route);

        new Moto_Categories_page_Logic().checkComponentsOfBrandsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
