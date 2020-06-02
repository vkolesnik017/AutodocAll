package ATD.ACC.QC_950_BlockTopProductsOnMainAccessories;

import ATD.Cart_page_Logic;
import ATD.Index_accessories_page_Logic;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_962_AddProductTopProductsBlockInBasket {

    private String nameProduct,titleProduct;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks adding a product to the cart when selecting it from top-6 block ")
    public void testChecksAddingProductToCartWithTop6Block(String route) {
        openPage(route);
        nameProduct = index_accessories_page_logic.getNameFirstProductInTop6Block();
        index_accessories_page_logic.clickOnFirstBtnAddToBasketInTop6Block()
                .clickOnBtnGoToBasket();
        titleProduct = new Cart_page_Logic().getNameTitleProduct();
        Assert.assertEquals(nameProduct, titleProduct);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
