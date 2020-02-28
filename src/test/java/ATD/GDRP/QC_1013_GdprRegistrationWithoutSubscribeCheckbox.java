package ATD.GDRP;

import ATD.Main_page_Logic;
import ATD.Main_page_mob_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;

public class QC_1013_GdprRegistrationWithoutSubscribeCheckbox {

    private String mail, firstName, secondName;

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
    @Description(value = "Test verify working GDPR checkbox in profile desktop and profile mobile")
    public void testGdprRegistrationWithoutSubscribeCheckbox(String route) {
        openPage(route);
        mail = "qc_1013_" + mailRandom();
        firstName = firstNameRandom();
        secondName = secondNameRandom();
        new Main_page_Logic().openRegistrationPopup()
                .fillRequiredFieldsForRegistration(firstName, secondName, mail, false)
                .fillPasswordFieldsAndClickRegistration()
                .checkingAutodocPlusActive().clickSetting().checkingUncheckedCheckbox();
        openPage("https://m.autodoc.de/?force=mobile");
        new Main_page_mob_Logic().closeFirstPopup().clickSignInInMenu()
                .closeFooterPopup().signIn(mail)
                .goToProfilePage().clickAddresseBtn().clickBillingAddress()
                .checkingUnCheckedCheckbox().clickCheckbox()
                .fillingFieldsInBillingAddress().fillingFieldsInShippingAddress().fillingNameVornameField()
                .clickSubmit().checkingSuccessPopup().checkingCheckedCheckbox();
        openPage(route);
        new Main_page_Logic().profileBtnClickInHeader()
                .clickSetting().checkingCheckedCheckbox();
    }
}
