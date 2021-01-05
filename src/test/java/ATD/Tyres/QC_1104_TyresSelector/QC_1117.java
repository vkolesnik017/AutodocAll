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

public class QC_1117 {
    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form2,tyres_season3,tyres_brand2,tyres_size3,tyres_dimension3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search on listing SUV")
    public void testTyresSearchBySelectorOnListingSUV(String route) {
        openPage(route);
        tyresListingPageLogic.checkTyresSelectorBlockVisibilityOnListing()
                .selectSeasonTyreOnListing("Ganzjahresreifen")
                .selectWidthTyreOnListing("215")
                .selectHeightTyreOnListing("65")
                .selectDiameterTyreOnListing("16")
                .clickSubmitTyresSelectorOnListing()
                .checkCharacteristicOnListing("Ganzjahresreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("215", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("65", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("16", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
