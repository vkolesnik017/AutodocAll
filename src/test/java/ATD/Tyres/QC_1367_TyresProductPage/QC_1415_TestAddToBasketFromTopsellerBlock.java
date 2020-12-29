package ATD.Tyres.QC_1367_TyresProductPage;


import ATD.Product_page_Logic;
import ATD.Tyre_item_page_Logic;
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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1415_TestAddToBasketFromTopsellerBlock {

    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Add To Basket From Topseller Block")
    public void testAddToBasketFromTopsellerBlock(String route) {
        openPage(route);
        new Tyre_item_page_Logic().checkAddToBasketFromTopsellerBlock();
        productPageLogic.checksPresentProductInCartPopup()
                .cartClick()
                .productPrice().shouldBe(visible);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
