package ATD.Tyres.QC_1238_SizeRelinkBlockOnTyresListing;


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

public class QC_1266_GoToAllSizesRouteInteractDiameterRelinkBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_size2,tyres_season_size11,tyres_season_size2,tyres_season_size12," +
                "tyres_size3,tyres_season_size3,tyres_season_size9,tyres_season_size10" +
                "tyres_size4,tyres_season_size7,tyres_season_size8,tyres_season_size4," +
                "tyres_size5,tyres_season_size6,tyres_season_size5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To All Sizes Route Interact Diameter Relink Block")
    public void testGoToAllSizesRouteInteractDiameterRelinkBlock(String route) {
        openPage(route);
        new TyresListing_page_Logic().clickAllSizesButtonAndCheckRedirect();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
