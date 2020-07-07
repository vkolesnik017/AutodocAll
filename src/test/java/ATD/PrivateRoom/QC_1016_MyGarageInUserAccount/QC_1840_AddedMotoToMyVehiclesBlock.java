package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

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

public class QC_1840_AddedMotoToMyVehiclesBlock {

    String email = "qc_1840myvehicles@mailinator.com";

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
    @Description(value = "Test checks added motorcycle to my vehicles block")
    public void testChecksAddedMotoToMyVehiclesBlock(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .selectMotoBlockInSelector()
                 .selectVehicleInSelector("4081","12111","104173")
                 .presenceAddedAuto()
                .checkElementsOfAddedAuto("BMW MOTORCYCLES K (05.1982 - ...)", "https://moto.autodoc.de/motorradteile/bmw-motorcycles/k?car_id=104173")
                .checkCountOfAddedAutoInGarageIcon()
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}