package ATD.Registration;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;


public class QC_1642_RecoveryPasswordFromCart {

    Mailinator mailinator = new Mailinator();
    Main_page_Logic main_page_logic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Checking recovery password from cart")
    public void testPasswordRecoveryFromCart(String route) {
        String mail = "PasswordRecoveryFromCart@mailinator.com";
        String newPassword = getRandomNumber();
        openPage(route);
        main_page_logic.useSearch(ridex_82B0896)
                .addFirstProductAndGoToCart()
                .nextButtonClick()
                .passwordRecoveryRequestFromCart(mail);
        mailinator.openEmail(mail).letterInfo(1).shouldHave(text("moments ago")).shouldHave(text("neues Passwort"));
        mailinator.openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        main_page_logic.loginWithOldPassword(mail)
                .loginWithNewPassword(newPassword);
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
