package ATD.PrivateProperties.QC_1016_FunctionalityMyGarageInUsersPrivatRoom;

import ATD.Main_page_Logic;
import ATD.Profile_plus_page_Logic;
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

public class QC_2314_AddRemoveVINNumberForVehiclePopupMyGarage {

    private String mail = "QC_2314_autotest@mailinator.com";
    private String vinNum = "1245263214521";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Check add and remove VIN number for vehicle popup my garage")
    public void testCheckAddRemoveVINNumberForVehiclePopupMyGarage(String routes) {
        openPage(routes);
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail);
        new Profile_plus_page_Logic().checkCountOfAddedVehiclesInGarageAtHeaderFromIcon(3)
                .checkVisibleAddedVehicleInPopUpOfGarageHeader(3)
                .checkWorkAddAndRemoveVinNumInPopupOfGarage(vinNum);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
