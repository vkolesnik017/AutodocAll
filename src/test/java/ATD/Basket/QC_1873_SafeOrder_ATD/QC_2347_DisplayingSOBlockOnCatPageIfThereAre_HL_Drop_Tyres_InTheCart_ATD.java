package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Cart_page_Logic;
import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2347_DisplayingSOBlockOnCatPageIfThereAre_HL_Drop_Tyres_InTheCart_ATD {


    private String mail = "QC_2347_autotest@mailinator.com";
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Cart_page_Logic cart_page_logic = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroutes("prod", "DE,FR", "main", "HeavyLoadProduct4,productDrop1,tyre_item16");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the purchase of a heavy load")
    public void testOfHeavyLoadsPurchase(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkAbsenceSafeOrderBlock();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", shop,"main","product32"));
        String idProduct =  product_page_logic.getProductId();
        product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .clickSafeOrderCheckbox()
                .checkPresenceSOInSummeryBlock();
        cart_page_logic.checkTotalPriceIncludedSO()
                .deleteDefinitelyGoodsFromCartPage(idProduct)
                .checkAbsenceSafeOrderBlock()
                .checkAbsenceSOInSummeryBlock();
        float productPrice = cart_page_logic.getProductPrice();
        float totalOrderPrice = cart_page_logic.getTotalOrderPrice();
        Assert.assertEquals(productPrice, totalOrderPrice);
        String orderNumber = cart_page_logic.nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345", "autotest","autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, "FR", "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOff()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}