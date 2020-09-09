package MOTO.QC_301_MotoSelector;

import ATD.Moto_Catalog_model_page_Logic;
import ATD.Moto_Catalog_page_Logic;
import ATD.Moto_Categories_maker_page_Logic;
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

public class QC_308_PresenceOfFilledValuesInSelector {
    private Moto_Categories_maker_page_Logic categoriesMakerPage = new Moto_Categories_maker_page_Logic();
    private Moto_Catalog_model_page_Logic catalogPage = new Moto_Catalog_model_page_Logic();
    private Moto_Catalog_page_Logic motoCatalogPage = new Moto_Catalog_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }
    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Presence of filled values in the selector")
    public void testChecksPresenceOfFilledValuesInSelector(String route) {
        openPage(route);
        String brandOfMoto = categoriesMakerPage.getBrandOfMotoFromUrl();
        categoriesMakerPage.presenceBrandOfMotoInSelector(brandOfMoto);
    }

    @DataProvider(name = "routesCatalogModel", parallel = true)
    Object[] dataProviderCatalogModel() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2,moto_category_car_list_model3");
    }

    @Test(dataProvider = "routesCatalogModel")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Presence of filled values in the selector")
    public void testChecksPresenceOfFilledValuesInSelectorCatalogModel(String route) {
        open(route);
        String brandOfMoto = catalogPage.getBrandOfMotoFromUrl();
        String modelOfMoto = catalogPage.getModelOfMotoFromUrl();
        catalogPage.presenceBrandAndModelOfMotoInSelector(brandOfMoto, modelOfMoto);
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2,moto_catalog");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Presence of filled values in the selector")
    public void testChecksPresenceOfFilledValuesInSelectorCatalog(String route) {
        openPage(route);
        String brandOfMoto = motoCatalogPage.getBrandOfMotoFromUrl();
        String modelOfMoto = motoCatalogPage.getModelOfMotoFromUrl();
        String motorOfMoto = motoCatalogPage.getValueOfMotorOfMotoFromUrl();
        motoCatalogPage.presenceBrandAndModelOfMotoInSelector(brandOfMoto, modelOfMoto, motorOfMoto);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
