package ATD.GDRP;

import ATD.Partnership_static_page_Logic;
import Common.SetUp;
import AWS.PrivacyPolicySubscription_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_519 {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "staticPartnership");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working of send ship form on Partnership page")
    public void testFormOnPartnershipPage(String route) {
        openPage(route);
        mail = new Partnership_static_page_Logic().scrollToSendShipForm().checkingDatenschutzerklarungLinkBehavior().fillingFieldsAndCheckBehaviorSendShipForm();
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}


