package ATD.PrivateProperties.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.CommonMethods;
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
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_677 {

    private String mail = "QC_677_autotest@mailinator.com";

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
    @Description(value = "Test checks the email change with unequal fields the new email and confirm the email")
    public void testNewEmailFieldIsNotEqualToConfirmEmailField(String route) {
        String newEmail = CommonMethods.mailinatorMailRandom("677");
        String confirmEmail = CommonMethods.mailinatorMailRandom("677");
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .fillFieldNewEmail(newEmail)
                .fillFieldConfirmEmail(confirmEmail)
                .clickSaveEmailBtn()
                .checkErrorTextInsidePopUp("muss mit dem Feld „E-Mail-Adresse“ übereinstimmen")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}