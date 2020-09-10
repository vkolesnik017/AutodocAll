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

public class QC_1973_TransitionToMyGarageFromPopUp {

    String email = "qc_1973TransitionToMyGarage@mailinator.com";

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
    @Description(value = "Test checks transition to my garage from PopUp")
    public void testChecksTransitionToMyGarageFromPopUp(String route) {
        openPage(route);

        new Main_page_Logic()
                .loginAndTransitionToProfilePlusPage(email)
                .checkCountOfAddedVehiclesInGarageAtHeader(5)
                .checkVisibleAddedVehicleInPopUpOfGarageHeader(5)
                .goToGarageBlockThroughPopUpInHeader()
                .checkListOfAddedVehicle(6);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
