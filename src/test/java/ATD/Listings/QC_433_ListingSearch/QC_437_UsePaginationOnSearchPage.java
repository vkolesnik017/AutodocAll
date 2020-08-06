package ATD.Listings.QC_433_ListingSearch;

import ATD.Search_page_Logic;
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
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_437_UsePaginationOnSearchPage {

    private Search_page_Logic searchPageLogic = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search6");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Use pagination on search page")
    public void testUsePaginationOnSearchPage(String route) {
        openPage(route);
        String url = url();
        searchPageLogic.switchOnSecondPage();
        waitingWhileLinkBecomeExpected(url.concat("&page=2"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
