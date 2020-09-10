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

public class QC_1846_AddingAutoFromHistoryViewedBlockToMyGarage {
    String email = "qc_1846addedautofromhistory@mailinator.com";
    private List<String> urlOfAddedVehicle = new ArrayList<>();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks adding auto from history viewed block to My garage ")
    public void testChecksAddingAutoFromHistoryViewedBlockToMyGarage(String route) {
        openPage(route);

        new Main_page_Logic().chooseBrandModelTypeInSelector("BMW", "10974", "58964")
                .goToCatalog()
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .selectTruckBlock()
                .selectTruckInSelector("174", "14250", "1019356")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .selectMotoCategory()
                .selectMotoInHorizontalMotoSelector("5063", "38692", "131227")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .visiblePopUpOfMyGarageBlock()
                .checkAddedVehicleInMyGaragePopUp(3)
                .openRegistrationFormFromMyGarage(0)
                .loginFromMyGarageAndTransitionToProfilePlusPage(email)
                .visibilityOfUsersName()
                .checkTitleOfAddedVehicle(urlOfAddedVehicle.get(urlOfAddedVehicle.size() - 1))
                .checkPresenceOfVehiclesInMyGarageBlockAndHeader()
                .checkPresenceOfVehiclesInViewedHistoryBlockOfMyGaragePopUp(urlOfAddedVehicle, 2)
                .addedVehicleFromViewedHistoryToMyGaragePopUp()
                .clearMyGarageListInPopUp();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
