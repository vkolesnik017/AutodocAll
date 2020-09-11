package PKW.GDRP;

import AWS.PrivacyPolicySubscription_aws;
import Common.SetUp;
import PKW.Shop_reviews_page_Logic;
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


public class QC_1906_GdprPageReviews {

    private String mail;
    private Shop_reviews_page_Logic shopReviewsPageLogic = new Shop_reviews_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "shop_reviews");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Gdpr verify working of reviews form on review page")
    public void testGdprWorkingReviewFormReviewPage(String route) {
        openPage(route);
        mail = shopReviewsPageLogic.scrollToReviewBlock()
                .checkingDatenschutzerklarungLinkBehavior(shopReviewsPageLogic.reviewFormDatenschutzerklarungLink())
                .fillRequiredFieldsReviewFormAndCheckBehavior("qc_1906_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Gdpr verify working of rating form on review page")
    public void testGdprInPopupRatingFormOnReviewPage(String route) {
        openPage(route);
        mail = shopReviewsPageLogic.clickBtnRateSiteInRatingBlock()
                .checkingDatenschutzerklarungLinkBehavior(shopReviewsPageLogic.ratingFormDatenschutzerklarungLink())
                .fillRequiredFieldsPopupRatingFormAndCheckBehavior("qc_1906_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
