package ATD.GDRP;

import ATD.Main_page_Logic;
import ATD.Main_page_mob_Logic;
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

public class QC_1012 {

    private String mail, firstName, secondName;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false) //TODO Change of logic. Changes to the task SALES-2345
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify working GDPR checkbox in profile desktop and profile mobile")
    public void testGdprRegistrationWithSubscribeCheckbox(String route) {
        openPage(route);
        mail = "qc_1012_" + mailRandom();
        firstName = firstNameRandom();
        secondName = secondNameRandom();
        new Main_page_Logic().openRegistrationPopup()
                .fillRequiredFieldsForRegistration(firstName, secondName, mail, true)
                .fillPasswordFieldsAndClickRegistration()
                .checkingAutodocPlusActive().clickSetting().checkingCheckedCheckbox();
        openPage("https://m.autodoc.de/?force=mobile");//TODO url add in database
        new Main_page_mob_Logic().closeFirstPopupAfterTransitionOnMob("apps.apple.com", "https://m.autodoc.de/?force=mobile").clickSignInInMenu()
                .closePopupAfterTransitionOnLoginPageMob("apps.apple.com", "https://m.autodoc.de/login")
                .closeFooterPopup().signIn(mail)
                .goToProfilePage().clickAddresseBtn().clickBillingAddress()
                .checkingCheckedCheckbox().clickCheckbox()
                .fillingFieldsInBillingAddress().fillingFieldsInShippingAddress().fillingNameVornameField()
                .clickSubmit().checkingSuccessPopup().checkingUnCheckedCheckbox();
        openPage(route);
        new Main_page_Logic().profileBtnClickInHeader()
                .clickSetting().checkingUncheckedCheckbox();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
