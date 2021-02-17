package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1895 {

    private String mail;
    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts()  {
        return new SetUp("PKW").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR form in footer on Main Page ")
    public void testGdprFooterForm(String route) {
        openPage(route);
        mail = mainPageLogic
                .scrollToFooterSubscribeBlock()
                .checkingDatenschutzerklarungLinkBehavior(mainPageLogic.linkDatenschutzerklärungInFooter())
                .checkingErrorPopupUnClickCheckbox("qc_1895_")
                .checkingSuccessPopupClickCheckbox("qc_1895_");
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
