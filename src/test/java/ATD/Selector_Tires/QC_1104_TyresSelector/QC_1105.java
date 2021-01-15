package ATD.Selector_Tires.QC_1104_TyresSelector;


import ATD.Tyres_feature_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1105 {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();
    private Tyres_feature_page_Logic tyres_feature_page_logic = new Tyres_feature_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres_maker");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by selector PKW")
    public void testTyresSearchBySelectorPKW(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSelectorVisibilityPKW()
                            .selectSeasonTyre("Sommerreifen")
                            .selectWidth("215")
                            .selectHeight("55")
                            .selectDiameter("16")
                            .clickSubmitTyresSelector()
                            .checkCharacteristicOnListing("Sommerreifen", tyresListingPageLogic.seasonCharacteristic())
                            .checkCharacteristicOnListing("215", tyresListingPageLogic.widthCharacteristic())
                            .checkCharacteristicOnListing("55", tyresListingPageLogic.heightCharacteristic())
                            .checkCharacteristicOnListing("16", tyresListingPageLogic.radiusCharacteristic());
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks tyres search by selector PKW from Tyres feature page")
    public void testTyresSearchBySelectorPKWFromTyresFeature(String route){
        openPage(route);
        tyres_feature_page_logic.clickExpectedSeasonBtn(tyres_feature_page_logic.summerSeasonRadioBtn())
                .selectWidth("215")
                .selectHeight("55")
                .selectDiameter("16")
                .clickSubmitTyresSelector()
                .checkCharacteristicOnListing("Sommerreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("215", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("55", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("16", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
