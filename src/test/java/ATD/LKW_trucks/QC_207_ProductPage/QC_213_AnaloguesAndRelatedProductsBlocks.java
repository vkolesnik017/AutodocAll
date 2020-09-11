package ATD.LKW_trucks.QC_207_ProductPage;

import ATD.Cart_page_Logic;
import ATD.LKW_Product_page_Logic;
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

public class QC_213_AnaloguesAndRelatedProductsBlocks {

    private LKW_Product_page_Logic productPage = new LKW_Product_page_Logic();
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Analogues and related products blocks")
    public void testChecksAnaloguesAndRelatedProductsBlocks(String route) {
        openPage(route);
        productPage.visibilityOfAnaloguesAndRelatedProductsBlock()
        .visibilityOfAnaloguesAdditionInfoBlock()
        .visibilityOfRelatedAdditionInfoBlock();
        String idOfAddedProduct = productPage.getIdOfRelatedProduct();
        productPage.addRelatedProductToBasket();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
