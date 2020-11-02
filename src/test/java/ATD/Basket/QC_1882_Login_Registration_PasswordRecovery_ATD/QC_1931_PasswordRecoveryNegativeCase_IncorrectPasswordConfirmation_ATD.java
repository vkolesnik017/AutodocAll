package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery_ATD;

import ATD.PasswordRecovery_page;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1931_PasswordRecoveryNegativeCase_IncorrectPasswordConfirmation_ATD {

    private String mail = "QC_1931_autotestATD@mailinator.com";
    private WebMail webMail = new WebMail();
    private PasswordRecovery_page passwordRecovery_page = new PasswordRecovery_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks password recovery by incorrect password confirmation.")
    public void testPasswordRecoveryNegativeCase_IncorrectPasswordConfirmation(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .passwordRecoveryRequestFromCart(mail);
        webMail.openMail(mail).letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("neues Passwort"));
        webMail.openLetterInOldMailServiceMailinator(1)
                .clickLinkRecoveryPasswordInLetter()
                .checkTextInPopupErrorWhenFieldsNotFilled("Das Feld Passwort ist erforderlich und muss einen Wert enthalten.")
                .checkTextInPopupErrorWhenEnteringDifferentPasswords("1111", "2222",
                        "Das Feld „Kennwort wiederholen\" muss mit dem Feld „Passwort“ übereinstimmen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
