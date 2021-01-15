package ATD.Selector_Tires.QC_1104_TyresSelector;


import ATD.Tyre_item_page_Logic;
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

public class QC_1370 {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Listing From Tyres Product Page By Size Selector")
    public void testTransitionToListingFromTyresProductPageBySizeSelector(String route) {
        openPage(route);
        new Tyre_item_page_Logic().selectSummerSeason()
                                        .selectWidthTyreOnListing("205")
                                        .selectHeightTyreOnListing("55")
                                        .selectDiameterTyreOnListing("16")
                                        .clickSubmitTyresSelectorOnListing()
                                        .checkBrandListingTransitionWithNumberOfProducts("Goodride", 5)
                                        .checkCharacteristicOnListing("Sommerreifen", tyresListingPageLogic.seasonCharacteristic())
                                        .checkCharacteristicOnListing("205", tyresListingPageLogic.widthCharacteristic())
                                        .checkCharacteristicOnListing("55", tyresListingPageLogic.heightCharacteristic())
                                        .checkCharacteristicOnListing("16", tyresListingPageLogic.radiusCharacteristic())
                                        .checkBrandIsSelectedInBrandBlock("Goodride")
                                        .checkWidthValueInSelector("205")
                                        .checkHeightValueInSelector("55")
                                        .checkDiameterValueInSelector("16");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
