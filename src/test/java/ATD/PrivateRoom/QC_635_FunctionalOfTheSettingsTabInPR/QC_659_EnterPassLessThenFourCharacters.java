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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_659_EnterPassLessThenFourCharacters {

    private String mail = "QC_659_autotest@mailinator.com";

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
    @Description(value = "Test checks password entry is less than four characters")
    public void testChecksPassEntryIsLessThanFourCharacters(String route) {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldOldPass(password)
                .fillFieldNewPass("1")
                .fillFieldConfirmPass("1")
                .clickSvePassBtn()
                .checkTitleInsidePopUp("Fehler")
                .checkErrorTextInsidePopUp("muss mindestens 4 Zeichen beinhalten")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
