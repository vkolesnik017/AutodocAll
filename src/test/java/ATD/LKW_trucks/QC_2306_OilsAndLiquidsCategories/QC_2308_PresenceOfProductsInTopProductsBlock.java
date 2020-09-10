package ATD.LKW_trucks.QC_2306_OilsAndLiquidsCategories;

import ATD.LKW_Category_page_Logic;
import ATD.LKW_Parent_Category_page_Logic;
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

public class QC_2308_PresenceOfProductsInTopProductsBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of products in TOP products block")
    public void testChecksPresenceOfProductsInTopProductsBlock(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().availabilityOfTopProductsBlock().comparisonOfProductAndGeneric("Motoröl");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category4,lkw_category_maker3,lkw_category_brand3,lkw_category_maker_brand4");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of products in TOP products block")
    public void testChecksPresenceOfProductsInTopProductsBlockCategory(String route) {
        openPage(route);
        new LKW_Category_page_Logic().availabilityOfTopProductsBlock()
                .comparisonOfProductAndGeneric("Motoröl");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
