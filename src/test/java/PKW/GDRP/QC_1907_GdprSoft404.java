package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import PKW.Car_parts_Logic;
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

public class QC_1907_GdprSoft404 {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "car_parts");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR in soft 404 block")
    public void testGdprSoft404(String route) {
        openPage(route);
        mail = new Car_parts_Logic()
                .checkingPresenceSoft404Block()
                .checkingDatenschutzerklarungLinkBehaviorInSoft404Form()
                .checkingSuccessPopupClickCheckbox("qc_1907_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }


}