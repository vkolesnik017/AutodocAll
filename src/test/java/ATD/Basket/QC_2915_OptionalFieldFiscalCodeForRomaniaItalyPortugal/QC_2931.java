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
import static ATD.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2931 {

    private String mail = "qc_2931_autotest@mailinator.com";
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
    @Description(value = "Error display when filling in the \"Fiscal code\" field with Cyrillic letters")
    public void testErrorDisplayFillingFiscalCodeFieldCyrillicLetters(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        new CartAccount_page_Logic().signIn(mail, password)
                .clickCheckboxForOpenBilling()
                .fillAllFields(shop)
                .fillAllFieldsForBilling(shop)
                .checkPresenceFieldFiscalCodeForShipping(false)
                .checkPresenceFieldFiscalCodeForBilling(false)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInShippingForm(), shop)
                .checkTextForCheckboxFiscalCode(cartAddressPageLogic.textFiscalCodeInBillingForm(), shop)
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeFieldForBilling()
                .fillingFieldFiscalCode("тест")
                .fillingFieldFiscalCodeBilling("тест")
                .nextBtnClick();
        cartAddressPageLogic.checkPresenceErrorTooltipFiscalCodeFieldForShipping()
                .checkPresenceErrorTooltipFiscalCodeFieldForBilling();
        checkingContainsUrl("address");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
