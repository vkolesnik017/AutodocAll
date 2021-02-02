package ATD.Basket.QC_2915_OptionalFieldFiscalCodeForRomaniaItalyPortugal;

import ATD.CartAccount_page_Logic;
import ATD.CartAddress_page_Logic;
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

public class QC_2975 {


    private String mail = "qc_2975_autotest@mailinator.com";
    private CartAddress_page_Logic cartAddressPageLogic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "IT,RO,PT", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "The \"Fiscal code\" field is filled in, if you re-check the selection box in the \"Fiscal code\" checkbox (the field was previously filled in)")
    public void testSaveFiscalCodeOnReselection(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        new CartAccount_page_Logic().signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillInPostalCode("default")
                .checkPresenceFieldFiscalCodeForShipping(false)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .clickCheckboxForOpenFiscalCodeField()
                .fillingFieldFiscalCode("TS111")
                .checkPresenceTextInFieldsForShippingOrBilling(cartAddressPageLogic.fieldFiscalCode(), true)
                .clickCheckboxForClosedFiscalCodeField()
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .clickCheckboxForOpenFiscalCodeField()
                .checkPresenceTextInFieldsForShippingOrBilling(cartAddressPageLogic.fieldFiscalCode(), true)
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxFirmAndOpenField(cartAddressPageLogic.checkboxFirmShipping(), cartAddressPageLogic.fieldFirm())
                .checkPresenceCheckboxFiscalCode(cartAddressPageLogic.fiscalCodeBlockInSippingForm(), false)
                .clickCheckboxFirmAndCloseField(cartAddressPageLogic.checkboxFirmShipping(), cartAddressPageLogic.fieldFirm())
                .clickCheckboxForOpenFiscalCodeField()
                .checkPresenceTextInFieldsForShippingOrBilling(cartAddressPageLogic.fieldFiscalCode(), true);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}



