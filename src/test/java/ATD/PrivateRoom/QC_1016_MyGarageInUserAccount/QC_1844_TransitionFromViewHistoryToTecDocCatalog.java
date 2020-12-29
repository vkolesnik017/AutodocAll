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
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1844_TransitionFromViewHistoryToTecDocCatalog {
    private List<String> urlOfAddedVehicle = new ArrayList<>();

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
    @Description(value = "Test checks transition from View history of My garage block to TecDoc catalog")
    public void testChecksTransitionFromViewHistoryToTecDocCatalog(String route) throws SQLException {
        openPage(route);

        new Main_page_Logic()
                .chooseBrandModelTypeInSelector("BMW", "10974", "58964")
                .goToCatalog()
                .presenceOfTecDocCatalog()
                .selectTruckBlock()
                .availabilityOfVerticalTruckSelector()
                .selectTruckInSelector("174", "14250", "1019356")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .selectMotoCategory()
                .selectMotoInHorizontalMotoSelector("5063", "38692", "131227")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle, 3)
                .checkTransitionByClickOnVehicleInMyGaragePopUp();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
