package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.*;
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

public class QC_76 {
    private LKW_main_page_Logic mainPage = new LKW_main_page_Logic();
    private LKW_Category_page_Logic categoryPage = new LKW_Category_page_Logic();
    private LKW_Parent_Category_page_Logic parentCategoryPage = new LKW_Parent_Category_page_Logic();
    private LKW_Categories_maker_page_Logic categoriesMakerPage = new LKW_Categories_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main,lkw_categories,lkw_maker_car_list2,lkw_category_car_list11");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check adding TOP products to basket")
    public void testChecksAddingTopProductsToBasketMain(String route) {
        openPage(route);
        String idOfAddedProduct = mainPage.getIdOfTopProduct();
        mainPage.addTopProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker2,lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check adding TOP products to basket")
    public void testChecksAddingTopProductsToBasketCategory(String route) {
        openPage(route);
        String idOfAddedProduct = categoryPage.getIdOfTopProduct();
        categoryPage.addTopProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check adding TOP products to basket")
    public void testChecksAddingTopProductsToBasketParentCategory(String route) {
        openPage(route);
        String idOfAddedProduct = parentCategoryPage.getIdOfTopProduct();
        parentCategoryPage.addTopProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }


    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check adding TOP products to basket")
    public void testChecksAddingTopProductsToBasketCategoriesMaker(String route) {
        openPage(route);
        String idOfAddedProduct = categoriesMakerPage.getIdOfTopProduct();
        categoriesMakerPage.addTopProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
