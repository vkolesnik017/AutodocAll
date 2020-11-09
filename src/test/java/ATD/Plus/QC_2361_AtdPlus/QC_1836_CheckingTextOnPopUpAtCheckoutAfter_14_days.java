package ATD.Plus.QC_2361_AtdPlus;

import ATD.Main_page_Logic;
import ATD.Profile_plus_page;
import ATD.Profile_plus_page_Logic;
import AWS.Customer_view_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1836_CheckingTextOnPopUpAtCheckoutAfter_14_days {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the text of the pop-up at the 14 days checkout")
    public void testCheckingTextOnPopUpAtCheckoutAfter_14_days(String route) {
        openPage(route);
        String andDataOfPremiumAccount = new Main_page_Logic().loginFromHeader("Dopeltest@autodoc.de", "2020100216")
                .goToPageAutodocPlus()
                .getEndDateOfPremiumAccount();
        String customerID = new Profile_plus_page_Logic().clickBtnUnsubscribeButton()
                .checkPresenceExpectedFirstTextInsideUnsubscribePopUp(new Profile_plus_page().firstTextInsideUnsubscribePopUp(),
                        "Wenn Sie Ihr Abonnement kündigen, können Sie bis zum Ende Ihres " +
                                "Abonnementzeitraums alle Vorteile von AUTODOC PLUS nutzen.")
                .checkPresenceExpectedFirstTextInsideUnsubscribePopUp(new Profile_plus_page().secondTextInsideUnsubscribePopUp(),
                        "Ihr Abonnement ist bis zum " + andDataOfPremiumAccount + " gültig.")
                .clickBtnNoToClosePopUpUnsubscribe()
                .checkPresenceUnsubscribeButton()
                .getDigitClientId();
        new Customer_view_aws().openCustomerPersonalArea(customerID)
                .checkPresenceTextInCustomerSubscriptionSelector("B+ (BASIC plus) MONTH");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
