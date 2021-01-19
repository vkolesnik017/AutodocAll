package ATD.PrivateProperties.QC_600_FunctionalTabBankPR;

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

public class QC_608 {

    private String mail = "QC_608_autotest@mailinator.com";

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
    @Description(value = "Test checks the saving of the bank data form with an incorrect IBAN")
    public void testSavingBankDataFormWithIncorrectIBAN(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileBankPage()
                .clickBtnAddNewBankInfo()
                .fillingFieldIBAN("RO18 BACX 0000 0015 3328 2005")
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}