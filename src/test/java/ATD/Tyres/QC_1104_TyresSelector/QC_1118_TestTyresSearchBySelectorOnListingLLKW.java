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

public class QC_1118_TestTyresSearchBySelectorOnListingLLKW {
    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_form3,tyres_season5,tyres_brand3,tyres_size4,tyres_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by selector on listing LLKW")
    public void testTyresSearchBySelectorOnListingLLKW(String route) {
        openPage(route);
        tyresListingPageLogic.checkTyresSelectorBlockVisibilityOnListing()
                .selectSeasonTyreOnListing("Sommerreifen")
                .selectWidthTyreOnListing("245")
                .selectHeightTyreOnListing("70")
                .selectDiameterTyreOnListing("17")
                .clickSubmitTyresSelectorOnListing()
                .checkCharacteristicOnListing("Sommerreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("245", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("70", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("17", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
