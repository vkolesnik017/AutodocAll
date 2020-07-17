package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.DataBase;
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

public class QC_1839_AddedTruckToMyVehiclesBlock {
    String email = "qc_1839myvehicles@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "main");
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
                .checkElementsOfAddedAuto("ASKAM (FARGO/DESOTO) AS 950 (09.2004 - ...)",new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_maker_car_list6"))
                .comparisonOfAddedVehiclesFromMyGarageAndHeader()
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
