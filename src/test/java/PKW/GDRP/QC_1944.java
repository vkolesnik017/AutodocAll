package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Main_page_Logic;
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

public class QC_1944 {

    private String mail;
    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShop("prod", "EN");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR in popup from Selector on main page")
    public void testGdprInPopupFromSelector(String route) {
        openPage(route);
        mail = mainPageLogic.clickBtnNotFoundCarInSelector()
                .checkingPrivacyPolicyLinkBehavior(mainPageLogic.privacyPolicyLinkPopupFromSelector())
                .fillRequiredFieldsInPopupFromSelector("qc_1944_", "11111");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
