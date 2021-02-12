package ATD.General_Common.QC_2679_NewsletterSubscriptionBlock;

import ATD.Main_page_Logic;
import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2681 {
    String email = "QC_2681" + mailRandom();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks subscribe to new letter in registration form pop-up in header")
    public void testChecksSubscribeToNewLetterInRegFormPopUp(String route) {
        openPage(route);

        new Main_page_Logic().openRegistrationPopup()
                .checkTextBlockInRegForm()
                .fillRequiredFieldsForRegistration(firstNameRandom(), secondNameRandom(), email, true)
                .fillPasswordFieldsAndClickRegistration()
                .checkingAppearingNameOfClient();
        new WebMail().openMail(email)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(email);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
