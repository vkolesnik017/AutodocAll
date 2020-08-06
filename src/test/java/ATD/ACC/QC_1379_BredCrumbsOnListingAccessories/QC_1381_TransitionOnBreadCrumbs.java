package ATD.ACC.QC_1379_BredCrumbsOnListingAccessories;


import ATD.Listing_accessories_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1381_TransitionOnBreadCrumbs {

    private Listing_accessories_page_Logic listing_accessories_page_logic = new Listing_accessories_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks transition for three breadcrumbs .")
    public void testCheckTransitionForThreeBreadCrumbs(String route) {
        openPage(route);
        listing_accessories_page_logic.checkingPresenceAndNotClickableThirdBreadCrumb()
                .clickSecondBreadCrumb();
                checkingContainsUrl("autozubehoer");
                back();
        listing_accessories_page_logic.clickFirstBreadCrumb();
                checkingContainsUrl("autoteile");

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
