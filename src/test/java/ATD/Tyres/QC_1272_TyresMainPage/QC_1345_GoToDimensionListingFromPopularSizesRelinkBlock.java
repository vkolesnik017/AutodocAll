package ATD.Tyres.QC_1272_TyresMainPage;


import ATD.SetUp;
import ATD.TyresListing_page_Logic;
import ATD.Tyres_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1345_GoToDimensionListingFromPopularSizesRelinkBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Dimension Listing From Popular Sizes Relink Block")
    public void testGoToDimensionListingFromPopularSizesRelinkBlock(String route) {
        openPage(route);
        new TyresListing_page_Logic().clickDimensionButtonAndCheckRedirect(new TyresListing_page_Logic().dimensionLinkListingRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}