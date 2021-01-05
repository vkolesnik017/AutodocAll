package ATD.Tyres.QC_1104_TyresSelector;


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

public class QC_1248 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks validation popup on main tyres page MOTO")
    public void testTyresSearchBySelectorMOTO(String route) {
        openPage(route);
        new Tyres_page_Logic().selectTabMOTO()
                .checkTyresSelectorVisibilityMOTO()
                .selectWidth("140")
                .clickSubmitTyresSelectorToCheckPopup()
                .validationPopupWithClearHeightTypDiameter()
                .closeTyresValidationPopup()
                .selectHeight("70")
                .clickSubmitTyresSelectorToCheckPopup()
                .validationPopupWithClearTypDiameter()
                .closeTyresValidationPopup()
                .selectType("R")
                .clickSubmitTyresSelectorToCheckPopup()
                .validationPopupWithClearDiameter();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
