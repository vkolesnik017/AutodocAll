package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import ATD.Moto_Categories_page_Logic;
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

public class QC_630 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting of child categories")
    public void testChecksSortingOfChildCategories(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .checkSortingOfChildCategories();
    }

    @DataProvider(name = "routesCatalog", parallel = true)
    Object[] dataProviderCatalog() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routesCatalog")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting of child categories")
    public void testChecksSortingOfChildCategoriesCatalog(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .checkSortingOfChildCategories();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
