package ATD.Tyres.QC_1104_TyresSelector;


import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1106_TestTyresSearchBySelectorSUV {

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
    @Description(value = "Test checks tyres search by selector SUV")
    public void testTyresSearchBySelectorSUV(String route) {
        openPage(route);
        new Tyres_page_Logic().selectTabSUV()
                .checkTyresSelectorVisibilitySUV()
                .selectSeasonTyre("Ganzjahresreifen")
                .selectWidth("235")
                .selectHeight("50")
                .selectDiameter("18")
                .clickSubmitTyresSelector()
                .checkCharacteristicOnListing("Ganzjahresreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("235", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("50", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("18", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
