package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.DataBase;
import ATD.Main_page_Logic;
import ATD.Profile_garage_page_Logic;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_1031_RemovingCarFromMyGarageBlock {

    private String mail = "QC_1031_autotestATD@mailinator.com";

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
    @Description(value = "Test checks removal of a car from My garage")
    public void testRemovingCarFromMyGarageBlock(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .selectVehicleCarInSelector("BMW", "4343", "14801")
                .presenceAddedAuto()
                .checkElementsOfAddedAuto("BMW X5 (E53) (05.2000 - 02.2007)",
                        new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "maker_car_list11"))
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
        refresh();
        new Profile_garage_page_Logic().checkAbsenceAddedAuto();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}