package ATD.Tyres.QC_1104_TyresSelector;

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

public class QC_2715_CheckingCorrectSizeInTyreSelectorForSelectedSeason {

    private Tyres_page_Logic tyresPage = new Tyres_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3");
    }

    @Test(dataProvider = "routesMoto")
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

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
