package LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Product_page_Logic;
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

public class QC_68_BreadCrumbsInProductPage_ActiveProduct {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Product page route with active product")
    public void testChecksBreadCrumbsInProductPageActive(String route) {
        openPage(route);

        new LKW_Product_page_Logic().checkLinksInBreadCrumbsBlock()
                .checkLinksInBreadCrumbsWithTruck()
                .checkSuccessfullyLKWCategoryCarListPageLoading("https://lkwteile.autodoc.de/ersatzteile/innenraumluftfilter-200166/daf/95-xf?car_id=1003688");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
