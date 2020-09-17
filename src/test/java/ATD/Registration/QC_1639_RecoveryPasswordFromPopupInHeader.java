package ATD.Registration;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_1639_RecoveryPasswordFromPopupInHeader {

    WebMail webMail = new WebMail();
    Main_page_Logic main_page_logic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Checking recovery password from popup in header")
    public void testPasswordRecoveryFromHeader(String route) {
        String mail = "QC_1639_autotest@autodoc.si";
        String newPassword = getRandomNumber();
        openPage(route);
        main_page_logic.passwordRecoveryRequest(mail);
        webMail.openMail(mail, passwordForMail).checkPresenceUnderFirstLetter()
                .letter(1).shouldHave(text("neues Passwort"));
        webMail.openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        main_page_logic.loginWithOldPassword(mail).loginWithNewPassword(newPassword);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
