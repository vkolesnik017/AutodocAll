package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.Reviews_Confirmation_page;
import ATD.Reviews_Confirmation_page_Logic;
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

    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private Mailinator mailinator = new Mailinator();
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
        product_page_logic.reviewsNameInput().sendKeys("test_atd_reviews");
        product_page_logic.reviewsEmailInput().sendKeys(randomEmail);
        product_page_logic.reviewsMessageInput().sendKeys(reviewMessage);
        product_page_logic.subscribeAcceptCheckbox().click();
        product_page_logic.reviewsSubmitButton().click();
        product_page_logic.faqPopupText().shouldHave(text("Danke für Ihre Beurteilung."));
        mailinator.openEmail(randomEmail)
                .openLetter(2)
                .linkFAQemailConfirm()
                .click();
        switchTo().window(1);
        new Reviews_Confirmation_page_Logic().checkReviewsConfirmationMessage();
        loginAws.loginInAwsWithOpen();
        open("https://aws.autodoc.de/reviews");
        reviewsAws.searchTextOnPage(randomEmail).shouldBe(visible);
        reviewsAws.searchTextOnPage(reviewMessage).shouldBe(visible);
        close();
    }
}
