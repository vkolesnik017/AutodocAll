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

public class QC_220_GdprRegistrationOnMain {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR form on main page in form of registration")
    public void testGdprRegistrationOnMain(String route) {
        openPage(route);
        mail = new Product_page_Logic().addProductToCart().closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .checkingDatenschutzerklarungLinkBehaviorRegistrationForm().fillingRegistrationFields("qc_223_");
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyForMail(this.mail);
    }
}


