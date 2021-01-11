package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Cart_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3218 {

    Product_page_Logic productPage = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product66");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check add product to basket from gluing block on motorcycle page")
    public void testCheckAddProductToBasketFromGluingBlock(String route) {
        openPage(route);
        String idOfAddedVolume = productPage.presenceOfGluingBlock().getIdOfGluingProduct(0);
        productPage.clickOnBtnAddGluingProductToBasket(0)
                .closeBasketPopUp()
                .hoverOnBasketInHeader()
                .clickOnBasketInHeader();
        new Cart_page_Logic().checkOfIdAddedProductInBasket(idOfAddedVolume);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
