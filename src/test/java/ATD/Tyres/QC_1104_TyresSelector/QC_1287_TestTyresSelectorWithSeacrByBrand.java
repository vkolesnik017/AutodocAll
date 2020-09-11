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

public class QC_1287_TestTyresSelectorWithSeacrByBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres selector search by brand")
    public void testTyresSearchByBrand(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSelectorVisibilityPKW()
                .clickBrandDropdown()
                .clickApolloBrand()
                .clickSubmitTyresSelector()
                .checkFirstTyreBrandApollo()
                .checkBrandIsSelectedInBrandBlock("Apollo");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
