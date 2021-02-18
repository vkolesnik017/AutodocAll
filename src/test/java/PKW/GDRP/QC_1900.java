package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1900 {

    private String mail;
    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working of reviews form on product page")
    public void testGdprWorkingReviewFormProductPage(String route) {
        openPage(route);
        mail = productPageLogic.scrollToReviewsBlock()
                .checkingDatenschutzerklarungLinkBehavior(productPageLogic.reviewFormDatenschutzerklarungLink())
                .fillRequiredFieldsReviewFormAndCheckBehavior("qc_1900_");
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }


    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working of rating form on product page")
    public void testGdprWorkingRatingFormProductPage(String route) {
        openPage(route);
        mail = productPageLogic.scrollToReviewsBlock()
                .clickBtnOpenPopupForReview()
                .checkingDatenschutzerklarungLinkBehavior(productPageLogic.ratingFormDatenschutzerklarungLink())
                .fillRequiredFieldsRatingFormAndCheckBehavior("qc_1900_");
        new WebMail().openMail(mail)
                .checkLetterInfoText(1, "just now", "Noch ein weiterer Schritt und Sie haben unseren Newsletter abonniert.")
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
