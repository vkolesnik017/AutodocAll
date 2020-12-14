package ATD.Tyres.QC_1104_TyresSelector;

import ATD.Tyres_feature_page_Logic;
import Common.SetUp;
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

public class QC_1240_TestValidationPopupWithClearDiameter {

    private Tyres_feature_page_Logic tyres_feature_page_logic = new Tyres_feature_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks validation popup with clear diameter on main tyres page")
    public void testValidationPopupWithClearDiameter(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSelectorVisibility()
                .selectSeasonTyre("Alle")
                .selectWidth("215")
                .selectHeight("70")
                .clickSubmitTyresSelector();
        new Tyres_page_Logic().validationPopupWithClearDiameter();
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks validation popup with clear diameter on Tyres feature page")
    public void testValidationPopupWithClearDiameterForTyresFeature(String route) {
        openPage(route);
        openPage(route);
        tyres_feature_page_logic.clickExpectedSeasonBtn(tyres_feature_page_logic.summerSeasonRadioBtn())
                .selectWidth("215")
                .selectHeight("55")
                .clickSubmitTyresSelector();
        new Tyres_page_Logic().validationPopupWithClearDiameter();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
