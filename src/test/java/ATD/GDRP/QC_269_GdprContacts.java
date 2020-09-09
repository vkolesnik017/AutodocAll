package ATD.GDRP;

import ATD.Contact_static_page_Logic;
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

public class QC_269_GdprContacts {


    private Contact_static_page_Logic contact_static_page_logic = new Contact_static_page_Logic();
    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "staticContact");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR form on noOrder tab")
    public void testGdprNoOrderTab(String route) {
        openPage(route);
        mail = contact_static_page_logic.checkingDatenschutzerklarungLinkBehavior(contact_static_page_logic.noOrderDatenschutzerklarungLink())
                .fillRequiredFieldsNoOrderAndCheckBehavior("qc_269_");
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR form on Order tab")
    public void testGdprOrderTab(String route) {
        openPage(route);
        mail = contact_static_page_logic.switchToOrderTab().checkingDatenschutzerklarungLinkBehavior(contact_static_page_logic.orderDatenschutzerklarungLink())
                .fillRequiredFieldsOrderAndCheckBehavior("qc_269_");
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}


