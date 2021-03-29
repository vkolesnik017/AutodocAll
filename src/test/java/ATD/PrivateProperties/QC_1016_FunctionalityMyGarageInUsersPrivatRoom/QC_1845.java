package ATD.PrivateProperties.QC_1016_FunctionalityMyGarageInUsersPrivatRoom;

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

public class QC_1845 {
    String email = "qc_1845sortingvehicle@mailinator.com";

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
    @Description(value = "Test checks sorting of vehicle by date in My garage block")
    public void testChecksSortingOfVehicleByDateInMyGarageBlock(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .openPopUpMyGarageInHeader()
                .clearMyGarageListInPopUp()
                .openSelectorBlock()
                .selectVehicleCarInSelector("AUDI", "433", "1040")
                .checkCountOfAddedVehicleInMyGarageBlock(1)
                .openSelectorBlock()
                .selectVehicleCarInSelector("FORD", "10257", "118618")
                .checkCountOfAddedVehicleInMyGarageBlock(2)
                .checkTitleInCarInfoBlock("FORD")
                .openSelectorBlock()
                .selectVehicleCarInSelector("MERCEDES-BENZ", "52", "310")
                .checkCountOfAddedVehicleInMyGarageBlock(3)
                .checkTitleInCarInfoBlock("MERCEDES-BENZ")
                .openSelectorBlock()
                .selectVehicleCarInSelector("SKODA", "268", "3022")
                .checkCountOfAddedVehicleInMyGarageBlock(4)
                .checkTitleInCarInfoBlock("SKODA")
                .openPopUpMyGarageInHeader()
                .clearMyGarageListInPopUp();

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
