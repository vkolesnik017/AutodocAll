package ATD.Basket.QC_1129_BasketSession;

import ATD.DataBase;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import ATD.SetUp;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1135_SynchronizationOfBasketSessionAfterPlacingAnOrder {

    private String mail = "QC_1135_autotestDE@mailinator.com", productIdOnProductPage;
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Main_page_Logic main_page_logic = new Main_page_Logic();

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
    @Description("Test checks Synchronization of the basket session after placing an order")
    public void testSynchronizationOfBasketSessionAfterPlacingAnOrder(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginFromHeader(mail)
                .checkingAppearingNameOfClient();
        openPage(route + "/" + new DataBase().getRouteByRouteName("DE", "product25"));
        productIdOnProductPage = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage);
        close();
        openPage(route);
        main_page_logic.loginFromHeader(mail)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .clickBtnNextAndTransitionOnAddressPage()
                .fillAllFields("DE")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick()
                .checkSuccessTextInHeader();
        close();
        openPage(route);
        main_page_logic.loginFromHeader(mail)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkEmptyCart();
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks Synchronization of the basket session after placing an order. login via Facebook")
    public void testSynchronizationOfBasketSessionAfterPlacingAnOrderLoginViaFB(String route) throws SQLException {
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
        openPage(route);
        main_page_logic.signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkOfIdAddedProductInBasket(productIdOnProductPage)
                .clickBtnNextAndTransitionOnAddressPage()
                .fillAllFields("DE")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick()
                .checkSuccessTextInHeader();
        close();
        openPage(route);
        main_page_logic.signInFromFB(mailFB, passFB)
                .checkingAppearingNameOfClient()
                .cartClick()
                .checkEmptyCart();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}