package ATD.Selector_Tires.QC_1104_TyresSelector;


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

public class QC_1268 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form4,tyres_brand4,tyres_season4,tyres_size5,tyres_dimension4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by selector on listing MOTO")
    public void testTyresSearchBySelectorOnListingMOTO(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkTyresSelectorBlockVisibilityOnListing()
                .selectSeasonTyreOnListing("Ganzjahresreifen")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearHeightWidthTypeDiameterOnListing()
                .closeTyresValidationPopupOnListing()
                .selectWidthTyreOnListing("140")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearHeightTypDiameterOnListing()
                .closeTyresValidationPopupOnListing()
                .selectHeightTyreOnListing("70")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearTypDiameterOnListing()
                .closeTyresValidationPopupOnListing()
                .selectTypeTyreOnListing("R")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearDiameterOnListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
