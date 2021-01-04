package ATD.ACC.QC_1340_ListingAccessories;


import ATD.Listing_accessories_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1381 {

    private Listing_accessories_page_Logic listing_accessories_page_logic = new Listing_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for four breadcrumbs .")
    public void testCheckTransitionForFourBreadCrumbs(String route) {
        openPage(route);
        listing_accessories_page_logic.checkingPresenceOfTheBreadcrumbs()
                .checkingPresenceAndNotClickableFourthBreadCrumb()
                .clickSecondBreadCrumb();
        checkingContainsUrl("autozubehoer");
        back();
        listing_accessories_page_logic.clickFirstBreadCrumb();
        checkingContainsUrl("autoteile");
        back();
        listing_accessories_page_logic.clickThirdBreadCrumb();
        checkingContainsUrl("autoelektronik-pr");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}