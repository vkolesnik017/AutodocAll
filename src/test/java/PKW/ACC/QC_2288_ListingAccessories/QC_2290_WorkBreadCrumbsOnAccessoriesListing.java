package PKW.ACC.QC_2288_ListingAccessories;

import PKW.Listing_accessories_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2290_WorkBreadCrumbsOnAccessoriesListing {

    private Listing_accessories_page_Logic listingAccessoriesPageLogic = new Listing_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checking work bread crumbs.")
    public void testCheckingWorkBreadCrumbs(String route) {
        openPage(route);
        listingAccessoriesPageLogic.clickFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        listingAccessoriesPageLogic.clickSecondBreadCrumb();
        checkingContainsUrl("autozubehoer");
        back();
        listingAccessoriesPageLogic.checkingNotClickableThirdBreadCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
