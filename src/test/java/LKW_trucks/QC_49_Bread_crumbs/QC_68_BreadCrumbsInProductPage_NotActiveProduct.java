package LKW_trucks.QC_49_Bread_crumbs;

import ATD.LKW_Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_68_BreadCrumbsInProductPage_NotActiveProduct {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Product page route with not active product")
    public void testChecksBreadCrumbsInProductPageNotActive(String route) {
        openPage(route);

        new LKW_Product_page_Logic().checkLinksInBreadCrumbsBlockWithNotActiveProduct()
                .goToTecDocCatalogFromBreadCrumbs().checkSuccessfullyLKWCategoriesPageLoading();
    }
}