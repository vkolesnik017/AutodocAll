package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Categories_page_Logic;
import ATD.Moto_Category_page_Logic;
import ATD.Moto_Parent_Category_page_Logic;
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

public class QC_323_AddTopProductToBasket {

    private Moto_Categories_page_Logic categoriesPage = new Moto_Categories_page_Logic();
    private Moto_Parent_Category_page_Logic parentCategoryPage = new Moto_Parent_Category_page_Logic();
    private Moto_Category_page_Logic categoryPage = new Moto_Category_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2,moto_catalog2,moto_main,moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProducts(String route)  {
        openPage(route);

        String idOfAddedProduct = categoriesPage.getIdOfProductFromTecDocListing();
        categoriesPage.addProductToBasket().checkOfIdAddedProductInBasket(idOfAddedProduct);

    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsParentCategory(String route)  {
        openPage(route);

        String idOfAddedProduct = parentCategoryPage.getIdOfProductFromTecDocListing();
        parentCategoryPage.addProductToBasket().checkOfIdAddedProductInBasket(idOfAddedProduct);
          }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks total count of TOP products")
    public void testChecksTotalCountOfTopProductsCategory(String route)  {
        openPage(route);

        String idOfAddedProduct = categoryPage.getIdOfProductFromTecDocListing();
        categoryPage.addProductToBasket().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
