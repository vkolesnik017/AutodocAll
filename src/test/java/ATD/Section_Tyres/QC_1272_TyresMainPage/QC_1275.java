package ATD.Section_Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_feature_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1275 {

    private Tyres_feature_page_Logic tyres_feature_page_logic = new Tyres_feature_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_model,tyres_feature,tyres_maker");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Tyres Top Block Presence")
    public void testTyresTopBlockPresence(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTopBlock();
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Tyres Top Block Presence from Tyres feature and maker page")
    public void testTyresTopBlockPresenceTyresFeatureAndMakerPage(String route) {
        openPage(route);
        tyres_feature_page_logic.checkTopBlock(tyres_feature_page_logic.allSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInAllSeasonCollection());
        tyres_feature_page_logic.checkTopBlock(tyres_feature_page_logic.sunSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInSunSeasonCollection())
                .checkingSliderInTopBlock(2, tyres_feature_page_logic.slickSlideInTopItemBlockForSunSeason());
        tyres_feature_page_logic.checkTopBlock(tyres_feature_page_logic.winterSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInWinterSeasonCollection())
                .checkingSliderInTopBlock(3, tyres_feature_page_logic.slickSlideInTopItemBlockForWinterSeason());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
