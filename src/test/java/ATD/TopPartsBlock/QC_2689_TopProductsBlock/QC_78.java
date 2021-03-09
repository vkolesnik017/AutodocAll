package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_main_page_Logic;
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

public class QC_78 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11,lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition from Top product block to product page")
    public void testChecksTransitionToProductPageMain(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .transitionToProductPageByClickOnTopImage()
                .transitionToProductPageByClickOnTitleOfTopProduct()
                .transitionToProductPageByClickOnLinkDetails();
    }

    @DataProvider(name = "routesCategoriesMaker", parallel = true)
    Object[] dataProviderCategoriesMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routesCategoriesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition from Top product block to product page")
    public void testChecksTransitionToProductPageCategoriesMaker(String route) {
        openPage(route);
        String urlOfProduct = new LKW_Categories_maker_page_Logic().getUrlOfTopProductFromHisTitle(0);
        new LKW_Categories_maker_page_Logic()
                .transitionToProductPageByClickOnTopImage(urlOfProduct)
                .transitionToProductPageByClickOnTitleOfTopProduct(urlOfProduct)
                .transitionToProductPageByClickOnLinkDetails(urlOfProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
