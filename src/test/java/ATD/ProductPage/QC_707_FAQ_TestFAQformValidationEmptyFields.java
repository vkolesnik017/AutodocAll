package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_707_FAQ_TestFAQformValidationEmptyFields {
    private Product_page productPage = new Product_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks faq form validation with empty fields")
    public void testInputValidationFAQ() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        productPage.faqSubmitButton().click();
        productPage.faqPopup().shouldBe(visible);
        productPage.faqPopupText().shouldHave(text("Fehler"));
        productPage.faqPopupClose().click();
        productPage.validationEmailInputFAQ().shouldBe(visible);
        productPage.validationNameInputFAQ().shouldBe(visible);
        productPage.validationMessageInputFAQ().shouldBe(visible);
        productPage.faqNameInput().sendKeys("test_atd_faq");
        productPage.faqSubmitButton().click();
        productPage.faqPopupText().shouldHave(text("Fehler"));
        productPage.faqPopupClose().click();
        productPage.validationEmailInputFAQ().shouldBe(visible);
        productPage.validationNameInputFAQ().shouldNotBe(visible);
        productPage.validationMessageInputFAQ().shouldBe(visible);
        productPage.faqNameInput().clear();
        productPage.faqEmailInput().sendKeys("test@mailinator.com");
        productPage.faqSubmitButton().click();
        productPage.faqPopupText().shouldHave(text("Fehler"));
        productPage.faqPopupClose().click();
        productPage.validationEmailInputFAQ().shouldNotBe(visible);
        productPage.validationNameInputFAQ().shouldBe(visible);
        productPage.validationMessageInputFAQ().shouldBe(visible);
        productPage.faqEmailInput().clear();
        productPage.faqMessageInput().sendKeys("TEST_MESSAGE");
        productPage.faqSubmitButton().click();
        productPage.faqPopupText().shouldHave(text("Fehler"));
        productPage.faqPopupClose().click();
        productPage.validationEmailInputFAQ().shouldBe(visible);
        productPage.validationNameInputFAQ().shouldBe(visible);
        productPage.validationMessageInputFAQ().shouldNotBe(visible);
        close();
    }
}
