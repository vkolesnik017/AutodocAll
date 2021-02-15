package ATD.Section_ACC.QC_2659_ToolsListing;

import ATD.Listing_instruments_page_Logic;
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
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2146 {

    private Listing_instruments_page_Logic listingInstrumentsPageLogic = new Listing_instruments_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","listing_instruments,listing_instruments5");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for three bread crumbs.")
    public void testCheckingTransitionForThreeBreadCrumbs(String route) {
        openPage(route);
        listingInstrumentsPageLogic.clickOnFirstBreadCrumb();
        checkingContainsUrl("autoteile");
        back();
        listingInstrumentsPageLogic.clickOnSecondBreadCrumb();
        checkingContainsUrl("werkzeuge");
        back();
        listingInstrumentsPageLogic.checkingPresenceAndNotClickableThirdBreadCrumb();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
