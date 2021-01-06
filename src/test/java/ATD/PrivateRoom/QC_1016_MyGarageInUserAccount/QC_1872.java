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
import java.util.ArrayList;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1872 {
    private List<String> urlOfAddedVehicle = new ArrayList<>();

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
    @Description(value = "Test checks removing a car from history viewed in pop-up garage")
    public void testChecksRemovingCarFromHistoryViewedInPopUp(String route) {
        openPage(route);

        new Main_page_Logic().chooseBrandModelTypeInSelector("VW", "4644", "14881")
                .goToCatalog()
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .selectCarInSelector("84", "10731", "107074")
                .addedCurrentUrlToList(urlOfAddedVehicle)
                .checkSelectedVehicleInPopUpOfGarageIcon(urlOfAddedVehicle)
                 .clearListOfVehicleInPopUpOfGarageIcon();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
