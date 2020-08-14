package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import PKW.Main_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1944_GdprPopupInSelectorOnShopEN {

    private String mail;
    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShop("prod", "EN");
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
