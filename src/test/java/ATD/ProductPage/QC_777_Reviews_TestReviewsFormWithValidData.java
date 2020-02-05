package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
import ATD.Reviews_Confirmation_page;
import AWS.Login_aws;
import AWS.Reviews_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QC_777_Reviews_TestReviewsFormWithValidData {

    private Product_page productPage = new Product_page();
    private Mailinator mailinator = new Mailinator();
    private Reviews_Confirmation_page reviewsConfirmationPage = new Reviews_Confirmation_page();
    private Login_aws loginAws = new Login_aws();
    private Reviews_aws reviewsAws = new Reviews_aws();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reviews form validation")
    public void testReviewsFormWithValidData() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        String randomEmail = mailRandomMailinator();
        String reviewMessage = ("AUTOTEST_REVIEWS" + getRandomNumber());
        productPage.reviewsNameInput().sendKeys("test_atd_reviews");
        productPage.reviewsEmailInput().sendKeys(randomEmail);
        productPage.reviewsMessageInput().sendKeys(reviewMessage);
        productPage.subscribeAcceptCheckbox().click();
        productPage.reviewsSubmitButton().click();
        productPage.faqPopupText().shouldHave(text("Danke für Ihre Beurteilung."));
        mailinator.openEmail(randomEmail)
                .openLetter(2)
                .linkFAQemailConfirm()
                .click();
        switchTo().window(1);
        reviewsConfirmationPage.checkReviewsConfirmationMessage();
        loginAws.loginInAwsWithOpen();
        open("https://aws.autodoc.de/reviews");
        reviewsAws.searchTextOnPage(randomEmail).shouldBe(visible);
        reviewsAws.searchTextOnPage(reviewMessage).shouldBe(visible);
        close();
    }
}
