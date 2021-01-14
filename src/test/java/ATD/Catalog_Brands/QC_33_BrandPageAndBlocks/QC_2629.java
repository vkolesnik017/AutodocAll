package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import ATD.Category_name_page_Logic;
import ATD.Main_page_Logic;
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

public class QC_2629 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to brand page")
    public void testChecksTransitionToBrandPage(String route) {
        openPage(route);

        new Main_page_Logic().presenceOfTopBrandsBlock().checkTransitionToBrandPageByAllBrands();
    }

    @DataProvider(name = "routesCategoryName", parallel = true)
    Object[] dataProviderCategoryName() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name10,category_maker3,category_maker_body4,category_maker_drive3,category_group3,category_group_body3,category_group_drive3,category_group_fuel3,category_group_year3");
    }

    @Test(dataProvider = "routesCategoryName")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to brand page")
    public void testChecksTransitionToBrandPageCategoryName(String route) {
        openPage(route);

        new Category_name_page_Logic().presenceOfTopProductsBlock().checkTransitionToBrandPageByAllBrands();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
