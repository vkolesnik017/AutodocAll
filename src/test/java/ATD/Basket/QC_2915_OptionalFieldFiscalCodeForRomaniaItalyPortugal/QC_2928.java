package ATD.Basket.QC_2915_OptionalFieldFiscalCodeForRomaniaItalyPortugal;


import ATD.CartAccount_page_Logic;
import ATD.CartAddress_page_Logic;
import ATD.CartPayments_page_Logic;
import ATD.Product_page_Logic;
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

public class QC_2928 {

    private String mail = "qc_2928_autotest@mailinator.com";
    private CartAddress_page_Logic cartAddressPageLogic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "IT,PT,RO", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the Fiscal code checkbox / field and the logic of its operation")
    public void testCheckingDisplayFiscalCodCheckboxAndField(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        new CartAccount_page_Logic().signIn(mail, password)
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForShipping(shop)
                .chooseDeliveryCountryForBilling(shop)
                .fillInPostalCode("default")
                .fillInPostalCodeForBilling("default")
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInBillingForm(), shop)
                .clickCheckboxFirmAndOpenField(cartAddressPageLogic.checkboxFirmShipping(), cartAddressPageLogic.fieldFirm())
                .clickCheckboxFirmAndOpenField(cartAddressPageLogic.checkboxFirmBilling(), cartAddressPageLogic.fieldFirmBilling())
                .checkPresenceCheckboxFiscalCode(cartAddressPageLogic.fiscalCodeBlockInSippingForm(), false)
                .checkPresenceCheckboxFiscalCode(cartAddressPageLogic.fiscalCodeBlockInBillingForm(), false)
                .clickCheckboxFirmAndCloseField(cartAddressPageLogic.checkboxFirmShipping(), cartAddressPageLogic.fieldFirm())
                .clickCheckboxFirmAndCloseField(cartAddressPageLogic.checkboxFirmBilling(), cartAddressPageLogic.fieldFirmBilling())
                .checkPresenceCheckboxFiscalCode(cartAddressPageLogic.fiscalCodeBlockInSippingForm(), true)
                .checkPresenceCheckboxFiscalCode(cartAddressPageLogic.fiscalCodeBlockInBillingForm(), true)
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeFieldForBilling()
                .fillingFieldFiscalCode("TS111")
                .fillingFieldFiscalCodeBilling("TS111")
                .nextBtnClick();
        new CartPayments_page_Logic().clickBtnReturnTheAddressPage()
                .checkPresenceShippingForm()
                .fieldFiscalCode().clear();
        cartAddressPageLogic.fieldFiscalCodeBilling().clear();
        cartAddressPageLogic.nextBtnClick();
        checkingContainsUrl("payments");

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
