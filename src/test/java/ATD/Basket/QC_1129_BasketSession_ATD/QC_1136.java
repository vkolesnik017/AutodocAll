package ATD.Basket.QC_1129_BasketSession_ATD;

import Common.DataBase;
import ATD.Main_page_Logic;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1136 {

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Main_page_Logic main_page_logic = new Main_page_Logic();

    private String productIdOnProductPage;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderMain() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks recovery of basket web session on the web version main page via Facebook")
    public void testRecoveryBasketWebSessionOnWebVersionMainPageViaFB(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginFromHeader(mailFB, passFB)
                .checkingAppearingNameOfClient();
        openPage(route + "/" + new DataBase("ATD").getRouteByRouteName("DE", "product25"));
        productIdOnProductPage = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage);
        closeWebDriver();
        openPage(route);
        main_page_logic.signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .deleteGoodFromCartPage();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}