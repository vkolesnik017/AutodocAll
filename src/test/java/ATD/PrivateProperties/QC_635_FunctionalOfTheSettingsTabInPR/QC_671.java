package ATD.PrivateProperties.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.Main_page_Logic;
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

public class QC_671 {

    private String mail = "QC_671_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the email change with empty field Confirm email")
    public void testFieldConfirmEmailAreEmpty(String route) {
        String newEmail = mailRandomMailinator("671");
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldNewEmail(newEmail)
                .clickSaveEmailBtn()
                .checkErrorTextInsidePopUp("Das Feld E-Mail-Adresse ist erforderlich und muss einen Wert enthalten.")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}