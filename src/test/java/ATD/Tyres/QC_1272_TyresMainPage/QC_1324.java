package ATD.Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_feature_page_Logic;
import Common.SetUp;
import ATD.Tyre_item_page_Logic;
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
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1324 {

    private Tyres_feature_page_Logic tyres_feature_page_logic = new Tyres_feature_page_Logic();

    private String tyreId;

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
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker,tyres_feature");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Product Page From Tyres Top Block")
    public void tesGoToProductPageFromTyresTopBlock(String route) {
        openPage(route);
        tyreId = new Tyres_page_Logic().clickTyreInTopBlockAndGetTyreId();
        new Tyre_item_page_Logic().checkTyreIdOnProductPage(tyreId);
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Transition To Product Page From Tyres Top Block from Tyres Feature and maker page")
    public void tesGoToProductPageFromTyresTopBlockFromTyresFeatureAndMakerPage(String route) {
        openPage(route);
        tyres_feature_page_logic.clickTyreInTopBlocAndCheckRedirect(tyres_feature_page_logic.allSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInAllSeason());
        back();
        tyres_feature_page_logic.clickTyreInTopBlocAndCheckRedirect(tyres_feature_page_logic.sunSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInSunSeason());
        back();
        tyres_feature_page_logic.clickTyreInTopBlocAndCheckRedirect(tyres_feature_page_logic.winterSeasonTabInTopBlock(), tyres_feature_page_logic.topItemInWinterSeason());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
