package ATD.Catalog_Mark.QC_2568_MarkPages;

import ATD.LKW_Category_maker_Logic;
import ATD.LKW_Category_page_Logic;
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

public class QC_31 {

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
    @Description(value = "Test check opportunity to open/close of brands/models block")
    public void testChecksOpportunityToOpenAndCloseBrandBlock(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .openOfBrandsBlock()
                .brandBlockInOpenCondition()
                .closeOfBrandsBlock();
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check opportunity to open/close of brands/models block")
    public void testChecksOpportunityToOpenAndCloseModelsBlock(String route) {
        openPage(route);
        new LKW_Category_maker_Logic()
                .openOfModelsBlock()
                .modelBlockInOpenCondition()
                .closeOfModelsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
