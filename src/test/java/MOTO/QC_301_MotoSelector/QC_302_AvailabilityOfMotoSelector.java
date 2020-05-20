package MOTO.QC_301_MotoSelector;

import ATD.*;
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
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_302_AvailabilityOfMotoSelector {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_makers,moto_categories_maker2,moto_category_maker,moto_parent_category,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability Of Moto Selector ")
    public void testChecksAvailabilityOfMotoSelector (String route) {
        openPage(route);

        new Moto_Category_page_Logic()
                .availabilityOfMotoSelector();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog2");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability Of Moto Selector ")
    public void testChecksAvailabilityOfMotoSelectorCategories (String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .availabilityOfMotoSelector();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability Of Moto Selector ")
    public void testChecksAvailabilityOfMotoSelectorMain (String route) {
        openPage(route);

        new Moto_main_page_Logic()
                .availabilityOfMotoSelector();
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model");
    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability Of Moto Selector ")
    public void testChecksAvailabilityOfMotoSelectorCatalogModel (String route) {
        open(route);

        new Moto_Catalog_model_page_Logic()
                .availabilityOfMotoSelector();
    }

    @DataProvider(name = "routesProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
    }

    @Test(dataProvider = "routesProduct")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Availability Of Moto Selector ")
    public void testChecksAvailabilityOfMotoSelectorProduct(String route) {
        openPage(route);

        new Moto_Product_page_Logic()
                .availabilityOfMotoSelector();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }

}
