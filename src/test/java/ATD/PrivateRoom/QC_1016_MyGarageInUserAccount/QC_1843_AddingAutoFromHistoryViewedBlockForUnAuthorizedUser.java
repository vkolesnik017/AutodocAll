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
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1843_AddingAutoFromHistoryViewedBlockForUnAuthorizedUser {
    private List<String> urlOfAddedVehicle = new ArrayList<>();

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
    @Description(value = "Test checks adding a car to the “browsing history” block of the “My Garage” pop-up for an unauthorized user ")
    public void testChecksAddingAutoFromHistoryViewedBlockForUnAuthorizedUser(String route) {
        openPage(route);

        new Main_page_Logic()
                .checkAppearanceOfPopUpWhenHoverOverImageOfGarageInHeader()
                .openSelectorFromMyGarageBlock()
                .selectVehicleInSelectorOfMyGarage("121", "4644", "14881")
                .checkVehicleListBeforeWrite(urlOfAddedVehicle)
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle)
                .closePopUpOfMyGarageBlock()
                .selectCarInSelector("84", "10731", "107074")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle)
                .closePopUpOfMyGarageBlock()
                .openSelectorFromMyGarageBlock()
                .selectTruckInSelectorFromMyGarage("143", "5501", "1022695")
                .visibilityOfImageBrandInHeadLine()
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle)
                .closePopUpOfMyGarageBlock()
                .selectTruckInSelector("132", "5549", "1008471")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle)
                .closePopUpOfMyGarageBlock()
                .openSelectorFromMyGarageBlock()
                .selectMotoBlockInSelector()
                .selectMotoInSelectorFromMyGarage("4082", "12019", "104144")
                .visibilityOfTecDocCatalog()
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle, 5)
                .closePopUpOfMyGarageBlock()
                .selectMotoInSelector("5063", "38691", "131225")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle, 5)
                .checkAddedVehicleInMyGaragePopUp(5)
                .checkingAbsenceVehicleInMyGarageBlock(urlOfAddedVehicle.get(0));
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
