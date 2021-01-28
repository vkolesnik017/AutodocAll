package ATD.Basket.QC_2915_OptionalFieldFiscalCodeForRomaniaItalyPortugal;

import ATD.CartAccount_page_Logic;
import ATD.CartAddress_page_Logic;
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

public class QC_2973 {

    private String mail = "qc_2973_autotest@mailinator.com";
    private CartAddress_page_Logic cartAddressPageLogic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "IT,RO", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "If the \"Fiscal code\" field was hidden, the previously entered value is not displayed in the AWS order")
    public void testIfFieldWasHiddenPreviouslyEnteredValueIsNotDisplayedInAWS(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        String orderNumber = new CartAccount_page_Logic().signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillInPostalCode("default")
                .checkPresenceFieldFiscalCodeForShipping(false)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .clickCheckboxForOpenFiscalCodeField()
                .fillingFieldFiscalCode("TS111")
                .checkPresenceTextInFieldsForShippingOrBilling(cartAddressPageLogic.fieldFiscalCode(), true)
                .clickCheckboxForClosedFiscalCodeField()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkPresenceTextInFiscalCodeField(false)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }



    @DataProvider(name = "routePT", parallel = true)
    Object[] dataProviderPT() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "PT", "main", "product32");
    }

    @Test(dataProvider = "routePT")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "If the \"Fiscal code\" field was hidden, the previously entered value is not displayed in the AWS order")
    public void testPT_IfFieldWasHiddenPreviouslyEnteredValueIsNotDisplayedInAWS(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        String orderNumber = new CartAccount_page_Logic().signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillInPostalCode("default")
                .checkPresenceFieldFiscalCodeForShipping(false)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .clickCheckboxForOpenFiscalCodeField()
                .fillingFieldFiscalCode("TS111")
                .checkPresenceTextInFieldsForShippingOrBilling(cartAddressPageLogic.fieldFiscalCode(), true)
                .clickCheckboxForClosedFiscalCodeField()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Multibanco")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkPresenceTextInFiscalCodeField(false)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
