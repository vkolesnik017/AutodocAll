package ATD.PrivateProperties.QC_1914_DisplayTrackingNumberInPR;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
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
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.switchTo;
import static mailinator.WebMail.passwordForMail;

public class QC_1832 {

    private String mail = "QC_1832_autotest@autodoc.si", firstOrderNumber, secondOrderNumber;
    private ArrayList<String> listSavedTrackingNumberFromAWS, listTrackingNumberFromUrl, listTrackingNumberFromMail;

    private Main_page_Logic main_page_logic = new Main_page_Logic();
    private WebMail webMail = new WebMail();
    private DataBase dataBase = new DataBase("ATD");

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
        main_page_logic.loginAndTransitionToProfilePlusPage(mail)
                .goToMyOrdersPage()
                .checkOrderHistoryAndClearIt("Testbestellungen");
        openPage(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick();
        firstOrderNumber = new Payment_handler_page_Logic().getOrderNumber();
        listSavedTrackingNumberFromAWS = new Order_aws(firstOrderNumber).openOrderInAwsAndLogsInIfUserIntoLogged()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .selectDeliveryAndEnterTrackingNum("0", "GLS", "0", "1111111111")
                .selectDeliveryAndEnterTrackingNum("1", "TNT", "1", "2222222222")
                .selectDeliveryAndEnterTrackingNum("2", "NOX", "2", "3333333333")
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

        listTrackingNumberFromMail = webMail.openMail(mail, passwordForMail)
                .checkAndOpenLetterInfoText("Auftrags- / Versandbestätigung, Bestellnummer", firstOrderNumber)
                .transitionToDeliveryPageAndGetTrackingNumFromUrlInMail();
        switchTo().window(0);
        webMail.exitOfMail();
        Assert.assertEquals(listSavedTrackingNumberFromAWS, listTrackingNumberFromMail);

        new Order_aws(firstOrderNumber).openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Versendet")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        openPage(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "profile_plus"));
        new Profile_plus_page_Logic().goToMyOrdersPage()
                .checkOrderHistoryAndClearIt("Testbestellungen");
        openPage(dataBase.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick();
        secondOrderNumber = new Payment_handler_page_Logic().getOrderNumber();
        listSavedTrackingNumberFromAWS = new Order_aws(secondOrderNumber).openOrderInAwsAndLogsInIfUserIntoLogged()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .selectDeliveryAndEnterTrackingNum("0", "POSTNORD", "0", "4444444444")
                .selectDeliveryAndEnterTrackingNum("1", "DPDPL", "1", "5555555555")
                .selectDeliveryAndEnterTrackingNum("2", "TNT", "2", "6666666666")
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

        listTrackingNumberFromMail = webMail.openMail(mail, passwordForMail)
                .checkAndOpenLetterInfoText("Auftrags- / Versandbestätigung, Bestellnummer", secondOrderNumber)
                .transitionToDeliveryPageAndGetTrackingNumFromUrlInMail();
        Assert.assertEquals(listSavedTrackingNumberFromAWS, listTrackingNumberFromMail);

        new Order_aws(secondOrderNumber).openOrderInAwsWithoutLogin()
                .checkCurrentStatusInOrder("Versendet")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}