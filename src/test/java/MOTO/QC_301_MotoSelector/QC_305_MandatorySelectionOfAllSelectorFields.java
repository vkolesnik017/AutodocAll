package MOTO.QC_301_MotoSelector;

import ATD.*;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_305_MandatorySelectionOfAllSelectorFields {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_car_list2,moto_makers,moto_categories_maker2,moto_category_car_list_model2,moto_category_maker,moto_parent_category,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Mandatory Selection Of All Selector Fields")
    public void testChecksMandatorySelectionOfAllSelectorFields(String route) {
        openPage(route);

        new Moto_Category_page_Logic()
                .visibilityOfToolTipForMarkeField()
                .visibilityOfToolTipForModelField()
                .visibilityOfToolTipForMotorField();
    }


    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_categories");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Mandatory Selection Of All Selector Fields")
    public void testChecksMandatorySelectionOfAllSelectorFieldsCategories(String route) {
        openPage(route);

        new Moto_Catalog_page_Logic()
                .visibilityOfToolTipForMarkeField()
                .visibilityOfToolTipForModelField()
                .visibilityOfToolTipForMotorField();
    }

    @DataProvider(name = "routesProductPage", parallel = true)
    Object[] dataProviderProductPage() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
    }

    @Test(dataProvider = "routesProductPage")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Mandatory Selection Of All Selector Fields")
    public void testChecksMandatorySelectionOfAllSelectorFieldsProductPage(String route) {
        openPage(route);

        new Moto_Product_page_Logic()
                .visibilityOfToolTipForMarkeField()
                .visibilityOfToolTipForModelField()
                .visibilityOfToolTipForMotorField();
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Mandatory Selection Of All Selector Fields")
    public void testChecksMandatorySelectionOfAllSelectorFieldsCatalogModel(String route) {
        open(route);

        new Moto_Catalog_model_page_Logic()
                .visibilityOfToolTipForMarkeField()
                .visibilityOfToolTipForModelField()
                .visibilityOfToolTipForMotorField();
    }

    @DataProvider(name = "routesMainPage", parallel = true)
    Object[] dataProviderMainPage() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routesMainPage")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Mandatory Selection Of All Selector Fields")
    public void testChecksMandatorySelectionOfAllSelectorFieldsMainPage(String route) {
        openPage(route);

        new Moto_main_page_Logic()
                .visibilityOfToolTipForMarkeField()
                .visibilityOfToolTipForModelField()
                .visibilityOfToolTipForMotorField();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
