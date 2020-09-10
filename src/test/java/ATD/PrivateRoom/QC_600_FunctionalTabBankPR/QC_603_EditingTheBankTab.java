package ATD.PrivateRoom.QC_600_FunctionalTabBankPR;

import ATD.Main_page_Logic;
import ATD.Profile_bank_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_603_EditingTheBankTab {

    private String mail = "QC_603_autotest@mailinator.com";
    private ArrayList bankData, newBankData;
    private Profile_bank_page_Logic profile_bank_page_logic = new Profile_bank_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks edit the bank tab")
    public void testEditBillingAddress(String route) {
        openPage(route);
        bankData = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileBankPage()
                .clickBtnAddNewBankInfo()
                .checkPresenceBankInfoForm()
                .clickChangeCancelBtn()
                .checkAbsenceBankInfoForm()
                .getNameReceiverAndIbanNum();
        newBankData = profile_bank_page_logic.clickBtnAddNewBankInfo()
                .fillingFieldReceiver("test1")
                .fillingFieldIBAN("DE27 1002 0890 0026 8672 15")
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate()
                .getNameReceiverAndIbanNum();
        Assert.assertNotEquals(bankData, newBankData);
        profile_bank_page_logic.clickBtnAddNewBankInfo()
                .fillingFieldReceiver("autotest")
                .fillingFieldIBAN("RO18 BACX 0000 0015 3328 2000")
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}