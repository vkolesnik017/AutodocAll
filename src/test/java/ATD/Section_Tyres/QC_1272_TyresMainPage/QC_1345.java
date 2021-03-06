package ATD.Section_Tyres.QC_1272_TyresMainPage;


import Common.SetUp;
import ATD.TyresListing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1345 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",
                "tyres_maker,tyres,tyres2,tyres3,tyres4,tyres_feature,tyres_model");
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
    public void close() {
        closeWebDriver();
    }
}
