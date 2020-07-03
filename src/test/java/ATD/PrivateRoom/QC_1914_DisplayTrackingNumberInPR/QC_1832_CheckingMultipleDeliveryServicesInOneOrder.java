package ATD.PrivateRoom.QC_1914_DisplayTrackingNumberInPR;

import ATD.*;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1832_CheckingMultipleDeliveryServicesInOneOrder {

    private String mail = "QC_1832_autotest@mailinator.com", orderNumber;
    private ArrayList<String> listSavedTrackingNumberFromAWS, listTrackingNumberFromUrl, listTrackingNumberFromMail;

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Mailinator mailinator = new Mailinator();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the display of the tracking number in PR")
    public void testDisplayTrackingNumberInPR(String route) throws SQLException {
        openPage(route);
        main_page_logic.loginAndTransitionToProfilePlusPage(mail);
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
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
        listSavedTrackingNumberFromAWS = order_aws.openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .selectDeliveryAndEnterTrackingNum("GLS", "0", "0", "1111111111")
                .selectDeliveryAndEnterTrackingNum("DHL", "1", "1", "2222222222")
                .selectDeliveryAndEnterTrackingNum("NOX", "2", "2", "3333333333")
                .selectStatusOrder("Versendet")
                .saveOrder()
                .checkCurrentStatusInOrder("Versendet")
                .getListSavedTrackingNumber();
        openPage(route);
        listTrackingNumberFromUrl = main_page_logic.profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .checkPresenceDeliveryStatusBlock()
                .checkNumberDeliveryServiceAdded(3)
                .transitionToDeliveryPageAndGetTrackingNumFromURL();
        Assert.assertEquals(listSavedTrackingNumberFromAWS, listTrackingNumberFromUrl);
        listTrackingNumberFromMail = mailinator.openEmail(mail)
                .openLetter(1)
                .transitionToDeliveryPageAndGetTrackingNumFromUrlInMail();
        Assert.assertEquals(listSavedTrackingNumberFromAWS, listTrackingNumberFromMail);
        order_aws.openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Versendet")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}