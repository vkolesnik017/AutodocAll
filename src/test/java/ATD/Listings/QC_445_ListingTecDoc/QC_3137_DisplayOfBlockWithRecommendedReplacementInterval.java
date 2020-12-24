package ATD.Listings.QC_445_ListingTecDoc;

import ATD.Category_car_list_page_Logic;
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

public class QC_3137_DisplayOfBlockWithRecommendedReplacementInterval {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list56");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck Display Of block with recommended replacement interval")
    public void testCheckDisplayOfBlockWithRecommendedReplacementInterval(String route) {
        openPage(route);
        new Category_car_list_page_Logic().displayRecommendedReplacementIntervalInfoText("Empfohlenes Teileaustauschintervall für Ihr Auto für die Teilekategorie Motoröl alle 15.000 km / alle 12 Monate");
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list57");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сheck Display Of block with recommended replacement interval")
    public void testCheckDisplayOfBlockWithRecommendedReplacementIntervalCarList(String route) {
        openPage(route);
        new Category_car_list_page_Logic().displayRecommendedReplacementIntervalInfoText("Empfohlenes Teileaustauschintervall für Ihr Auto für die Teilekategorie Bremsscheiben alle 50.000 km");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
