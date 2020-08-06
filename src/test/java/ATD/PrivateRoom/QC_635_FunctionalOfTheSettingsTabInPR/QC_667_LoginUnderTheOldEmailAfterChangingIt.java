package ATD.PrivateRoom.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.Main_page_Logic;
import ATD.Profile_plus_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_667_LoginUnderTheOldEmailAfterChangingIt {

    private String mail = "QC_667_autotest@mailinator.com";
    private Main_page_Logic main_page_logic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks login with new email")
    public void testLoginWithNewEmail(String route) {
        String newEmail = mailRandomMailinator("667");
        openPage(route);
        main_page_logic.loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldNewEmail(newEmail)
                .fillFieldConfirmEmail(newEmail)
                .clickSaveEmailBtn()
                .closePopUp()
                .logOutClick()
                .loginFromHeader(mail, password);
        main_page_logic.closeInvalidEmailPopUPForLogin()
                .loginWithNewEmail(newEmail);
        main_page_logic.confirmPrivacyPolicyInPopUp();
        new Profile_plus_page_Logic().goToSettingPage()
                .fillFieldNewEmail(mail)
                .fillFieldConfirmEmail(mail)
                .clickSaveEmailBtn()
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}