package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.CartAccount_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1928_LoginNegativeCase_EmptyInputs {

    private String mail = "QC_1928_autotestATD@mailinator.com";
    private CartAccount_page_Logic cartAccount_page_logic = new CartAccount_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the login with empty inputs.")
    public void testLoginNegativeCase_EmptyInputs(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick();
        cartAccount_page_logic.loginButton().click();
        cartAccount_page_logic.checkTextFromErrorPopUpWhenLogin("Das Feld Passwort ist erforderlich und muss einen Wert enthalten.");
        cartAccount_page_logic.checkTextFromErrorPopUpWhenLogin("Bitte geben Sie eine gültige E-mail Adresse an");
        cartAccount_page_logic.closeErrorPopUpBtn().click();
        cartAccount_page_logic.emailFieldInLoginForm().setValue(mail);
        cartAccount_page_logic.loginButton().click();
        cartAccount_page_logic.checkTextFromErrorPopUpWhenLogin("Das Feld Passwort ist erforderlich und muss einen Wert enthalten.");
        cartAccount_page_logic.closeErrorPopUpBtn().click();
        cartAccount_page_logic.emailFieldInLoginForm().clear();
        cartAccount_page_logic.passwordFieldInLoginForm().setValue(password);
        cartAccount_page_logic.loginButton().click();
        cartAccount_page_logic.checkTextFromErrorPopUpWhenLogin("Bitte geben Sie eine gültige E-mail Adresse an");
        cartAccount_page_logic.closeErrorPopUpBtn().click();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}