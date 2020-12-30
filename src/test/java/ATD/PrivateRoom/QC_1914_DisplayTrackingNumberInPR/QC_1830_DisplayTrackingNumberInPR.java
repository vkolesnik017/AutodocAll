package ATD.PrivateRoom.QC_1914_DisplayTrackingNumberInPR;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
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

public class QC_1830_DisplayTrackingNumberInPR {


    private String mail = "QC_1830_autotest@mailinator.com", orderNumber;
    private Main_page_Logic main_page_logic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the display of the tracking number in PR")
    public void testDisplayTrackingNumberInPR(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginAndTransitionToProfilePlusPage(mail);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        order_aws.openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .selectDeliveryAndEnterTrackingNum("0", "DHL", "0", "1111111111")
                .selectDeliveryAndEnterTrackingNum("1", "DHL", "1", "2222222222")
                .selectDeliveryAndEnterTrackingNum("2", "DHL", "2", "3333333333")
                .selectDeliveryAndEnterTrackingNum("3", "DHL", "3", "4444444444")
                .selectDeliveryAndEnterTrackingNum("4", "DHL", "4", "5555555555")
                .selectStatusOrder("Versendet")
                .saveOrder()
                .checkCurrentStatusInOrder("Versendet");
        openPage(route);
        main_page_logic.profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .checkPresenceDeliveryStatusBlock()
                .checkNumberDeliveryServiceAdded(5)
                .checkTransitionToDeliveryPage();
        order_aws.openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Versendet")
                .clickBtnAddedDeliveryInOrderBtn()
                .selectDeliveryAndEnterTrackingNum("6", "DHL", "6", "6666666666")
                .selectStatusOrder("Versendet")
                .saveOrder()
                .checkCurrentStatusInOrder("Versendet");
        openPage(route);
        main_page_logic.profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .checkPresenceDeliveryStatusBlock()
                .checkNumberDeliveryServiceAddedFromTooltip(5)
                .trackingNumInDeliveryPageFromTooltip();
        order_aws.openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Versendet")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}