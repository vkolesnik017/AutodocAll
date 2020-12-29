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

public class QC_1011_GdprPresenceCheckboxInProfileWithoutChecked {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify presence GDPR checkbox in profile without checked checkbox")
    public void testGdprPresenceCheckboxInProfileWithoutChecked(String route) {
        openPage(route);
        mail = "qc_1011_" + mailRandom();
        new Main_page_Logic().openRegistrationPopup()
                .fillRequiredFieldsForRegistration(firstNameRandom(), secondNameRandom(), mail, false).fillPasswordFieldsAndClickRegistration()
                .checkingAutodocPlusActive().clickSetting().checkingUncheckedCheckbox().logOut()
                .loginFromHeader(mail)
                .checkingAutodocPlusActive().clickSetting().checkingUncheckedCheckbox().clickCheckboxInSetting().checkingPopupAfterClickCheckbox().checkingCheckedCheckbox();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
