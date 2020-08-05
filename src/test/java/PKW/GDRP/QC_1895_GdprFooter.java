package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import PKW.Main_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1895_GdprFooter {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR form in footer on Main Page ")
    public void testGdprFooterForm(String route) {
        openPage(route);
        mail = new Main_page_Logic()
                .scrollToFooterSubscribeBlock()
                .checkingDatenschutzerklarungLinkBehaviorInReviewsForm()
                .checkingErrorPopupUnClickCheckbox("qc_1895_")
                .checkingSuccessPopupClickCheckbox("qc_1895_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
