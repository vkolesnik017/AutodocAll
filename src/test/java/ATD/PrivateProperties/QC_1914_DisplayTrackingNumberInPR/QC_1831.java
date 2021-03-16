package ATD.PrivateProperties.QC_1914_DisplayTrackingNumberInPR;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_1831 {

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private Profile_orders_page_Logic profile_orders_page_logic = new Profile_orders_page_Logic();
    private WebMail webMail = new WebMail();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "deliveryService", parallel = true)
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"GLS"},
                {"NOX"},
                {"POSTNORD"},
                {"DPDPL"},
                {"TNT"}
        };
    }

    @Test(dataProvider = "deliveryService", enabled = false) // TODO отключон из за замечания со стороны команды BSK
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks existing delivery services")
    public void testCheckingForExistingDeliveryServices(String deliveryService) throws SQLException {
        String mail = dataBase.getMail(deliveryService);
        openPage(dataBase.getFullRouteByRouteName("prod", "DE", "main"));
        main_page_logic.loginAndTransitionToProfilePlusPage(mail)
                .goToMyOrdersPage()
                .checkOrderHistoryAndClearIt("Testbestellungen");
        close();
        openPage(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick();
        String orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        String trackingNumFromAWS = order_aws.openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .selectDeliveryAndEnterTrackingNum( "0", deliveryService,"0", "1111111111")
                .selectStatusOrder("Versendet")
                .saveOrder()
                .checkCurrentStatusInOrder("Versendet")
                .getSavedTrackingNumber();
        openPage(dataBase.getFullRouteByRouteName("prod", "DE", "main"));
        String trackingNumFromPR = main_page_logic.profilePlusBtnClickInHeader()
                .goToMyOrdersPage()
                .checkPresenceStatusOrderBlock()
                .getTrackingNum();
        Assert.assertEquals(trackingNumFromAWS, trackingNumFromPR);
        String deliveryPageURL = profile_orders_page_logic.checkNumberDeliveryServiceAdded(1)
                .transitionToDeliveryPageAndGetURL(deliveryService);

        webMail.openMail(mail, passwordForMail)
                .checkAndOpenLetterInfoText("Versandbestätigung, Bestellnummer", orderNumber);
        String deliveryPageUrlFromMail = webMail.transitionToDeliveryPageAndGetUrlFromMail(deliveryService);
        Assert.assertEquals(deliveryPageURL, deliveryPageUrlFromMail);
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