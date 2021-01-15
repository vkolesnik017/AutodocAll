package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.LKW_Product_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_68 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Product page route with active product")
    public void testChecksBreadCrumbsInProductPageActive(String route) throws SQLException {
        openPage(route);

        new LKW_Product_page_Logic().checkLinksInBreadCrumbsBlock()
                .checkLinksInBreadCrumbsWithTruck();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_category_car_list48"));
    }

    @DataProvider(name = "routesNotActive", parallel = true)
    Object[] dataProviderNotActive() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product2");
    }

    @Test(dataProvider = "routesNotActive")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks bread crumbs block in Product page route with not active product")
    public void testChecksBreadCrumbsInProductPageNotActive(String route) throws SQLException {
        openPage(route);

        new LKW_Product_page_Logic().checkLinksInBreadCrumbsBlockWithNotActiveProduct()
                .goToTecDocCatalogFromBreadCrumbs().checkSuccessfullyLKWCategoriesPageLoading();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
