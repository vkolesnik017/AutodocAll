package ATD.PrivateRoom;

import ATD.Main_page_Logic;
import ATD.Profile_plus_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_597_CheckingUrlForTabsInPR {

    private String mail = "QC_597_autotestATD@mailinator.com", metaTitle, nameTabDeposit, nameTabSettings, nameTabBank,
            nameTabAddresses, nameTabMyOrder, nameTabMyGarage;
    private Profile_plus_page_Logic profile_plus_page_logic = new Profile_plus_page_Logic();

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
    @Description(value = "Test checks URL for tabs in PR")
    public void testCheckingUrlForTabsInPR(String route) throws SQLException {
        openPage(route);
        nameTabDeposit = new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToDepositPage()
                .getNameTabMyDeposit();
        checkingContainsUrl("/profile/deposit");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabDeposit);
        back();
        nameTabSettings = profile_plus_page_logic.goToSettingPage()
                .getNameTabSettings();
        checkingContainsUrl("/profile/settings");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabSettings);
        back();
        nameTabBank = profile_plus_page_logic.goToProfileBankPage()
                .getNameTabBank();
        checkingContainsUrl("/profile/bank");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabBank);
        back();
        nameTabAddresses = profile_plus_page_logic.goToProfileAddressesPage()
                .getNameTabAddresses();
        checkingContainsUrl("/profile/addresses");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabAddresses);
        back();
        nameTabMyOrder = profile_plus_page_logic.goToMyOrdersPage()
                .getNameTabMyOrder();
        checkingContainsUrl("/profile/orders");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabMyOrder);
        back();
        nameTabMyGarage = profile_plus_page_logic.goToMyVehiclesBlock()
                .getNameTabMyVehicles();
        checkingContainsUrl("/profile/garage");
        metaTitle = title();
        Assert.assertEquals(metaTitle, nameTabMyGarage);
        back();
        profile_plus_page_logic.checkAbsenceBtnWithTransitionToBonusPage();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}