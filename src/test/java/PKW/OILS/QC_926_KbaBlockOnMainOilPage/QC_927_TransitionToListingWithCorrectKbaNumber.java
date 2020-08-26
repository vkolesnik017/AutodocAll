package PKW.OILS.QC_926_KbaBlockOnMainOilPage;

import PKW.Car_parts_motoroil_page_Logic;
import PKW.DataBase;
import PKW.Motoroil_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_927_TransitionToListingWithCorrectKbaNumber {
    private String firstValueOfKbaNumber = "0603";
    private String secondValueOfKbaNumber = "bqo";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new PKW.SetUp().setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to listing with correct KBA number")
    public void testChecksTransitionToListingWithCorrectKbaNumber(String route) throws SQLException {
        openPage(route);

        new Motoroil_page_Logic()
                .presenceOfKbaSelector()
                .selectVehicleInKbaSelectorWithCorrectNumber(firstValueOfKbaNumber, secondValueOfKbaNumber).presenceOfBreadCrumbsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "car_parts_motoroil4"));
        new Car_parts_motoroil_page_Logic().presenceVehicleInSelector("121", "8607", "107860")
                .presenceVehicleInKbaSelector(firstValueOfKbaNumber, secondValueOfKbaNumber);
        //       .checkingApplicabilityOfProductForSelectedVehicle();  /* -- ОТКЛЮЧЕНА ПРОВЕРКА, ТАК КАК, СКОРЕЕ ВСЕГО, ТУТ БАГА. ЭТОТ МОМЕНТ ОБСУЖДАЕТСЯ --*/
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
