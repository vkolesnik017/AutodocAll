package ATD.QASYS_255_Registration;

import ATD.CartAccount_page;
import ATD.Main_page;
import ATD.Profile_page;
import ATD.SetUp;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.Mailinator;
import org.testng.annotations.*;

import static ATD.CommonMethods.getRandomNumber;
import static ATD.CommonMethods.password;
import static ATD.CommonMethods.ridex_82B0896;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_422_PasswordRecovery {

    private String newPassword;
    private String PasswordRecoveryFromHeader = "PasswordRecoveryFromHeader@mailinator.com";
    private String PasswordRecoveryFromCart = "PasswordRecoveryFromCart@mailinator.com";

    private Main_page main_page = new Main_page();
    private Mailinator mailinator = new Mailinator();
    private Profile_page profile_page = new Profile_page();
    private CartAccount_page cartAccount_page = new CartAccount_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Flaky
    @Owner(value = "Evlentiev")
    @Test(dataProvider = "route")
    public void testPasswordRecoveryFromHeader(String route) {
        newPassword = getRandomNumber();
        open(route);
        main_page.loginBtnInHeader().click();
        main_page.forgotPasswordLink().click();
        main_page.emailFieldInPasswordRecoveryPopUp().setValue(PasswordRecoveryFromHeader);
        main_page.sendBtnInPasswordRecoveryPopUp().click();
        main_page.closePopupMessageSentForChangePassword().click();
        main_page.closePopupMessageSentForChangePassword().shouldBe(not(visible));
        mailinator.openEmail(PasswordRecoveryFromHeader).letter(1).shouldHave(text("moments ago")).shouldHave(text("neues Passwort"));
        mailinator.openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        // trying login with old password
        main_page.logoutButton().click();
        main_page.loginBtnInHeader().click();
        main_page.emailInputInLoginPopup().setValue(PasswordRecoveryFromHeader);
        main_page.passwordInputInLoginPopup().setValue(password);
        main_page.loginBtnInPopUp().click();
        main_page.closePopUpInvalidDataForLogin().click();
        // login with new password
        main_page.passwordInputInLoginPopup().setValue(newPassword);
        main_page.loginBtnInPopUp().click();
        profile_page.nameOfClient().shouldBe(visible);
    }

    @Owner(value = "Evlentiev")
    @Test(dataProvider = "route")
    @Flaky
    public void testPasswordRecoveryFromCart(String route) {
        newPassword = getRandomNumber();
        open(route);
        main_page.useSearch(ridex_82B0896)
                .addFirstProductAndGoToCart()
                .nextButtonClick()
                .forgotPasswordLink().click();
        cartAccount_page.emailFieldInPasswordRecoveryPopUp().setValue(PasswordRecoveryFromCart);
        cartAccount_page.sendBtnInPasswordRecoveryPopUp().click();
        cartAccount_page.closePopupMessageSentForChangePassword().click();
        cartAccount_page.closePopupMessageSentForChangePassword().shouldBe(not(visible));
        mailinator.openEmail(PasswordRecoveryFromCart).letter(1).shouldHave(text("moments ago")).shouldHave(text("neues Passwort"));
        mailinator.openLetter(1)
                .clickLinkRecoveryPasswordInLetter()
                .fillPasswordFieldsAndClickSubmit(newPassword)
                .nameOfClient().shouldBe(visible);
        // trying login with old password
        main_page.logoutButton().click();
        main_page.loginBtnInHeader().click();
        main_page.emailInputInLoginPopup().setValue(PasswordRecoveryFromCart);
        main_page.passwordInputInLoginPopup().setValue(password);
        main_page.loginBtnInPopUp().click();
        main_page.closePopUpInvalidDataForLogin().click();
        // login with new password
        main_page.passwordInputInLoginPopup().setValue(newPassword);
        main_page.loginBtnInPopUp().click();
        profile_page.nameOfClient().shouldBe(visible);
    }

    @AfterMethod
    private void changePasswordToDefault() {
        profile_page.settingsTabBtn().click();
        profile_page.oldPasswordFiled().setValue(newPassword);
        profile_page.newPasswordField().setValue(password);
        profile_page.newPasswordConfirmField().setValue(password);
        profile_page.changePasswordBtn().click();
        profile_page.closeSuccessfulPasswordChangePopup().shouldBe(visible);
    }
}
