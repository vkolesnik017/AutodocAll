package PKW.ACC.QC_1020_BlockTopProductsOnMainACC;

import PKW.Cart_page_Logic;
import PKW.Index_accessories_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1026_AddProductFromBlockTopProductsToBasket {

    private String nameProduct, nameProductInBasket;
    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks add product from block top products to basket ")
    public void testCheckingAddProductFromTopProductsToBasket(String route) {
        openPage(route);
        nameProduct = index_accessories_page_logic.getNameFirstProductInBlockTopProducts();
        index_accessories_page_logic.clickOnFirstBtnAddProductToBasketInBlockTopProducts();
        index_accessories_page_logic.clickBtnGoToBasket();
        nameProductInBasket = new Cart_page_Logic().getNameAddedProductInBasket();
        Assert.assertEquals(nameProduct, nameProductInBasket);

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
