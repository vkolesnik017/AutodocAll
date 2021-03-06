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
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2971 {


    private String mail = "qc_2971_autotest@mailinator.com";
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
    @Description(value = "Checking Go to the next step if the \"Fiscal code\" field is filled / empty, Billing and Shipping are not separated")
    public void testCheckingGoToNextStepIfFiscalCodeFieldIsFilledOrEmpty(String route) {
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
                .checkPresenceFieldFiscalCodeForShipping(true)
                .fillingFieldFiscalCode("TS111")
                .nextBtnClick();
        checkingContainsUrl("payments");
        new CartPayments_page_Logic().clickBtnReturnTheAddressPage()
                .checkPresenceShippingForm()
                .fieldFiscalCode().clear();
        cartAddressPageLogic.checkPresenceFieldFiscalCodeForShipping(true)
                .nextBtnClick();
        checkingContainsUrl("payments");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
