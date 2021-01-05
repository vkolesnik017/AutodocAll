package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import Common.DataBase;
import ATD.Main_page_Logic;
import ATD.Profile_garage_page_Logic;
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
import static com.codeborne.selenide.Selenide.refresh;

public class QC_1031 {

    private String mail = "QC_1031_autotestATD@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
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
                        new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "maker_car_list11"))
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
        refresh();
        new Profile_garage_page_Logic().checkAbsenceAddedAuto();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}