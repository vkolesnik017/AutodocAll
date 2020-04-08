package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.Reviews_Confirmation_page_Logic;
import AWS.Login_aws;
import AWS.Reviews_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.*;

public class QC_777_Reviews_TestReviewsFormWithValidData {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reviews form validation")
    public void testReviewsFormWithValidData() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        closeCookiesFooterMessage();
        String randomEmail = mailRandomMailinator();
        String reviewMessage = ("AUTOTEST_REVIEWS" + getRandomNumber());
        new Product_page_Logic().enterValidReviewData(randomEmail, reviewMessage);
        new Mailinator().openEmail(randomEmail)
                .openLetter(1)
                .linkFAQemailConfirm().shouldBe(appear).click();
        switchTo().window(1);
        new Reviews_Confirmation_page_Logic().checkReviewsConfirmationMessage();
        new Login_aws().loginInAwsWithOpen();
        open("https://aws.autodoc.de/reviews");
        new Reviews_aws().checkReviewInAWS(randomEmail, reviewMessage);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
