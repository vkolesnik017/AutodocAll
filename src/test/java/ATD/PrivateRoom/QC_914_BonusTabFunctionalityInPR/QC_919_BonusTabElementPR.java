package ATD.PrivateRoom.QC_914_BonusTabFunctionalityInPR;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_919_BonusTabElementPR {

    private String mail = "QC_914_bonusTestATD@mailinator.com";

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
    @Description(value = "Test checks for elements of the Bonus tab")
    public void testBonusTabElementPR(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToBonusSystemPage()
                .checkForTextInBlockTopTitle("Mein AUTODOC")
                .checkPresenceClientID()
                .checkPresenceHeaderBlockAndElementInside()
                .checkPresenceBonusLabel()
                .checkPresenceBonusesCombustionDate()
                .checkPresenceBonusTable()
                .checkPresenceBonusProgramBlock();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}