package ATD.LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_maker_car_list_Logic;
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

public class QC_17 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks appearance of popup with selected car")
    public void testChecksAppearanceOfPopUpWithSelectedCar(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().visibilityOfInfoLink().appearanceOfInfoPopUpWithSelectedCar();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
