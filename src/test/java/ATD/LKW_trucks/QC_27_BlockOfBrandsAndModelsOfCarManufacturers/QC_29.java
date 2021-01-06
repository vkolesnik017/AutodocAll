package ATD.LKW_trucks.QC_27_BlockOfBrandsAndModelsOfCarManufacturers;

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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_29 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability of brands and models blocks")
    public void testChecksAvailabilityOfBrandsAndModelsBlocksCategory(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .availabilityOfTopBrandsBlock()
                .visibilityOfBrandsInTopBlock();
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability of brands and models blocks")
    public void testChecksAvailabilityOfBrandsAndModelsBlocksCategoryMaker(String route) {
        openPage(route);
        new LKW_Category_maker_Logic()
                .availabilityOfTopBrandsBlock()
                .availabilityOfElementsInTopBrandsBlock();
    }
    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability of brands list block")
    public void testChecksAvailabilityOfBrandsListBlocksMakers(String route) {
        openPage(route);
        new LKW_makers_page_Logic()
                .visibilityOfBrandsList()
                .checkOfElementsInBrandsList();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check of all models list")
    public void testChecksOfAllModelsListCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .availabilityOfModelListBlock()
                .checkOfElementsInModelListBlock();
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of auto parts for TOP brands block")
    public void testCheckPresenceOfAutoPartsForTopBrandsBlock(String route) {
        openPage(route);
        new LKW_main_page_Logic()
        .availabilityOfAutoPartsTopBrandsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
