package ATD.LKW_trucks.QC_73_TopProductsBlock;

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

public class QC_77 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check addition information of product in Top block")
    public void testChecksAdditionInfoInTopBlockMain(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .visibilityOfAdditionInfoInTopBlock();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_maker2,lkw_category_brand,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check addition information of product in Top block")
    public void testChecksAdditionInfoInTopBlock(String route) {
        openPage(route);
        new LKW_Category_page_Logic()
                .visibilityOfAdditionInfoInTopBlock();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check addition information of product in Top block")
    public void testChecksAdditionInfoInTopBlockCategoriesMaker(String route) {
        openPage(route);
        new LKW_Categories_maker_page_Logic()
                .visibilityOfAdditionInfoInTopBlock();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories,lkw_maker_car_list2,lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check addition information of product in Top block")
    public void testChecksAdditionInfoInTopBlockCategories(String route) {
        openPage(route);
        new LKW_Categories_page_Logic()
                .visibilityOfAdditionInfoInTopBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
