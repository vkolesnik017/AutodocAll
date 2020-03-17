package ATD.GDRP;

import ATD.Main_page_Logic;
import ATD.SetUp;
import AWS.PrivacyPolicySubscription_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_220_GdprRegistrationOnMain {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR on main page in form of registration")
    public void testGdprRegistrationOnMain(String route) {
        openPage(route);
        mail = "QC_220_" + mailRandom();
        new Main_page_Logic().openRegistrationPopup()
                .checkingDatenschutzerklarungLinkBehaviorRegistrationForm()
                .fillRequiredFieldsForRegistration(firstNameRandom(), secondNameRandom(), mail, false)
                .fillPasswordFieldsAndClickRegistration()
                .checkingAppearingNameOfClient();
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }
    @AfterMethod
    private void teatDown() {
        close();
    }
}


