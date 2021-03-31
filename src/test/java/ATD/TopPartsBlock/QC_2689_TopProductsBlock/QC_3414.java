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

public class QC_3414 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_maker_car_list11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlock(String route) {
        openPage(route);
        new LKW_Categories_page_Logic().availabilityOfTopProductsBlock();
    }

    @DataProvider(name = "routesLKWCategory", parallel = true)
    Object[] dataProviderLKWCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_brand,lkw_category_maker_brand2,lkw_category_maker");
    }

    @Test(dataProvider = "routesLKWCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockLKWCategory(String route) {
        openPage(route);
        new LKW_Category_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesLKWCategories", parallel = true)
    Object[] dataProviderLKWCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesLKWCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockLKWCategories(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic().presenceOfTopBlock();
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main,moto_categories_maker,moto_catalog_model6,moto_categories");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockMoto(String route) {
        openPage(route);
        new Moto_main_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesMotoCategory", parallel = true)
    Object[] dataProviderMotoCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesMotoCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockMotoCategory(String route) {
        openPage(route);
        new Moto_Category_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesMotoFilter", parallel = true)
    Object[] dataProviderMotoFilter() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesMotoFilter")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockMotoFilter(String route) {
        openPage(route);
        new Moto_Parent_Category_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoryName", parallel = true)
    Object[] dataProviderCategoryName() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "category_name_brand15,category_maker_brand7,category_group_brand5,category_name,category_maker");
    }

    @Test(dataProvider = "routesCategoryName")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockCategoryName(String route) {
        openPage(route);
        new Category_name_brand_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoryNameSecond", parallel = true)
    Object[] dataProviderCategoryNameSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "category_maker_body,category_maker_drive,category_maker_brand,category_group,category_group_body");
    }

    @Test(dataProvider = "routesCategoryNameSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockCategoryNameSecond(String route) {
        openPage(route);
        new Category_name_brand_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoryNameThird", parallel = true)
    Object[] dataProviderCategoryNameThird() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "category_group_drive,category_group_fuel,category_group_year,category_group_brand,category_model");
    }

    @Test(dataProvider = "routesCategoryNameThird")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockCategoryNameThird(String route) {
        openPage(route);
        new Category_name_brand_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategoryNameFourth", parallel = true)
    Object[] dataProviderCategoryNameFourth() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "category_model_body,category_model_drive,category_model_fuel,category_model_year,category_model_brand2");
    }

    @Test(dataProvider = "routesCategoryNameFourth")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockCategoryNameFourth(String route) {
        openPage(route);
        new Category_name_brand_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesGroupList", parallel = true)
    Object[] dataProviderGroupList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "group_list,model_maker_list,category_name_parent,group_list_body,categories_maker");
    }

    @Test(dataProvider = "routesGroupList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockGroupList(String route) {
        openPage(route);
        new Group_list_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesGroupListSecond", parallel = true)
    Object[] dataProviderGroupListSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "group_list_drive,group_list_fuel,group_list_year,group_list_hp3,model_maker_list_year,model_maker_list_hp2");
    }

    @Test(dataProvider = "routesGroupListSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockGroupListSecond(String route) {
        openPage(route);
        new Group_list_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesSupplier", parallel = true)
    Object[] dataProviderSupplier() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","supplier2");
    }

    @Test(dataProvider = "routesSupplier")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockSupplier(String route) {
        openPage(route);
        new Supplier_page_Logic().presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesMakerList", parallel = true)
    Object[] dataProviderMakerList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","maker_car_list,categories");
    }

    @Test(dataProvider = "routesMakerList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check presence of TOP block")
    public void testChecksPresenceOfTopBlockMakerList(String route) {
        openPage(route);
        new Maker_car_list_page_Logic().presenceOfTopProductsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
