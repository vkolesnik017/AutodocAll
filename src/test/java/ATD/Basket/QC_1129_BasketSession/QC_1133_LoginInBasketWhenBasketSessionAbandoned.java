package ATD.Basket.QC_1129_BasketSession;

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

public class QC_1133_LoginInBasketWhenBasketSessionAbandoned {

    private String mail = "QC_1133_autotestDE@mailinator.com", productIdOnProductPage, productIdOnProductPageForAllData;

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Product_page_Logic product_page_logic = new Product_page_Logic();

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
    @Description("Test checks Login in the basket when the basket session is abandoned")
    public void testLoginInBasketWhenBasketSessionAbandoned(String route) throws SQLException {
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
        openPage(route + "/" + new DataBase().getRouteByRouteName("DE", "product"));
        productIdOnProductPageForAllData = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFields("DE")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkOfAbsenceIdAddedProductInAllData(productIdOnProductPage)
                .checkOfIdAddedProductInAllData(productIdOnProductPageForAllData)
                .deleteGoodFromCartAllDataPage();
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks Login in the basket when the basket session is abandoned. login via Facebook")
    public void testLoginInBasketWhenBasketSessionAbandonedFaceBookLogin(String route) throws SQLException {
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
        openPage(route + "/" + new DataBase().getRouteByRouteName("DE", "product"));
        productIdOnProductPageForAllData = product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signInFromFB(mailFB, passFB)
                .fillAllFields("DE")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .checkOfAbsenceIdAddedProductInAllData(productIdOnProductPage)
                .checkOfIdAddedProductInAllData(productIdOnProductPageForAllData)
                .deleteGoodFromCartAllDataPage();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
