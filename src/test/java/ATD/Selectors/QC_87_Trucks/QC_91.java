package ATD.Selectors.QC_87_Trucks;

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

public class QC_91 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks switching with selecting not suitable truck to Product page")
    public void testChecksSwitchingWithSelectingNotSuitableTruckToProductPage(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().inputArticleOfProductInSearchField().selectProductInSearchDropMenu()
                .visibilityOfTitleAboutNotCompatibilityTruckAndProduct("Tut uns Leid, aber das gewünschte Ersatzteil ist mit Ihrem Fahrzeug nicht kompatibel. Erfahren Sie passende alternative Produkte für Ihr Auto.");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
