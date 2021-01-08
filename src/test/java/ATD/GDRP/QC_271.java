package ATD.GDRP;

import ATD.Main_page_Logic;
import Common.SetUp;
import AWS.PrivacyPolicySubscription_aws;
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

public class QC_271 {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR form in footer on Main Page")
    public void testGdprFooterForm(String route) {
        openPage(route);
        mail = new Main_page_Logic()
                .scrollToFooterSubscribeBlock()
                .checkingDatenschutzerklarungLinkBehaviorInReviewsForm()
                .checkingErrorPopupUnclickCheckbox("qc_271_")
                .checkingSuccessPopupClickCheckbox("qc_271_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}


