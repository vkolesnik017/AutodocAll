package ATD.ProductPage;


import ATD.DataBase;
import ATD.Product_page_Logic;
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
    private Product_page_Logic product_page_logic = new Product_page_Logic();
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
        product_page_logic.faqSubmitButton().click();
        product_page_logic.faqPopup().shouldBe(visible);
        product_page_logic.faqPopupText().shouldHave(text("Fehler"));
        product_page_logic.faqPopupClose().click();
        product_page_logic.validationEmailInputFAQ().shouldBe(visible);
        product_page_logic.validationNameInputFAQ().shouldBe(visible);
        product_page_logic.validationMessageInputFAQ().shouldBe(visible);
        product_page_logic.faqNameInput().sendKeys("test_atd_faq");
        product_page_logic.faqSubmitButton().click();
        product_page_logic.faqPopupText().shouldHave(text("Fehler"));
        product_page_logic.faqPopupClose().click();
        product_page_logic.validationEmailInputFAQ().shouldBe(visible);
        product_page_logic.validationNameInputFAQ().shouldNotBe(visible);
        product_page_logic.validationMessageInputFAQ().shouldBe(visible);
        product_page_logic.faqNameInput().clear();
        product_page_logic.faqEmailInput().sendKeys("test@mailinator.com");
        product_page_logic.faqSubmitButton().click();
        product_page_logic.faqPopupText().shouldHave(text("Fehler"));
        product_page_logic.faqPopupClose().click();
        product_page_logic.validationEmailInputFAQ().shouldNotBe(visible);
        product_page_logic.validationNameInputFAQ().shouldBe(visible);
        product_page_logic.validationMessageInputFAQ().shouldBe(visible);
        product_page_logic.faqEmailInput().clear();
        product_page_logic.faqMessageInput().sendKeys("TEST_MESSAGE");
        product_page_logic.faqSubmitButton().click();
        product_page_logic.faqPopupText().shouldHave(text("Fehler"));
        product_page_logic.faqPopupClose().click();
        product_page_logic.validationEmailInputFAQ().shouldBe(visible);
        product_page_logic.validationNameInputFAQ().shouldBe(visible);
        product_page_logic.validationMessageInputFAQ().shouldNotBe(visible);
        close();
    }
}
