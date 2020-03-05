package ATD.GDRP;

import ATD.Blog_page;
import ATD.Blog_page_Logic;
import ATD.Sales_page;
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

public class QC_515_BlogPage {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "info_section_index");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working of send ship form on Blog page")
    public void testFormOnBlogPage(String route) {
        openPage(route);
        mail = new Blog_page_Logic().checkingDatenschutzerklarungLinkBehavior().fillingFieldsAndCheckBehaviorSendMailForm();
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyAndSubscribeForMail(this.mail);
    }
}


