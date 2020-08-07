package ATD.ProductPage;


import ATD.DataBase;
import ATD.FAQ_Confirmation_page_Logic;
import ATD.Product_page_Logic;
import AWS.FAQ_aws;
import AWS.Login_aws;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_709_FAQ_TestFAQformWithValidData {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private FAQ_Confirmation_page_Logic faqConfirmationPage = new FAQ_Confirmation_page_Logic();
    private FAQ_aws faqAws = new FAQ_aws();
    private Mailinator mailinator = new Mailinator();
    private Login_aws loginAws = new Login_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks faq form with valid data")
    public void testFAQformWithValidData() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product14"));
        String randomEmail = mailRandomMailinator("709");
        String faqMessage = ("QC_709_AUTOTEST_FAQ" + getRandomNumber());
        String faqResponse = ("QC_709_FAQ_AUTOTEST_RESPONSE" + getRandomNumber());
        product_page_logic.enterFAQValidData(randomEmail, faqMessage);
        mailinator.openEmail(randomEmail)
                .openLetter(1)
                .clickFAQemailConfirm();
        switchTo().window(1);
        faqConfirmationPage.checkFaqConfirmationMessage();
        loginAws.loginInAwsWithOpen();
        open("https://aws.autodoc.de/faq");
        faqAws.checkFAQinAWS(randomEmail, faqMessage, faqResponse);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
