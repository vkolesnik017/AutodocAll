package ATD.Selector_Tires.QC_1104_TyresSelector;

import ATD.Tyres_maker_page_Logic;
import ATD.Tyres_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2715 {

    private Tyres_page_Logic tyresPage = new Tyres_page_Logic();
    private Tyres_maker_page_Logic makerPage = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres2,tyres,tyres3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the correct size in the tire selector for the selected season")
    public void testCheckingCorrectSizeInTyreSelectorForSelectedSeason(String route) {
        openPage(route);
        List<String> allSeasons = tyresPage.selectSeasonInSelector("allwetter").checkVisibleTopTyres().getAllWidthValuesFromSelector();
        List<String> summerSeason = tyresPage.selectSeasonInSelector("sommer").checkVisibleTopTyres().getAllWidthValuesFromSelector();
        tyresPage.mismatchComparisonOfSeasons(allSeasons, summerSeason);
        List<String> winterSeason = tyresPage.selectSeasonInSelector("winter").checkVisibleTopTyres().getAllWidthValuesFromSelector();
        tyresPage.mismatchComparisonOfSeasons(winterSeason, summerSeason)
                .mismatchComparisonOfSeasons(winterSeason, allSeasons).selectSeasonInSelector("0").checkVisibleTopTyres()
                .checkOfAllWidthValues(allSeasons, summerSeason, winterSeason);
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the correct size in the tire selector for the selected season")
    public void testCheckingCorrectSizeInTyreSelectorForSelectedSeasonMaker(String route) {
        openPage(route);
        tyresPage.selectSeasonInSelector("allwetter");
        makerPage.displayingVehicleLinksInSelector();
        List<String> allSeasons = tyresPage.getAllWidthValuesFromSelector();
        tyresPage.selectSeasonInSelector("sommer");
        makerPage.displayingVehicleLinksInSelector();
        List<String> summerSeason = tyresPage.getAllWidthValuesFromSelector();
        tyresPage.mismatchComparisonOfSeasons(allSeasons, summerSeason);
        tyresPage.selectSeasonInSelector("winter");
        makerPage.displayingVehicleLinksInSelector();
        List<String> winterSeason = tyresPage.getAllWidthValuesFromSelector();
        tyresPage.mismatchComparisonOfSeasons(winterSeason, summerSeason)
                .mismatchComparisonOfSeasons(winterSeason, allSeasons).selectSeasonInSelector("0");
        makerPage.displayingVehicleLinksInSelector();
        tyresPage.checkOfAllWidthValues(allSeasons, summerSeason, winterSeason);
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
