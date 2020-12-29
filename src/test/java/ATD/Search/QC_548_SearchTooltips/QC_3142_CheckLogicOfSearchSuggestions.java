package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_3142_CheckLogicOfSearchSuggestions {
    private List<String> searchText = Arrays.asList("brems", "radla", "zünd");
    Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Checking the logic of search suggestions when searching by synonym or generic")
    public void testCheckInformationPopUpWithSearchTips(String route) {
        open(route);
        mainPage.checkLogicOfSearchSuggestions(searchText, route);
    }
}
