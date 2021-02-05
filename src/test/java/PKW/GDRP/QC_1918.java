package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Versand_static_page_Logic;
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

public class QC_1918 {

    private String mail;
    private Versand_static_page_Logic versandStaticPageLogic = new Versand_static_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "static_versand");
    }

    @Test(dataProvider = "route", enabled = false)  //TODO Change of logic. Changes to the task SALES-2345 and Bug SALES-3203
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR form in footer on Versand static page ")
    public void testGdprFooterFormVersandPage(String route) {
        openPage(route);
        mail = versandStaticPageLogic
                .scrollToFooterSubscribeBlock()
                .checkingDatenschutzerklarungLinkBehavior(versandStaticPageLogic.linkDatenschutzerklarungInFooter())
                .checkingGdprSuccessWithClickCheckboxInFooter("qc_1918_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
