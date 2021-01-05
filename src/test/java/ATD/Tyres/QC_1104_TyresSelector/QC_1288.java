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

public class QC_1288 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks tyres search by speed index")
    public void testTyresSearchBySpeedIndex(String route) {
        openPage(route);
        new Tyres_page_Logic().checkTyresSelectorVisibilityPKW()
                .clickSpeedIndexDropdown()
                .clickSpeedIndexH()
                .clickSpeedIndexDropdown()
                .clickSubmitTyresSelector()
                .checkCharacteristicOnListing("H", new TyresListing_page_Logic().speedIndexCharacteristic())
                .checkSpeedIndexInSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
