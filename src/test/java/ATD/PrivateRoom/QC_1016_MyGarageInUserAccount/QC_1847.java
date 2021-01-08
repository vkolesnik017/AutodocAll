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

public class QC_1847 {

    String email = "qc_1847_SortingVehiclesByDate@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting vehicles by date of addition")
    public void testChecksSortingVehiclesByDate(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .checkPresenceOfVehiclesInMyGarageBlockAndHeader()
                .transitionToMainPage()
                .chooseBrandModelTypeInSelector("AUDI", "433", "1040")
                .goToCatalog()
                .checkListOfAddedVehicleInHeaderAfterSelectedNewAuto()
                .selectTruckBlock()
                .selectTruckInVerticalSelector("24", "714", "1004434")
                .checkListOfAddedVehicleInHeaderAfterSelectedNewAuto()
                .selectMotoBlock()
                .selectMotoInHorizontalMotoSelector("4068", "12676", "103462")
                .checkListOfAddedVehicleInHeaderAfterSelectedNewAuto();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
