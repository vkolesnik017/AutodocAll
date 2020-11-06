package PKW.ACC.QC_2288_ListingAccessories;

import Common.SetUp;
import PKW.Listing_accessories_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2289_AvailabilityAndWorkBreadCrumbsOnAccessoriesListing {

    private Listing_accessories_page_Logic listingAccessoriesPageLogic = new Listing_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking presence bread crumbs.")
    public void testCheckingPresenceBreadCrumbs(String route) {
        openPage(route);
        listingAccessoriesPageLogic.checkingPresenceBreadCrumbs();
        listingAccessoriesPageLogic.clickFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        listingAccessoriesPageLogic.clickSecondBreadCrumb();
        checkingContainsUrl("autozubehoer");
        back();
        listingAccessoriesPageLogic.clickThirdBreadCrumb();
        checkingContainsUrl("autoelektronik-pr");
        back();
        listingAccessoriesPageLogic.checkingNotClickableFourthBreadCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
