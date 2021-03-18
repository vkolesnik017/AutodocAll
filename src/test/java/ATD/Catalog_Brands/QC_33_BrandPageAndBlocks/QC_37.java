package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import ATD.LKW_Category_maker_brand_page_Logic;
import ATD.LKW_main_page_Logic;
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

public class QC_37 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of top_brands block")
    public void testChecksVisibilityOfTopBrandsBlock(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .visibilityOfTopBrandsBlock()
                .clickOnLinkMoreOfTopBrand()
                .sizeOfBrandsMoreThen(6);
    }

    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderForMainPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility top_brands block")
    public void testChecksVisibilityOfTopBrandsBlockAtMainPage(String mainPageRoute) {
        openPage(mainPageRoute);
        new LKW_main_page_Logic().visibilityOfTopBrandsBlock();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
