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
import static com.codeborne.selenide.Selenide.*;

public class QC_1132_SynchronizationMobSessionOfBasketOnWebVersion {

    private String mail = "QC_1132_autotestDE@mailinator.com", productIdOnProductPage;

    private Product_page_mob_Logic product_page_Mob_logic = new Product_page_mob_Logic();
    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Cart_page_mob_Logic cart_page_mob_logic = new Cart_page_mob_Logic();
    private Main_page_mob_Logic main_page_mob_logic = new Main_page_mob_Logic();
    private Profile_page_Logic profile_page_logic = new Profile_page_Logic();

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
    @Description("Test checks synchronization of the mob session of the basket on the web version")
    public void testSynchronizationMobSessionOnWebVersion(String route) throws SQLException {
        openPage("https://m.autodoc.de/?force=mobile");
        main_page_mob_logic.closeFirstPopup()
                .clickSignInInMenu()
                .closeFooterPopup()
                .signIn(mail);
        openPage("https://m.autodoc.de/vemo/2295352?force=mobile");
        productIdOnProductPage = product_page_Mob_logic.getProductId();
        product_page_Mob_logic.addProductToCart()
                .cartClick();
        cart_page_mob_logic.checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage(route);
        main_page_logic.loginFromHeader(mail)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .deleteGoodFromCartPage();
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks synchronization of the mob session of the basket on the web version when login through FaceBook")
    public void testSynchronizationMobSessionOnWebVersionLoginFromFB(String route) throws SQLException {
        openPage("https://m.autodoc.de/?force=mobile");
        main_page_mob_logic.closeFirstPopup()
                .clickSignInInMenu()
                .closeFooterPopup()
                .signInFromFB(mailFB, passFB)
                .checkPresenceIconUserId();
        openPage("https://m.autodoc.de/vemo/2295352?force=mobile");
        productIdOnProductPage = product_page_Mob_logic.getProductId();
        product_page_Mob_logic.addProductToCart()
                .cartClick();
        cart_page_mob_logic.checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage(route);
        main_page_logic.signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .deleteGoodFromCartPage();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}