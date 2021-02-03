package ATD.Basket.QC_3027_CheckingVATWithdrawalForCH;

import ATD.Index_instruments_page_Logic;
import ATD.Listing_page_Logic;
import AWS.PageVAT_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
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

public class QC_3033 {

    private String mail = "QC_3033_autotest@autodoc.si";
    private DataBase dB = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "87.0", true);
    }

    @DataProvider(name = "deliveryShop", parallel = false)
    Object[] blockingWords() {
        return new Object[][]{
                {"CH"},
                {"DE"},
                {"LI"}
        };
    }

    @Test(dataProvider = "deliveryShop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check present VAT postscript for CH")
    public void testCheckPresentVATPostscriptForCH(String deliveryShop) throws SQLException {
        String vatForCH = new PageVAT_aws().getVatForCH();
        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "search19"));
        new Listing_page_Logic().checkPresentVatPostscriptInCardProduct(vatForCH)
                .clickFirstProductOnListing()
                .checkPresenceVatPostscript(vatForCH);
        openPage(dB.getFullRouteByRouteAndSubroute("prod", "CH", "main", "index_instruments"));
        String orderID = new Index_instruments_page_Logic().checkPresenceVatPostscriptInMiniCardsInTop6(vatForCH)
                .checkPresenceVatPostscriptInMiniCardsInTop10(vatForCH)
                .clickOnFirstBtnAddToBasketInTop6ProductsBlock()
                .clickOnBtnGoToBasket()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("78266")
                .chooseDeliveryCountryForShipping(deliveryShop)
                .fillFieldTelNumForShipping("200+002")
                .fillFieldFirmNameForShipping("autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded("CH", "CH", "autotest")
                .clickOnTheDesiredPaymentMethod("CH", "Bank")
                .nextBtnClick()
                .checkTextContainingVatPercentage(vatForCH)
                .nextBtnClick()
                .getOrderNumber();
        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderID)
                .checkTextContainingVatPercentageInEmail(vatForCH);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
