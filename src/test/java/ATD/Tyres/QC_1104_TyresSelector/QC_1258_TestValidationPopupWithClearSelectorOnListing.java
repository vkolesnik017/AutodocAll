package ATD.Tyres.QC_1104_TyresSelector;


import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1258_TestValidationPopupWithClearSelectorOnListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyre_form2,tyre_form3,tyres_brand,tyres_brand2,tyres_brand3," +
                "tyres_season2,tyres_season3,tyres_season5,tyres_size2,tyres_size3,tyres_size4,tyres_dimension2,tyres_dimension3,tyres_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks validation popup on listing tyres")
    public void testTyresSearchBySelectorOnListing(String route) {
        openPage(route);
        new TyresListing_page_Logic().checkTyresSelectorBlockVisibilityOnListing()
                .selectSeasonTyreOnListing("Sommerreifen")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearHeightWidthDiameterOnListing()
                .closeTyresValidationPopupOnListing()
                .selectWidthTyreOnListing("215")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearHeightDiameterOnListing()
                .closeTyresValidationPopupOnListing()
                .selectHeightTyreOnListing("70")
                .clickSubmitTyresSelectorOnListing()
                .validationPopupWithClearDiameterOnListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
