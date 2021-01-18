package ATD.PrivateProperties.QC_1862_BonusesInOrderAndBonusTabInPR;

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

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_915 {

    private String mail = "QC_914_bonusTestATD@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the display of the bonus tab. Currency is equal to shop")
    public void testBonusTabDisplayCurrencyIsEqualToShop(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToBonusSystemPage()
                .checkCurrencyOnHeaderBonusLabel(shop)
                .checkPresenceBonusLabel()
                .checkPresenceBonusesCombustionDate()
                .checkPresenceBonusTable()
                .checkPresenceBonusProgramBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}