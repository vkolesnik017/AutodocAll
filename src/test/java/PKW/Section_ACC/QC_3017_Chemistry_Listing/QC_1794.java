package PKW.Section_ACC.QC_3017_Chemistry_Listing;

import Common.SetUp;
import PKW.Listing_chemicals_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static Common.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1794 {

    private Listing_chemicals_page_Logic listing_chemicals_page_logic = new Listing_chemicals_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence and work of the breadcrumbs block.")
    public void testCheckingPresenceAndWorkBreadCrumbsBlock(String route) {
        openPage(route);
        new Listing_chemicals_page_Logic().checkingPresenceBreadCrumbsBlock();
        listing_chemicals_page_logic.clickFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        listing_chemicals_page_logic.clickSecondBreadCrumb();
        checkingContainsUrl("chemie-und-pflege");
        back();
        listing_chemicals_page_logic.checkingPresenceAndNotClickableThirdBreadCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
