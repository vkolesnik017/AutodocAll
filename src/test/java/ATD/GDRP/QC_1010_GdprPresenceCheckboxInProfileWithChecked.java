package ATD.GDRP;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;

public class QC_1010_GdprPresenceCheckboxInProfileWithChecked {

    private String mail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "alex_qa")
    @Description(value = "Test verify presence GDPR checkbox in profile with checked checkbox")
    public void testGdprPresenceCheckboxInProfileWithChecked(String route){
        openPage(route);
        mail = "qc_1011_" + mailRandom();
        new Main_page_Logic().openRegistrationPopup()
                .fillRequiredFieldsForRegistration(firstNameRandom(), secondNameRandom(), mail, true).fillPasswordFieldsAndClickRegistration()
                .checkingAutodocPlusActive().clickSetting().checkingCheckedCheckbox().logOut()
                .loginFromHeader(mail)
                .checkingAutodocPlusActive().clickSetting().checkingCheckedCheckbox().clickCheckboxInSetting().checkingPopupAfterClickCheckbox().checkingUncheckedCheckbox();
    }
}
