package ATD.General_Common.QC_2679_NewsletterSubscriptionBlock;

import ATD.Search_page_Logic;
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

public class QC_2680 {
    String email = "qcc_2680" + mailinatorMailRandom();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search23");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks subscribe to news letter by registering in basket")
    public void testChecksSubscribeToNewsLetterByRegisteringInBasket(String route) {
        openPage(route);
        new Search_page_Logic()
                .addFirstProductAndGoToCart()
                .nextButtonClick()
                .checkAndClickTextBlockInRegForm()
                .registrationFromCart(email)
                .fillAllFieldsForShipping("DE")
                .nextBtnClick();
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
