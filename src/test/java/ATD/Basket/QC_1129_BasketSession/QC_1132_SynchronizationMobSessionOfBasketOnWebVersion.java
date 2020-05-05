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

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1132_SynchronizationMobSessionOfBasketOnWebVersion {

    private String mail = "QC_1132_autotestDE@mailinator.com", productIdOnProductPage;
    private String mailFB = "garanchenko.oleg@gmail.com", passFB = "garanchenko1992";

    private Product_page_mob_Logic product_page_Mob_logic = new Product_page_mob_Logic();

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
        new Main_page_mob_Logic().closeFirstPopup()
                .clickSignInInMenu()
                .closeFooterPopup()
                .signIn(mail);
        openPage("https://m.autodoc.de/vemo/2295352?force=mobile");
        productIdOnProductPage = product_page_Mob_logic.getProductId();
        product_page_Mob_logic.addProductToCart()
                .cartClick();
        new Cart_page_mob_Logic().checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage(route);
        new Main_page_Logic().loginFromHeader(mail)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage);
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks synchronization of the mob session of the basket on the web version when login through FaceBook")
    public void testSynchronizationMobSessionOnWebVersionLoginFromFB(String route) throws SQLException {
        openPage("https://m.autodoc.de/?force=mobile");
        new Main_page_mob_Logic().closeFirstPopup()
                .clickSignInInMenu()
                .closeFooterPopup()
                .signInFromFB(mailFB, passFB);
        openPage("https://m.autodoc.de/vemo/2295352?force=mobile");
        productIdOnProductPage = product_page_Mob_logic.getProductId();
        product_page_Mob_logic.addProductToCart()
                .cartClick();
        new Cart_page_mob_Logic().checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage(route);
        new Main_page_Logic().signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage);
    }


    @AfterMethod
    private void tearDown() {
        close();
    }
}