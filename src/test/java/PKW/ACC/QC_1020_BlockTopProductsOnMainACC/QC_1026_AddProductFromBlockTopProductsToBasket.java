package PKW.ACC.QC_1020_BlockTopProductsOnMainACC;

import Common.SetUp;
import PKW.Cart_page_Logic;
import PKW.Index_accessories_page_Logic;
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

public class QC_1026_AddProductFromBlockTopProductsToBasket {

    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories,index_accessories_group");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks add product from block top products to basket ")
    public void testCheckingAddProductFromTopProductsToBasket(String route) {
        openPage(route);
        String nameProduct = index_accessories_page_logic.getIdFirstProductInBlockTopProducts();
        index_accessories_page_logic.clickOnFirstBtnAddProductToBasketInBlockTopProducts();
        index_accessories_page_logic.clickBtnGoToBasket();
        String nameProductInBasket = new Cart_page_Logic().getIdAddedProduct();
        Assert.assertEquals(nameProduct, nameProductInBasket);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
