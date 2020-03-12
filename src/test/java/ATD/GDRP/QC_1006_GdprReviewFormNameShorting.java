package ATD.GDRP;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.Reviews_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1006_GdprReviewFormNameShorting {

    private String mail;
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Reviews_aws reviews_aws = new Reviews_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product18");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working of name shorting functionality in Review form")
    public void testReviewFormNameShorting(String route) {
        openPage(route);
        mail = product_page_logic.checkingReviewsForm().fillingFieldsAndCheckBehaviorReviewsForm("qc1006_");
        System.out.println(mail);
        reviews_aws.openAndLoginReviewsAwsPage().searchQuestionAndPublished(mail);
        openPage(route);
        product_page_logic.checkingCorrectShortingNameReviewsForm();
        reviews_aws.openReviewsAwsPage().searchQuestionAndUnPublished(mail);
        openPage(route);
        product_page_logic.checkAnswerQuestionsDoesntAppearReviewsForm();
    }

    @AfterMethod
    void checkingUnpublished() {
        close();
        if (mail != null) reviews_aws.openAndLoginReviewsAwsPage().searchQuestionAndUnPublished(mail);
    }
}


//autotest_qc1006@test.com