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

public class QC_1134_CheckBasketSessionSynchronizationOnDifferentSkins {

    private String mail = "QC_1134_autotestDE@mailinator.com", productIdOnProductPage;
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
    @Description("Test checks basket session synchronization on different skins")
    public void testBasketSessionSynchronizationOnDifferentSkins(String route) throws SQLException {
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
        openPage("https://www.pkwteile.de/");
        new PKW.Main_page_Logic().loginFromHeader(mail, password)
                .cartClick()
                .checkEmptyCart();
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks basket session synchronization on different skins. login via Facebook")
    public void testBasketSessionSynchronizationOnDifferentSkinsLoginViaFB(String route) throws SQLException {
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
        openPage("https://www.pkwteile.de/");
        new PKW.Main_page_Logic().loginFromHeader(mailFB, passFB)
                .cartClick()
                .checkEmptyCart();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}