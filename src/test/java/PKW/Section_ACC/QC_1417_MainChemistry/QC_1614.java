package PKW.Section_ACC.QC_1417_MainChemistry;

import Common.SetUp;
import PKW.Cart_page_Logic;
import PKW.Index_chemicals_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1614 {

    private String idProduct, idAddedProductInBasket;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks add product to basket from top products block.")
    public void testCheckingAddProductToBasketFromTopProductsBlock(String route) {
        openPage(route);
        idProduct = index_chemicals_page_logic.getIdProductFromTopProducts();
        index_chemicals_page_logic.clickOnFirstBtnAddProductToBasketInBlockTopProducts();
        index_chemicals_page_logic.cartClick();
        idAddedProductInBasket = new Cart_page_Logic().getIdAddedProduct();
        Assert.assertEquals(idProduct, idAddedProductInBasket);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
