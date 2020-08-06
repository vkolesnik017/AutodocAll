package ATD.GDRP;

import ATD.Category_car_list_page_Logic;
import ATD.SetUp;
import AWS.PrivacyPolicySubscription_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1008_GdprFormSoft404SelectorSearch {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list19");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify form soft 404 from selector search")
    public void testFormSoft404SelectorSearch(String route) {
        openPage(route);
        mail = "QC_1008_" + mailRandom();
        new Category_car_list_page_Logic().checkingDatenschutzerklarungLinkBehaviorSoftForm().checkingBehaviorSoft404(mail);
        new PrivacyPolicySubscription_aws().openPolicySubscriptionWithLogin().checkingPolicyAndSubscribeForMail(this.mail);
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}


