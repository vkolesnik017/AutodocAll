package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import PKW.Main_page_Logic;
import PKW.Profile_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1230_GdprRegistrationOnMain {

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
    @Description(value = "Test verify working GDPR on main page in form of registration")
    public void testGdprRegistrationOnMain(String route) {
        openPage(route);
        mail = new Main_page_Logic()
                .openRegistrationFormInHeader()
                .checkingDatenschutzerklarungLinkBehaviorRegistrationForm()
                .fillingRegistrationFields("qc_1230_");
        new Main_page_Logic()
                .checkPresencePopupSuccessAuthorization()
                .clickBtnToPRFromPopupAuthorizationSuccessfully();
        new Profile_page_Logic().checkingPresenceIdUser();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
