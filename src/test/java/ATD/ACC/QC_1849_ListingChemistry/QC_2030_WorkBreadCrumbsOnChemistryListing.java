package ATD.ACC.QC_1849_ListingChemistry;

import ATD.Listing_chemicals_Page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.close;


public class QC_2030_WorkBreadCrumbsOnChemistryListing {

    private Listing_chemicals_Page_Logic listing_chemicals_page_logic = new Listing_chemicals_Page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for three bread crumbs.")
    public void testCheckTransitionForThreeBreadCrumbs(String route) {
        openPage(route);
        listing_chemicals_page_logic.clickOnFirstBreadCrumb();
        checkingContainsUrl("autoteile");
        back();
        listing_chemicals_page_logic.clickOnSecondBreadCrumb();
        checkingContainsUrl("autopflege");
        back();
        listing_chemicals_page_logic.checkingPresenceAndNotClickableThirdBreadCrumb();

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
