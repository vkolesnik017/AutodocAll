package ATD.GDRP;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.PrivacyPolicySubscription_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_535_FaqFormOnProductPage {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working of FAQ form on product page")
    public void testVerificationFAQForm(String route) {
        openPage(route);
        mail = new Product_page_Logic().scrollToFaqForm().checkingDatenschutzerklarungLinkBehaviorFaqForm().fillingFieldsAndCheckBehaviorFaqForm("qc535_");
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }
}


