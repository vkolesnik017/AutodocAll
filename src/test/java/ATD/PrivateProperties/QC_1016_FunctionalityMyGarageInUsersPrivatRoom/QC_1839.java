package ATD.PrivateProperties.QC_1016_FunctionalityMyGarageInUsersPrivatRoom;

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

public class QC_1839 {
    String email = "qc_1839myvehicles@mailinator.com";

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
    @Description(value = "Test checks added truck to my vehicles block")
    public void testChecksAddedTruckToMyVehiclesBlock(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .clickTrucksTab()
                .selectTruckInSelector("2242", "8959", "1012748")
                .presenceAddedAuto()
                .checkElementsOfAddedAuto("ASKAM (FARGO/DESOTO) AS 950 (09.2004 - ...)",new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_maker_car_list6"))
                .comparisonOfAddedVehiclesFromMyGarageAndHeader()
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
