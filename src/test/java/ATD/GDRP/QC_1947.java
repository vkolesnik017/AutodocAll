package ATD.GDRP;

import Common.SetUp;
import ATD.Versand_static_page_Logic;
import AWS.PrivacyPolicySubscription_aws;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1947 {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "staticVersand");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test verify working of send ship form on Versand page")
    public void testFormOnVersandPage(String route) {
        openPage(route);
        mail =  new Versand_static_page_Logic()
                .clickAllCountriesButton()
                .checkingDatenschutzerklarungLinkBehavior()
                .fillingFieldsAndCheckBehaviorSendShipForm("Japan");
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
