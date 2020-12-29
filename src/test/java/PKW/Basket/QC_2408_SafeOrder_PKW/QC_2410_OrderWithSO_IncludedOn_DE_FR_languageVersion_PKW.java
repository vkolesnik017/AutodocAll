package PKW.Basket.QC_2408_SafeOrder_PKW;

import AWS.Order_aws;
import Common.SetUp;
import PKW.CartAllData_page_Logic;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2410_OrderWithSO_IncludedOn_DE_FR_languageVersion_PKW {

    private String mail = "QC_2410_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,FR", "main", "product4");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks order with SO included on FR / DE language versions.")
    public void testOrderWithSO_IncludedOn_DE_FR_languageVersion(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        float totalPrice = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryAndFillingFirmInput( shop, "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .addSafeOrderInOrderAndCheckTotalPriceIncludedSO(shop)
                .checkThatSafeOrderCheckboxIsSelected()
                .getTotalPriceAllDataPage(shop);
        String orderNumber = new CartAllData_page_Logic().nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        float totalPriceInAWS = order_aws.openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceInAWS);
        totalPriceInAWS = order_aws.reSaveOrder()
                .checkThatStatusSafeOrderIsOn()
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceInAWS);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
