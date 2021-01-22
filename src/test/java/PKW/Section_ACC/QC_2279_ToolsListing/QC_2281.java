package PKW.Section_ACC.QC_2279_ToolsListing;

import Common.SetUp;
import PKW.Listing_instruments_Page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2281 {

    private Listing_instruments_Page_Logic listingInstrumentsPageLogic = new Listing_instruments_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments,listing_instruments2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test Checking the presence and functionality of the breadcrumbs")
    public void testCheckingBreadcrumbs(String route) {
        openPage(route);
        new Listing_instruments_Page_Logic().checkingPresenceBreadcrumbsBlock();
        listingInstrumentsPageLogic.clickOnFirstBreadcrumb();
        checkingContainsUrl("ersatzteile");
        back();
        listingInstrumentsPageLogic.clickOnSecondBreadcrumb();
        checkingContainsUrl("werkzeuge");
        back();
        listingInstrumentsPageLogic.checkingPresenceAndNotClickableThirdBreadcrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}


