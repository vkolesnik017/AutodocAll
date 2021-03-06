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

public class QC_1945 {

    private String mail;
    private Shop_reviews_page_Logic shopReviewsPageLogic = new Shop_reviews_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "shop_reviews");
    }

    @Test(dataProvider = "route", enabled = false) //TODO  Bug SALES-3323
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test verify working GDPR in popup Answer on review page")
    public void testGdprInPopupAnswerOnReviewPage(String route) {
        openPage(route);
        mail = new Shop_reviews_page_Logic().clickBtnReplyInCommentBlock()
                .checkingDatenschutzerklarungLinkBehavior(shopReviewsPageLogic.popupAnswerLinkDatenschutzerkl√§rung())
                .fillRequiredFieldsAndCheckBehaviorInPopupForReply("qc_1945_");
        new PrivacyPolicySubscription_aws()
                .openPolicySubscriptionWithLogin()
                .checkingPolicyAndSubscribeForMail(this.mail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
