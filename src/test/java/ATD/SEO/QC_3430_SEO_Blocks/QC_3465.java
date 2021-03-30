package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Tyres_season_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3465 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season2,tyres_brand_size4,tyres_brand_dimension10,tyres_season_size14,tyres_season_dimension11,tyres_season_brand_dimension2,tyres_group_season_brand5,tyres_dimension17,tyres_season_dimension11,tyres_season_brand_dimension2,tyres_group_season_brand5,tyres_dimension17");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks of linking block on tyres routes")
    public void testChecksLinkingBlockOnTyresRutes(String route) throws IOException {
        openPage(route);
        new Tyres_season_page_Logic().checkTransitionOfTopTyreSizeLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
