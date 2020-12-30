package ATD.Tyres.QC_1475_TopBlockOnTyresListings;


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

public class QC_1500_GoToTyresProductPageFromTopBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyres_brand6,tyres_season2,tyres_season6," +
                "tyres_season7,tyres_dimension6,tyres_size6,tyres_season_dimension2,tyres_season_size2,tyres_brand7,tyres_season,tyres_season8,tyres_season9," +
                "tyres_dimension7,tyres_size7,tyres_season_dimension3,tyres_season_size3,tyres_season5,tyres_season10,tyres_season11,tyres_dimension8,tyres_size8" +
                "tyres_season_dimension4,tyres_season12,tyres_season4,tyres_size5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Go To Tyres Product Page From Top Block")
    public void testGoToTyresProductPageFromTopBlock(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkProductPageOfAllProductsInTopBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
