package ATD.Tyres.QC_1104_TyresSelector;


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

public class QC_1111_TestTyresSearchBySelectorMOTO {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres_type_list_brands,tyres_type_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by selector MOTO")
    public void testTyresSearchBySelectorMOTO(String route) {
        openPage(route);
        new Tyres_page_Logic().selectTabMOTO()
                .checkTyresSelectorVisibilityMOTO()
                .selectWidth("140")
                .selectHeight("70")
                .selectType("R")
                .selectDiameter("17")
                .selectSeasonTyre("Ganzjahresreifen")
                .clickSubmitTyresSelector()
                .checkCharacteristicOnListing("Ganzjahresreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("140", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("70", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("R", tyresListingPageLogic.typeCharacteristic())
                .checkCharacteristicOnListing("17", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
