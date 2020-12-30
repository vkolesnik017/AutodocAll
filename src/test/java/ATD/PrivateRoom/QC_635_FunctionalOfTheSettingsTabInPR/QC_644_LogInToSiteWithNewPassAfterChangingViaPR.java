package ATD.PrivateRoom.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.Main_page_Logic;
import ATD.Profile_plus_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_644_LogInToSiteWithNewPassAfterChangingViaPR {

    private String mail = "QC_644_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check enter in to the site with a new password after changing via PR")
    public void testLogInToSiteWithNewPassAfterChangingViaPR(String route) {
        String newPass = passRandom();
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldOldPass(password)
                .fillFieldNewPass(newPass)
                .fillFieldConfirmPass(newPass)
                .clickSvePassBtn()
                .closePopUp()
                .logOutClick()
                .loginFromHeader(mail, newPass);
        new Profile_plus_page_Logic().goToSettingPage()
                .fillFieldOldPass(newPass)
                .fillFieldNewPass(password)
                .fillFieldConfirmPass(password)
                .clickSvePassBtn()
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}