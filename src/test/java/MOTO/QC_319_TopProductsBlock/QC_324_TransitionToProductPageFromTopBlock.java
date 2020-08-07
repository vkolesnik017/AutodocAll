package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.Moto_Parent_Category_page_Logic;
import ATD.Moto_main_page_Logic;
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

public class QC_324_TransitionToProductPageFromTopBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main,moto_categories_maker2,moto_categories,moto_catalog2,moto_catalog_model2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to product page from TOP block")
    public void testChecksTransitionToProductPageFromTopBlock(String route) {
        openPage(route);
        String titleOfBrand = new Moto_main_page_Logic().getBrandFromTopProductTitle();
        new Moto_main_page_Logic()
                .goToProductPageFromTopBlock(titleOfBrand);
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to product page from TOP block")
    public void testChecksTransitionToProductPageFromTopBlockCategory(String route) {
        openPage(route);
        String titleOfBrand = new Moto_Category_page_Logic().getBrandFromTopProductTitle();
        new Moto_Category_page_Logic()
                .goToProductPageFromTopBlock(titleOfBrand);
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to product page from TOP block")
    public void testChecksTransitionToProductPageFromTopBlockParentCategory(String route) {
        openPage(route);
        String titleOfBrand = new Moto_Parent_Category_page_Logic().getBrandFromTopProductTitle();
        new Moto_Parent_Category_page_Logic()
                .goToProductPageFromTopBlock(titleOfBrand);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
