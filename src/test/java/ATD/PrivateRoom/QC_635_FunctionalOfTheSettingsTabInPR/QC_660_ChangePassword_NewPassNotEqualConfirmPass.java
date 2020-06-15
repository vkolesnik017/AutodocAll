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

public class QC_660_ChangePassword_NewPassNotEqualConfirmPass {

    private String mail = "QC_660_autotest@mailinator.com";

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
    @Description(value = "Test checks the password change with an empty field Old password")
    public void testPassChangeWithAnEmptyFieldOldPass(String route) {
        String newPass = passRandom();
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldOldPass(password)
                .fillFieldNewPass(newPass)
                .fillFieldConfirmPass("qweQwe123")
                .clickSvePassBtn()
                .checkTitleInsidePopUp("Fehler")
                .checkErrorTextInsidePopUp("muss mit dem Feld „Neues Kennwort“ übereinstimmen")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}