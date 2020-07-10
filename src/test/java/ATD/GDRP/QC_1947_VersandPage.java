package ATD.GDRP;

import ATD.SetUp;
import ATD.Versand_static_page_Logic;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1947_VersandPage {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "staticVersand");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test verify working of send ship form on Versand page")
    public void testFormOnVersandPage(String route) {
        openPage(route);
        mail =  new Versand_static_page_Logic().clickAllCountriesButton().checkingDatenschutzerklarungLinkBehavior().fillingFieldsAndCheckBehaviorSendShipForm("Japan");
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
