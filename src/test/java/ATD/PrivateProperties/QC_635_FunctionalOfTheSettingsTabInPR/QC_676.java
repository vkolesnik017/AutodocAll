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

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_676 {

    private String mail = "QC_676_autotest@mailinator.com";

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
    @Description(value = "Test checks the change of mail with the entry of an invalid mail")
    public void testMailWithEntryOfInvalidMail(String route) {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldNewEmail("QC_676_autotestmailinator.com")
                .fillFieldConfirmEmail("QC_676_autotestmailinator.com")
                .clickSaveEmailBtn()
                .checkErrorTextInsidePopUp("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten")
                .closePopUp()
                .fillFieldNewEmail("QC_676_autotest@ mailinator.com")
                .fillFieldConfirmEmail("QC_676_autotes@ tmailinator.com")
                .clickSaveEmailBtn()
                .checkErrorTextInsidePopUp("Das Feld E-mail muss eine gültige E-Mail-Adresse enthalten")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}