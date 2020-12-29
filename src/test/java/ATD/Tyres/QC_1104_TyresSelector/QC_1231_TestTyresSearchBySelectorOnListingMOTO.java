package ATD.Tyres.QC_1104_TyresSelector;


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

public class QC_1231_TestTyresSearchBySelectorOnListingMOTO {
    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form4,tyres_season4,tyres_brand4,tyres_size5,tyres_dimension4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by selector on listing MOTO")
    public void testTyresSearchBySelectorOnListingMOTO(String route) {
        openPage(route);
        tyresListingPageLogic.checkTyresSelectorBlockVisibilityOnListing()
                .selectWidthTyreOnListing("150")
                .selectHeightTyreOnListing("70")
                .selectTypeTyreOnListing("R")
                .selectDiameterTyreOnListing("18")
                .selectSeasonTyreOnListing("Ganzjahresreifen")
                .clickSubmitTyresSelectorOnListing()
                .checkCharacteristicOnListing("Ganzjahresreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("150", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("70", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("R", tyresListingPageLogic.typeCharacteristic())
                .checkCharacteristicOnListing("18", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
