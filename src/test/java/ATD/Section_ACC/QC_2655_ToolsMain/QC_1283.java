package ATD.Section_ACC.QC_2655_ToolsMain;

import ATD.Cart_page_Logic;
import ATD.Index_instruments_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1283 {

    private String nameProduct, titleProduct;
    private Index_instruments_page_Logic index_instruments_page_logic = new Index_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks adding a product to the cart when selecting it from top-6 products block ")
    public void testChecksAddingProductToCartWithTop6BlockForInstruments(String route) {
        openPage(route);
        nameProduct = index_instruments_page_logic.getNameFirstProductInTop6ProductBlock();
        index_instruments_page_logic.clickOnFirstBtnAddToBasketInTop6ProductsBlock()
                .clickOnBtnGoToBasket();
        titleProduct = new Cart_page_Logic().getNameTitleProduct();
        Assert.assertEquals(nameProduct, titleProduct);

    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
