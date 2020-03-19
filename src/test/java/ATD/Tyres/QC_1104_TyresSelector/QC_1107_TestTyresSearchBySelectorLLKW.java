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

public class QC_1107_TestTyresSearchBySelectorLLKW {

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
    @Description(value = "Test checks tyres search by selector LLKW")
    public void testTyresSearchBySelectorLLKW(String route) {
        openPage(route);
        new Tyres_page_Logic().selectTabLLKW()
                .checkTyresSelectorVisibilityLLKW()
                .selectSeasonTyre("Sommerreifen")
                .selectWidth("255")
                .selectHeight("70")
                .selectDiameter("15")
                .clickSubmitTyresSelector()
                .checkCharacteristicOnListing("Sommerreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkCharacteristicOnListing("255", tyresListingPageLogic.widthCharacteristic())
                .checkCharacteristicOnListing("70", tyresListingPageLogic.heightCharacteristic())
                .checkCharacteristicOnListing("15", tyresListingPageLogic.radiusCharacteristic());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
