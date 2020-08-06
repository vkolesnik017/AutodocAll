package ATD.ACC.QC_1064_BlockTopProductsOnMainChemie;

import ATD.Cart_page_Logic;
import ATD.Index_chemicals_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1067_AddProductWithTopProductsBlockInBasket {

    private String nameProduct, titleProduct;
    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks adding a product to the cart when selecting it from top products block ")
    public void testChecksAddingProductToCartWithTopProductsBlockForChemistry(String route) {
        openPage(route);
        nameProduct = index_chemicals_page_logic.getNameProductInBlockTopProducts();
        index_chemicals_page_logic.clickOnFirstBtnAddToBasketInTopProductsBlock()
                .clickOnBtnGoToBasket();
        titleProduct = new Cart_page_Logic().getNameTitleProduct();
        Assert.assertEquals(nameProduct, titleProduct);
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
