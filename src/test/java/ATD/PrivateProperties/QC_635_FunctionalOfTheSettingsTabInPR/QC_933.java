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

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_933 {

    private String mail = "QC_933_autotest@mailinator.com";

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
    @Description(value = "Test checks for elements of the Settings tab")
    public void testSettingsTabElementPR(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkForTextInBlockTopTitle("Mein AUTODOC")
                .checkPresenceClientID()
                .checkPresenceHeaderBlockAndElementInside()
                .checkNamePageAndPresenceIcon("Einstellungen")
                .checkPresenceFieldsInChangePassBlock()
                .checkPresenceFieldsInChangeEmailBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
