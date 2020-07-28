package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.Main_page_Logic;
import ATD.Profile_garage_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1027_EditingVehicleOnMyGaragePage {

    private String mail = "QC_1027_autotest@mailinator.com";
    private ArrayList<String> infoVehicle, infoVehicleAfterEdit;


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the editing a vehicle on the My garage page in PR")
    public void testEditingVehicleOnMyGaragePage(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .selectVehicleCarInSelector("BMW", "4343", "14801")
                .checkCountOfAddedVehicleInMyGarageBlock(1)
                .openSelectorBlock()
                .clickTrucksTab()
                .selectTruckInSelector("2242", "8959", "1012748")
                .checkCountOfAddedVehicleInMyGarageBlock(2)
                .openSelectorBlock()
                .selectMotoBlockInSelector()
                .selectVehicleInSelector("4081", "12111", "104173")
                .checkCountOfAddedVehicleInMyGarageBlock(3)
                .checkTextWithAddedVehicleWithTextInSelector();
        infoVehicle = new Profile_garage_page_Logic().getTextWithInformationAboutAddedVehicle();
        infoVehicleAfterEdit = new Profile_garage_page_Logic().editAllVehicle()
                .getTextWithInformationAboutAddedVehicle();
        Assert.assertNotEquals(infoVehicle, infoVehicleAfterEdit);
        new Profile_garage_page_Logic().openPopUpMyGarageInHeader()
                .clearMyGarageListInPopUp();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}