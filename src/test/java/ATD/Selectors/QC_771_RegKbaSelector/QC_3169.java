package ATD.Selectors.QC_771_RegKbaSelector;

import ATD.Category_car_list_page_Logic;
import ATD.Motoroil_page_Logic;
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

public class QC_3169 {

    private String firstValueOfKbaNumber = "0603";
    private String secondValueOfKbaNumber = "bqo";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("ATD").setUpShopWithSubroutes("prod", "DE,DK,FI,FR,IT,NL,NO,PT,SE,CH", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "Test checks transition to listing with correct KBA number")
    public void testChecksTransitionToListingWithCorrectKbaNumber(String route) {
        openPage(route);
        new Motoroil_page_Logic()
                .sendKbaSelectorFormWithValidData(firstValueOfKbaNumber, secondValueOfKbaNumber);
        new Category_car_list_page_Logic().presenceVehicleInSelector("121", "8607", "107860")
                .presenceVehicleInKbaSelector(firstValueOfKbaNumber, secondValueOfKbaNumber)
                .checkingApplicabilityOfProductForSelectedVehicle();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
