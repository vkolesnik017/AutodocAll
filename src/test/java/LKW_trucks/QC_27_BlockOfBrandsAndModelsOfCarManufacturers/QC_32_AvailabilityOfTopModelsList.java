package LKW_trucks.QC_27_BlockOfBrandsAndModelsOfCarManufacturers;

import ATD.LKW_Category_brand_page_Logic;
import ATD.LKW_Category_page_Logic;
import ATD.SetUp;
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

public class QC_32_AvailabilityOfTopModelsList {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability of TOP model list in TOP brands block")
    public void testChecksAvailabilityOfTopModelsList(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .openOfBrandsBlock()
                .brandBlockInOpenCondition()
                .availabilityOfPopularModelList()
                .selectPopularModel(4, 1)
                .checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/volvo/fh-16");

    }

    @DataProvider(name = "routesCategoryBrand", parallel = true)
    Object[] dataProviderCategoryBrand() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand");
    }

    @Test(dataProvider = "routesCategoryBrand")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check availability of TOP model list in TOP brands block")
    public void testChecksAvailabilityOfTopModelsListCategoryBrand(String route) {
        openPage(route);
        new LKW_Category_brand_page_Logic()
                .openOfBrandsBlock()
                .brandBlockInOpenCondition()
                .availabilityOfPopularModelList()
                .selectPopularModel(4, 1)
                .checkSuccessfullyLKWCategoryModelBrandPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/volvo/fh-16/mf-mann-filter");

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
