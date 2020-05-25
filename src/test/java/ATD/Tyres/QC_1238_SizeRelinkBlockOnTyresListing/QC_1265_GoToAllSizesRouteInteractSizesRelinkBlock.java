package ATD.Tyres.QC_1238_SizeRelinkBlockOnTyresListing;


import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1265_GoToAllSizesRouteInteractSizesRelinkBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_season6,tyres_season7,tyres_dimension2," +
                "tyres_brand5,tyres_season,tyres_season8,tyres_season9,tyres_dimension3,tyres_brand2,tyres_season5,tyres_season10,tyres_season11,tyres_dimension5," +
                "tyres_brand3,tyres_season12,tyres_season4,tyres_dimension4,tyres_brand4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To All Sizes Route Interact All Sizes Relink Block")
    public void testGoToAllSizesRouteInteractSizesRelinkBlock(String route) {
        openPage(route);
        new TyresListing_page_Logic().clickAllSizesButtonAndCheckRedirect();
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
