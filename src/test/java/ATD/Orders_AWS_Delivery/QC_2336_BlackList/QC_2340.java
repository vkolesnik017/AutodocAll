package ATD.Orders_AWS_Delivery.QC_2336_BlackList;

import ATD.Product_page_Logic;
import AWS.Order_aws;
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

public class QC_2340 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test order entry into status 65 (Blacklist) when PHONE parameter matches")
    public void testCheckingBlacklistStatusWhenPhoneMatches(String route) throws SQLException {
        String mail = "qc_2340_autotest@mailinator.com";
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String orderID = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("011110000-1111")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderID).openOrderInAwsAndCheckBlackListLabel()
                .checkCurrentStatusInOrder("Blacklist")
                .checkPhoneNumberForExtraCharacters("-")
                .reSaveOrder()
                .checkAndClosePopUpBlackList()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
