package ATD.PrivateRoom.QC_635_FunctionalOfTheSettingsTabInPR;

import ATD.DataBase;
import ATD.Main_page_Logic;
import ATD.Profile_plus_page_Logic;
import ATD.SetUp;
import AWS.Customer_search_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_679_NewsLetterConsentCheckbox {

    private String mail = "QC_679_autotest@mailinator.com";

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
    @Description(value = "Test checks news letter consent checkbox")
    public void testNewsLetterConsentCheckbox(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToSettingPage()
                .checkPresenceChangePassBlock()
                .checkPresenceChangeEmailBlock()
                .clickSubscribeCheckbox()
                .checkTextInsidePopUpSubscribe("Den Newsletter wurde abonniert.")
                .closePopUp()
                .checkThatSubscribeCheckboxIsSelected();
        new Customer_search_aws().openSearchInAwsWithLogin()
                .enterMailAndClickSearch(mail)
                .transitionOnCustomerViewPage()
                .checkStatusOfLastLog();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod","DE", "main","profile_plus"));
        new Profile_plus_page_Logic().goToSettingPage()
                .clickSubscribeCheckbox()
                .checkTextInsidePopUpSubscribe("Den Newsletter wurde abgemeldet.")
                .closePopUp();
        checkingContainsUrl("profile/settings");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}