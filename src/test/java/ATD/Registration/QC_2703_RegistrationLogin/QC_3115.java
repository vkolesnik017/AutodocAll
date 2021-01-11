package ATD.Registration.QC_2703_RegistrationLogin;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.mailRandom;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3115 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Kolesnik")
    @Flaky
    @Description(value = "test check error login pop-up")
    public void testCheckErrorLoginPopUp(String route) {
        String mail = mailRandom();
        openPage(route);
        new Main_page_Logic().
                clickOnRegistrationForm()
                .clickOnSubmitOfRegistrationForm()
                .displayPopUpAboutRequiredLoginField("Dies ist ein Pflichtfeld.\nBitte geben Sie eine gültige E-mail Adresse an")
                .closePopUpAboutRequiredLoginField()
                .clickOnSubmitOfRegistrationForm()
                .displayPopUpAboutRequiredLoginField("Dies ist ein Pflichtfeld.\nBitte geben Sie eine gültige E-mail Adresse an")
                .forcedClickOnLogoInHeader()
                .setMailInRegistrationForm("QC_3115" + mail)
                .clickOnSubmitOfRegistrationForm()
                .displayPopUpAboutRequiredLoginField("Dies ist ein Pflichtfeld.");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
