package ATD.GDRP;

import ATD.Product_page_Logic;
import ATD.SetUp;
import AWS.FAQ_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_985_GdprFaqFormNameShorting {

    private String mail;
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private FAQ_aws faq_aws = new FAQ_aws();

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
    @Description(value = "Test verify working of name shorting functionality in FAQ form")
    public void testFaqFormNameShorting(String route) {
        openPage(route);
        mail = product_page_logic.scrollToFaqForm().fillingFieldsAndCheckBehaviorFaqForm("qc985_");
        System.out.println(mail);
        faq_aws.openAndLoginFaqAwsPage().searchQuestionAndPublished(mail);
        openPage(route);
        product_page_logic.checkingCorrectShortingNameFaqForm();
        faq_aws.openFaqAwsPage().searchQuestionAndUnPublished(mail);
        openPage(route);
        product_page_logic.checkAnswerQuestionsDoesntAppearFaqForm();
    }

    @AfterMethod
    void checkingUnpublished() {
        close();
        faq_aws.openAndLoginFaqAwsPage().searchQuestionAndUnPublished(mail);
    }
}


