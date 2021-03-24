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

public class QC_1023 {
    String email = "qc_1023@mailinator.com";

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
    @Description(value = "Test checks transition to TecDoc catalog from personal client account ")
    public void testCheckTransitionToTecDocFromPersonalClientAccount(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .goToMyVehiclesBlock()
                .openPopUpMyGarageInHeader()
                .clearMyGarageListInPopUp()
                .openSelectorBlock()
                .selectMotoBlockInSelector()
                .selectVehicleInSelector("4081", "12111", "104173")
                .checkCountOfAddedVehicleInMyGarageBlock(1)
                .openSelectorBlock()
                .clickTrucksTab()
                .selectTruckInSelector("2242", "8959", "1012748")
                .checkCountOfAddedVehicleInMyGarageBlock(2)
                .openSelectorBlock()
                .selectVehicleCarInSelector("BMW", "4343", "14801")
                .checkCountOfAddedVehicleInMyGarageBlock(3)
                .openPopUpMyGarageInHeader()
                .clearMyGarageListInPopUp();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
