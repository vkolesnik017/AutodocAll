package ATD.QASYS_19_Basket;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QASYS_274 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

  @DataProvider(name = "routesMain", parallel = true)
  Object[] routesMain() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories,categories_maker,category_car_list,category_group,category_group_body," +
            "category_group_brand,category_group_drive,category_group_fuel,category_group_year,category_maker,category_maker_body,category_maker_brand,category_maker_drive," +
            "category_model,category_model_brand,category_name,category_name_parent,category_oen,group_list,group_list_body,group_list_drive,group_list_fuel,group_list_hp," +
            "group_list_year,index_accessories,index_instruments,listing_accessories,listing_instruments,maker_car_list,model_maker_list,model_maker_list_hp,model_maker_list_year," +
            "product,search,tyre_form,tyre_item13,tyres_brand,tyres_brand_dimension,tyres_dimension,tyres_group_season_brand,tyres_season," +
            "tyres_season_dimension,tyres_season_size");
  }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks the addition of products to the basket from all root main")
    public void testAddingProductToBasketFromAllRoutesMain(String route) {
        openPage(route);
        product_page_logic.checkNumberBasketAndRefreshPageIfNot();
        clickOfBuyBtnForAllPages();
        product_page_logic.closePopupOtherCategoryIfYes()
                .checksPresentProductInCartPopup()
                .cartClick()
                .productPrice().shouldBe(visible);
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] routesLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_categories_maker,lkw_category," +
                "lkw_category_brand,lkw_category_car_list,lkw_category_maker,lkw_category_maker_brand,lkw_category_model_brand,lkw_main,lkw_maker_car_list,lkw_parent_category," +
                "lkw_product,lkw_search");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks the addition of products to the basket from all root LKW")
    public void testAddingProductToBasketFromAllRoutesLKW(String route) {
        openPage(route);
        product_page_logic.checkNumberBasketAndRefreshPageIfNot();
        clickOfBuyBtnForAllPages();
        product_page_logic.closePopupOtherCategoryIfYes()
                .cartIcon().scrollIntoView("{block: \"center\"}").waitUntil(visible, 5000).hover();
        product_page_logic.firstProductPriceInPopupOfCart().shouldBe(visible);
        product_page_logic.cartClick()
                .productPrice().shouldBe(visible);
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] routesMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog,moto_catalog_model,moto_categories_maker," +
                "moto_categories,moto_category,moto_category_car_list,moto_category_car_list_model,moto_category_maker,moto_main,moto_parent_category,moto_parent_category_maker," +
                "moto_product");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "The test checks the addition of products to the basket from all root Moto")
    public void testAddingProductToBasketFromAllRoutesMoto(String route) {
        openPage(route);
        product_page_logic.checkNumberBasketAndRefreshPageIfNot();
        clickOfBuyBtnForAllPages();
        product_page_logic.closePopupOtherCategoryIfYes()
                .cartIcon().scrollIntoView("{block: \"center\"}").waitUntil(visible, 5000).hover();
        product_page_logic.firstProductPriceInPopupOfCart().shouldBe(visible);
        product_page_logic.cartClick()
                .productPrice().shouldBe(visible);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
