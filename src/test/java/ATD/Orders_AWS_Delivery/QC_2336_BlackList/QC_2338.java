package ATD.Orders_AWS_Delivery.QC_2336_BlackList;

import ATD.Product_page_Logic;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2338 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking if the order is in the Black List status with address data from BL in shipping but not in billing")
    public void testCheckingBlacklistStatusWhenAddressDataMatches() throws SQLException {
        String mail = "testbluser@mailinator.com";
        String pass = "maersk";
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        String orderID = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, pass)
                .fillAllFieldsForShipping("autotest", "autotest", "bluser", "autotest",
                        "DE", "bluser", "100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderID).openOrderInAwsAndCheckBlackListLabel()
                .checkCurrentStatusInOrder("Blacklist")
                .checkIconIdInBlackList()
                .reSaveOrder()
                .checkAndClosePopUpBlackList()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}