package ATD.General_Common.QC_1554_Footer;

import ATD.Main_page_Logic;
import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1564 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test successful newsletter subscription")
    public void checkingSuccessfulSubscription(String route) {
        String mail = "qc_1564_" + mailinatorMailRandom();
        openPage(route);
        new Main_page_Logic().checkSuccessfulNewsletterSubscription(mail);
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin()
                .checkingSuccessfulSubscription(mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
