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

public class QC_28_AvailabilityOfHeadlineForBrandsAndModelsBlocks {
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
    @Description(value = "Test availability of headline for brands and models blocks")
    public void testChecksAvailabilityOfHeadLineForBrandsAndModelsBlocks(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .availabilityOfHeadlineOfTopBlockBrands();
    }

    @DataProvider(name = "routesCategoryMakerBrand", parallel = true)
    Object[] dataProviderCategoryMakerBrand() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand,lkw_category_maker2");
    }

    @Test(dataProvider = "routesCategoryMakerBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test availability of headline for brands and models blocks")
    public void testChecksAvailabilityOfHeadLineForBrandsAndModelsBlocksCategoryMakerBrand(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .availabilityOfHeadlineOfTopModelBlock("Volvo");
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test availability of headline for brands and models blocks")
    public void testChecksAvailabilityOfHeadLineForBrandsAndModelsBlocksCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .availabilityOfHeadlineOfTopModelBlock("MERCEDES-BENZ")
                .checkOfModelCountInModelBlock();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test availability of headline for brands and models blocks")
    public void testChecksAvailabilityOfHeadLineForBrandsAndModelsBlocksCategoriesMain(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .availabilityOfHeadlineOfBrandsModelBlock();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
