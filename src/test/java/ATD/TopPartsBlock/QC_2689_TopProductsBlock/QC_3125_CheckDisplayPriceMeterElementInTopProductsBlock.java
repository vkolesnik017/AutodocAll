package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Category_name_brand_page_Logic;
import ATD.LKW_Category_brand_page_Logic;
import AWS.PriceProductDescription_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3125_CheckDisplayPriceMeterElementInTopProductsBlock {
    Category_name_brand_page_Logic brandPage = new Category_name_brand_page_Logic();
    LKW_Category_brand_page_Logic lkwBrandPage = new LKW_Category_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_brand12,category_name_brand13");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlock(String route) {
        openPage(route);
        List<String> idOfProduct = brandPage.getIdOfProduct();
        List<String> priceTitle = brandPage.getPriceTitle();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_brand5");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the display of the 'price per meter' element in the TOP products block")
    public void testCheckDisplayPriceMeterElementInTopProductsBlockLKW(String route) {
        openPage(route);
        List<String> idOfProduct = lkwBrandPage.getIdOfProduct();
        List<String> priceTitle = lkwBrandPage.getPriceTitle();
        new PriceProductDescription_aws().openPriceDescriptionPage().checkPresenceOfPriceTitle(idOfProduct, priceTitle);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
