package ATD.Basket.QC_1129_BasketSession;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1397_RecoveryBasketWebSessionOnMobVersionMainPageViaFacebook {

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Main_page_mob_Logic main_page_mob_logic = new Main_page_mob_Logic();

    private String productIdOnProductPage;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderMain() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks recovery of basket web session on the mobile version main page via Facebook")
    public void testRecoveryBasketWebSessionOnMobVersionMainPageViaFB(String route) throws SQLException {
        openPage(route);
        main_page_logic.signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient();
        openPage(route + "/" + new DataBase().getRouteByRouteName("DE", "product25"));
        productIdOnProductPage = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage("https://m.autodoc.de/?force=mobile");
        main_page_mob_logic.closeFirstPopupAfterTransitionOnMob("apps.apple.com", "https://m.autodoc.de/?force=mobile")
                .clickSignInInMenu()
                .closeFooterPopup()
                .signInFromFB(mailFB, passFB)
                .checkPresenceIconUserId()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .deleteItemFromCart()
                .checkEmptyCart();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}