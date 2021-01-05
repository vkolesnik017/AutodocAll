package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import Common.DataBase;
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

public class QC_1840 {

    String email = "qc_1840myvehicles@mailinator.com";

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
    @Description(value = "Test checks added motorcycle to my vehicles block")
    public void testChecksAddedMotoToMyVehiclesBlock(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .selectMotoBlockInSelector()
                 .selectVehicleInSelector("4081","12111","104173")
                 .presenceAddedAuto()
                .checkElementsOfAddedAuto("BMW MOTORCYCLES K (05.1982 - ...)", new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog6"))
                .comparisonOfAddedVehiclesFromMyGarageAndHeader()
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
