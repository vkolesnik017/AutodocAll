package ATD.GDRP;

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

public class QC_1010 {

    private String mail;

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
    @Description(value = "Test verify presence GDPR checkbox in profile with checked checkbox")
    public void testGdprPresenceCheckboxInProfileWithChecked(String route){
        openPage(route);
        mail = "qc_1010_" + mailinatorMailRandom();
        new Main_page_Logic().openRegistrationPopup()
                .fillRequiredFieldsForRegistration(firstNameRandom(), secondNameRandom(), mail, true).fillPasswordFieldsAndClickRegistration()
                .checkingAutodocPlusActive().clickSetting().checkingCheckedCheckbox().logOut()
                .loginFromHeader(mail)
                .checkingAutodocPlusActive().clickSetting().checkingCheckedCheckbox().clickCheckboxInSetting().checkingPopupAfterClickCheckbox().checkingUncheckedCheckbox();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
