package ATD.PrivateProperties.QC_619_FunctionalTabMyOrderInPR;

import ATD.Payment_handler_page_Logic;
import ATD.Product_page_Logic;
import ATD.Profile_orders_page;
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

public class QC_1293 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the clickability of the photo and the name of the product in the order card")
    public void testCheckCkickabilityOfPhotoAndNameOfProduct() throws SQLException {
        Profile_orders_page profile_orders_page = new Profile_orders_page();
        String mail = "QC_1293_autotest@mailinator.com";
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
        new Payment_handler_page_Logic().profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .clickDetailsOrderBtn()
                .checkPresenceElement(profile_orders_page.orderDetailsBlock())
                .checkIdInOrderDetails(orderID)
                .checkRedirectByClickingOnProduct(profile_orders_page.orderDetailsImg())
                .checkRedirectByClickingOnProduct(profile_orders_page.orderDetailsProductName());
        new Order_aws(orderID).openOrderInAwsWithLogin()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
