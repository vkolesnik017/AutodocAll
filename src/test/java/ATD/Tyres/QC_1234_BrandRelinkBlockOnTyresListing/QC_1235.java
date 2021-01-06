package ATD.Tyres.QC_1234_BrandRelinkBlockOnTyresListing;


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

public class QC_1235 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_season6,tyres_season7,tyres_brand," +
                "tyres_season,tyres_season8,tyres_season9,tyres_brand7,tyres_season5,tyres_season10,tyres_season11,tyres_brand3,tyres_season12,tyres_season4,tyres_brand4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Presence Brand Relink Block On Tyres Listing")
    public void testPresenceBrandRelinkBlockOnTyresListing(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkBrandRelinkBlockVisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
