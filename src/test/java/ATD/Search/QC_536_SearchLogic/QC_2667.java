package ATD.Search.QC_536_SearchLogic;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;

public class QC_2667 {

    private String request = "glühlampe, hauptscheinwerfer RIDEX";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check Correctness of search results when searching by generic and brand")
    public void testCheckCorrectnessOfSearchResultsWhenSearchingByGenericAndBrand(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).checkTitlesWithGeneric("RIDEX glühlampe, hauptscheinwerfer");
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list14");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test check Correctness of search results when searching by generic and brand")
    public void testCheckCorrectnessOfSearchResultsWhenSearchingByGenericAndBrandLKW(String route) {
        openPage(route);
        new Main_page_Logic().useSearch(request).checkTitlesWithGeneric("RIDEX glühlampe, hauptscheinwerfer");
    }
}
