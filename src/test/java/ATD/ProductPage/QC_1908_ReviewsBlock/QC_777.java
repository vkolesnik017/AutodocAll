package ATD.ProductPage.QC_1908_ReviewsBlock;


import ATD.CommonMethods;
import Common.DataBase;
import ATD.Product_page_Logic;
import ATD.Reviews_Confirmation_page_Logic;
import AWS.Login_aws;
import AWS.Reviews_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_777 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reviews form validation")
    public void testReviewsFormWithValidData() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        closeCookiesFooterMessage();
        String randomEmail = CommonMethods.mailinatorMailRandom("777");
        String reviewMessage = ("QC_777_AUTOTEST_REVIEWS" + getRandomNumber());
        new Product_page_Logic().enterValidReviewData(randomEmail, reviewMessage);
        new WebMail().openMail(randomEmail)
                .openLetterInOldMailServiceMailinator(1)
                .clickBtnConfirmSubscriptions();
        new Login_aws().loginInAwsWithOpen();
        open("https://aws.autodoc.de/reviews");
        new Reviews_aws().checkReviewInAWS(randomEmail, reviewMessage);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
