package ATD.PrivateRoom.QC_600_FunctionalTabBankPR;

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

public class QC_606_DeletingBankDataInPR {

    private String mail = "QC_606_autotest@mailinator.com";

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
    @Description(value = "Test checks the filling of the form in the Bank tab")
    public void testFillingOutFormInBankTab(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToProfileBankPage()
                .clickBtnAddNewBankInfo()
                .fillingFieldReceiver("Autotest")
                .fillingFieldIBAN("RO18 BACX 0000 0015 3328 2000")
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate()
                .checkPresenceCurrentBankBlock()
                .clickDeleteBankDataBtn()
                .clickSaveFormBtn()
                .checkPresenceAndClosePopUpUpdate()
                .checkForMossingText();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}