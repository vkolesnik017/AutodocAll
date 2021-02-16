package ATD.Search.QC_548_SearchTooltips;

import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.waitWhileRouteContainsExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_550 {

    private String searchText = "Lenkersatz";

    private Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Go to search listing by click tooltip in search")
    public void testGoToListingFromTooltipInSearch(String route) {
        open(route);
        new Main_page_Logic().inputTextInSearchBar(searchText)
                .clickTooltipInSearchByExactText(searchText)
                .verifyNameRouteEqualsSearch()
                .verifyTextInSearchBar(searchText);
    }

    @DataProvider(name = "routeLKW")
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Go to search listing by click tooltip in search")
    public void testGoToListingFromTooltipInSearchLKW(String route) throws SQLException {
        open(route);
        mainPage.inputTextInSearchBar(searchText)
                .clickTooltipInSearchByExactText(searchText);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_search20"));
        new Search_page_Logic().verifyTextInSearchBar(searchText)
                .verifyTextInUrl(searchText)
                .verifyTextInMainHeadline(searchText);
    }

    @DataProvider(name = "routeMOTO")
    Object[] dataProviderMOTO() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "main");
    }

    @Test(dataProvider = "routeMOTO")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Go to search listing by click tooltip in search")
    public void testGoToListingFromTooltipInSearchMOTO(String route) throws SQLException {
        open(route);
        mainPage.inputTextInSearchBar(searchText)
                .clickTooltipInSearchByExactText(searchText);
        waitWhileRouteContainsExpected("search");
        new Search_page_Logic().verifyTextInSearchBar(searchText)
                .verifyTextInUrl(searchText)
                .verifyTextInMainHeadline(searchText);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
