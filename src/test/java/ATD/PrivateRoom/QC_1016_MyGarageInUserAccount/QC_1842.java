package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

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

public class QC_1842 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks My garage pop-Up for unauthorized user without viewed history Auto ")
    public void testChecksMyGaragePopUpForUnAuthorizedUserWithOutViewedHistoryAuto(String route) {
        openPage(route);

        new Main_page_Logic()
                .checkCountOfVehicleInMygarageIcon(0)
                .openMyGaragePopUp()
                .openSelectorFromMyGarageBlock()
                .closeSelectorFromMyGarageBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
