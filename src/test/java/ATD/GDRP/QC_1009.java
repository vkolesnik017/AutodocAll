package ATD.GDRP;

import ATD.Main_page_Logic;
import Common.SetUp;
import AWS.PrivacyPolicySubscription_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1009 {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false)  //TODO Change of logic. Changes to the task SALES-2345
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify form soft 404 from Search Bar in header")
    public void testFormSoft404HeaderSearch(String route) {
        openPage(route);
        mail = "QC_1009_" + mailRandom();
        new Main_page_Logic().useSearch("Запчасть")
                .checkingDatenschutzerklarungLinkBehaviorSoftForm().checkingBehaviorSoft404(mail);
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}


