package ATD.PrivateRoom.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.Main_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_645_LogInToSiteWithAnOldPassAfterChangingViaPR {

    private String mail = "QC_645_autotest@mailinator.com";
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
    @Description(value = "Test check enter in to the site with a new password after changing via PR")
    public void testLogInToSiteWithNewPassAfterChangingViaPR(String route) {
        String newPass = passRandom();
        openPage(route);
        main_page_logic.loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldOldPass(password)
                .fillFieldNewPass(newPass)
                .fillFieldConfirmPass(newPass)
                .clickSvePassBtn()
                .closePopUp()
                .logOutClick()
                .loginFromHeader(mail, password);
        main_page_logic.closePopUpInvalidData()
                .loginWithNewPassword(newPass)
                .goToSettingPage()
                .fillFieldOldPass(newPass)
                .fillFieldNewPass(password)
                .fillFieldConfirmPass(password)
                .clickSvePassBtn()
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}