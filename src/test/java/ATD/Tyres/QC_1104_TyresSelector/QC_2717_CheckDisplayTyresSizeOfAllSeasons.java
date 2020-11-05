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

public class QC_2717_CheckDisplayTyresSizeOfAllSeasons {

    private Tyres_page_Logic tyresPage = new Tyres_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check display tyres size of all seasons, regardless of the selected season in the motorcycle tire selector")
    public void testCheckDisplayTyresSizeOfAllSeasons(String route) {
        openPage(route);
        List<String> all = tyresPage.checkVisibleTopTyres().getAllValuesFromSelector();
        List<String> allSeasons = tyresPage.selectSeasonInSelector("allwetter").checkVisibleTopTyres().getAllValuesFromSelector();
        tyresPage.comparisonOfAllSeasonsValues(allSeasons, all);
        List<String> winterSeason = tyresPage.selectSeasonInSelector("winter").checkVisibleTopTyres().getAllValuesFromSelector();
        tyresPage.comparisonOfAllSeasonsValues(winterSeason, all);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
