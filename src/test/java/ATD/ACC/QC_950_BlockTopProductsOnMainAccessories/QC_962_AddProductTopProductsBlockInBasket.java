package ATD.ACC.QC_950_BlockTopProductsOnMainAccessories;

import ATD.Cart_page_Logic;
import ATD.Index_accessories_page_Logic;
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

public class QC_962_AddProductTopProductsBlockInBasket {

    private Index_accessories_page_Logic index_accessories_page_logic = new Index_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories,index_accessories_group");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks adding a product to the cart when selecting it from top-6 block")
    public void testChecksAddingProductToCartWithTop6BlockForAccessories(String route) {
        openPage(route);
        String nameProduct = index_accessories_page_logic.getNameFirstProductInTop6Block();
        index_accessories_page_logic.clickOnFirstBtnAddToBasketInTop6Block()
                .clickOnBtnGoToBasket();
        String titleProduct = new Cart_page_Logic().getNameTitleProduct();
        Assert.assertEquals(nameProduct, titleProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
