package LKW_trucks.QC_33_BrandsBLock;

import ATD.LKW_Category_maker_brand_page_Logic;
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

public class QC_40_OpenAndCloseOfBrandsBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of top_brands block")
    public void testChecksOpportunityToOpenAndCloseOfBrandsBlock(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .openOfTopBrandsBlock()
                .closeOfTopBrandsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
