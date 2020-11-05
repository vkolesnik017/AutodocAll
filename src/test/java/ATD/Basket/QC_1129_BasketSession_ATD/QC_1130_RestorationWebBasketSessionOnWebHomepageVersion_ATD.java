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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1130_RestorationWebBasketSessionOnWebHomepageVersion_ATD {

    private String mail = "QC_1130_autotestDE@mailinator.com", productIdOnProductPage;
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Main_page_Logic main_page_logic = new Main_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks restoration web basket session on the web homepage version")
    public void testRestorationWebBasketSessionOnWebHomepageVersion(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginFromHeader(mail)
                .checkingAppearingNameOfClient();
        openPage(route + "/" + new DataBase("ATD").getRouteByRouteName("DE", "product25"));
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
                .deleteGoodFromCartPage();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}