package ATD.Tyres.QC_1367_TyresProductPage;


import ATD.SetUp;
import ATD.TyresListing_page_Logic;
import ATD.TyresProduct_page_Logic;
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

public class QC_1370_TestTransitionToListingFromTyresProductPageBySizeSelector {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Listing From Tyres Product Page By Size Selector")
    public void testTransitionToListingFromTyresProductPageBySizeSelector(String route) {
        openPage(route);
        new TyresProduct_page_Logic().selectSummerSeason()
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
    public void tearDown() {
        close();
    }
}