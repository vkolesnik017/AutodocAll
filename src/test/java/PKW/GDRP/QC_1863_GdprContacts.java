package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Contact_static_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1863_GdprContacts {

    private Contact_static_page_Logic contactStaticPageLogic = new Contact_static_page_Logic();
    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "staticContact");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR contacts form on Order tab ")
    public void testGdprOrderTab(String route) {
        openPage(route);
        mail = contactStaticPageLogic.checkingDatenschutzerklarungLinkBehavior(contactStaticPageLogic.orderDatenschutzerklarungLinkInContacts())
                .fillRequiredFieldsOrderAndCheckBehavior("qc_1863_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyForMail(this.mail);
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR contacts form on NoOrder tab")
    public void testGdprNoOrderTab(String route) {
        openPage(route);
        mail = contactStaticPageLogic.switchToOrderTab()
                .checkingDatenschutzerklarungLinkBehavior(contactStaticPageLogic.noOrderDatenschutzerklarungLinkInContacts())
                .fillRequiredFieldsNoOrderAndCheckBehavior("qc_1863_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
