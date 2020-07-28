package ATD.PrivateRoom.QC_1016_MyGarageInUserAccount;

import ATD.DataBase;
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

import static ATD.CommonMethods.mailRandomMailinator;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1018_AddPassengerCarToMyGarageBlockInPR {

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
    @Description(value = "Test checks the adding a passenger car to the My garage block in PR")
    public void testAddPassengerCarToMyGarageBlockInPR(String route) throws SQLException {
        String mail = mailRandomMailinator("1018");
        openPage(route);
        new Main_page_Logic().registrationFromLoginButton(mail)
                .goToMyVehiclesBlock()
                .openSelectorBlock()
                .selectVehicleCarInSelector("BMW", "4343", "14801")
                .presenceAddedAuto()
                .checkElementsOfAddedAuto("BMW X5 (E53) (05.2000 - 02.2007)",
                        new DataBase().getFullRouteByRouteAndSubroute("prod","DE", "main", "maker_car_list11"))
                .comparisonOfAddedVehiclesFromMyGarageAndHeader()
                .checkPopUpWithAddedAuto()
                .deleteOfAddedAuto();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}