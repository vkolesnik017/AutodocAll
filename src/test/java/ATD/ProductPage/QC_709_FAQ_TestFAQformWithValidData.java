package ATD.ProductPage;


import ATD.DataBase;
import ATD.FAQ_Confirmation_page;
import ATD.FAQ_Confirmation_page_Logic;
import ATD.Product_page_Logic;
import AWS.FAQ_aws;
import AWS.Login_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.CommonMethods.getRandomNumber;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class QC_709_FAQ_TestFAQformWithValidData {
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private FAQ_Confirmation_page_Logic faqConfirmationPage = new FAQ_Confirmation_page_Logic();
    private FAQ_aws faqAws = new FAQ_aws();
    private Mailinator mailinator = new Mailinator();
    private Login_aws loginAws = new Login_aws();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks faq form with valid data")
    public void testFAQformWithValidData() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        String randomEmail = mailRandomMailinator();
        String faqMessage = ("AUTOTEST_FAQ" + getRandomNumber());
        String faqResponse = ("FAQ_AUTOTEST_RESPONSE" + getRandomNumber());
        product_page_logic.faqNameInput().sendKeys("test_atd_faq");
        product_page_logic.faqEmailInput().sendKeys(randomEmail);
        product_page_logic.faqMessageInput().sendKeys(faqMessage);
        product_page_logic.faqSubmitButton().click();
        product_page_logic.faqPopupText().shouldHave(text("FAQ"));
        mailinator.openEmail(randomEmail)
                .openLetter(1)
                .linkFAQemailConfirm()
                .click();
        switchTo().window(1);
        faqConfirmationPage.checkFaqConfirmationMessage();
        loginAws.loginInAwsWithOpen();
        open("https://aws.autodoc.de/faq");
        faqAws.searchTextOnPage(randomEmail).shouldBe(visible);
        faqAws.searchTextOnPage(faqMessage).click();
        faqAws.faqResponseInput().sendKeys(faqResponse);
        faqAws.faqAWSsubmitButton().click();
        faqAws.searchTextOnPage(faqResponse).click();
        mailinator.openEmail(randomEmail)
                .openLetter(1);
        faqAws.searchTextOnPage(faqResponse).shouldBe(visible);
        close();
    }
}
