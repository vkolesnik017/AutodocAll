package ATD.Basket.QC_1882_Login_Registration_PasswordRecovery;

import ATD.CartAccount_page_Logic;
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
import static mailinator.WebMail.passwordForMail;

public class QC_1884_RegistrationNegativeCase_PasswordRecoveryCheck {

    private String mail = "QC_1884_autotest@autodoc.si";
    private WebMail webMail = new WebMail();

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
    @Description(value = "Test checks the registration with an existing e-mail and password recovery check.")
    public void testRegistrationNegativeCasePasswordRecoveryCheck(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .registrationFromCart(mail);
        new CartAccount_page_Logic().recoveryPassFromPopUpEmailAlreadyExists(mail);
        webMail.openMail(mail, passwordForMail).subjectLetter(1).shouldHave(text("neues Passwort"));
        webMail.openLetter(1)
                .clickLinkRecoveryPasswordInLetter();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}