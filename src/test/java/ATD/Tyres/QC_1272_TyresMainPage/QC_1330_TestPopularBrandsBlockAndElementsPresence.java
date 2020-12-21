package ATD.Tyres.QC_1272_TyresMainPage;

import ATD.Tyres_feature_page_Logic;
import ATD.Tyres_maker_page_Logic;
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

public class QC_1330_TestPopularBrandsBlockAndElementsPresence {

    private Tyres_page_Logic tyres_page_logic = new Tyres_page_Logic();
    private Tyres_maker_page_Logic tyres_maker_page_logic = new Tyres_maker_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @DataProvider(name = "routesTyresMaker", parallel = true)
    Object[] dataProviderTyresMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Popular Brands Block And Elements Presence")
    public void testPopularBrandsBlockAndElementsPresence(String route) {
        openPage(route);
        new Tyres_page_Logic().checkPopularBrandsBlockVisibility()
                                .checkPopularBrandsSliderFirstPosition(14, 7, tyres_page_logic.brandsInSlider(), "alt")
                                .clickSecondPageInBrandSlider()
                                .checkPopularBrandsSliderSecondPosition(14, 7, tyres_page_logic.brandsInSlider(), "alt");
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Popular Brands Block And Elements Presence from Tyres feature page")
    public void testPopularBrandsBlockAndElementsPresenceFromTyresFeature(String route) {
        openPage(route);
        new Tyres_feature_page_Logic().checkPopularBrandsBlockVisibility()
                .checkPopularBrandsSliderFirstPosition()
                .clickSecondPageInBrandSlider()
                .checkPopularBrandsSliderSecondPosition();
    }

    @Test(dataProvider = "routesTyresMaker")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Popular Brands Block And Elements Presence from Tyres maker page")
    public void testPopularBrandsBlockAndElementsPresenceFromTyresMaker(String route) {
        openPage(route);
        tyres_maker_page_logic.checkPopularBrandsBlockVisibility()
                .checkPopularBrandsSliderFirstPosition(12, 6, tyres_maker_page_logic.brandsInSlider(), "src")
                .clickSecondPageInBrandSlider()
                .checkPopularBrandsSliderSecondPosition(12, 6, tyres_maker_page_logic.brandsInSlider(), "src");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
