package LKW_trucks.QC_87_SelectorInProductPage;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.LKW_maker_car_list_Logic;
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

public class QC_91_GoToProductPageWithSelectedNotSuitableCar {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list3");
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
    private void tearDown() {
        close();
    }
}
