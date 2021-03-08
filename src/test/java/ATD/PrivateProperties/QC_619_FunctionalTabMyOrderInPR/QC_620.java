package ATD.PrivateProperties.QC_619_FunctionalTabMyOrderInPR;

import ATD.Product_page_Logic;
import ATD.Profile_plus_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_620 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the display of the 'Awaiting payment' status in the Personal Account")
    public void testCheckingDisplayOfTheAwaitingPayment() throws SQLException {
        String mail = "QC_620_autotest@mailinator.com";
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        String orderID = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws (orderID);
        order_aws.openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung");
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "profile_plus"));
        new Profile_plus_page_Logic().goToMyOrdersPage()
                .checkPresenceStatusOrderBlock()
                .checkTextInStatusOrder("Zahlung steht noch aus");
        order_aws.openOrderInAwsWithoutLogin()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
