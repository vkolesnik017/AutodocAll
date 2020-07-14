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

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1017_ElementsOfGaragePageInPR {

    private String mail = "QC_1017_autotestATD@mailinator.com";

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
    @Description(value = "Test checks the elements of the Garage page in PR")
    public void testElementsOfGaragePageInPR(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToMyVehiclesBlock()
                .checkForTextInBlockTopTitle("Mein AUTODOC")
                .checkPresenceClientID()
                .checkPresenceHeaderBlockAndElementInside()
                .checkNamePageAndPresenceIcon("Meine Fahrzeuge")
                .checkPresenceSelectorAddCar()
                .checkTextOfEmptyVehicleList("Es gibt kein Fahrzeug in Ihrer Garage.");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}