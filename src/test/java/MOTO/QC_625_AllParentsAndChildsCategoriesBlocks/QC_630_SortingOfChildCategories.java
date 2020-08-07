package MOTO.QC_625_AllParentsAndChildsCategoriesBlocks;

import ATD.Moto_Categories_page_Logic;
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

public class QC_630_SortingOfChildCategories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2");
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
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
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
