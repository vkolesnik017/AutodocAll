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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_744_Reviews_TestReviewsFormValidation {
    private Product_page productPage = new Product_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reviews form validation")
    public void testInputValidationReviews() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "product14"));
        closeCookiesFooterMessage();
        productPage.reviewsSubmitButton().click();
        productPage.faqPopup().shouldBe(visible);
        productPage.faqPopupClose().click();
        productPage.validationEmailInputReviews().shouldBe(visible);
        productPage.validationNameInputReviews().shouldBe(visible);
        productPage.validationMessageInputReviews().shouldBe(visible);
        productPage.validationSubscribeCheckbox().shouldBe(visible);
        close();
    }
}
