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

public class QC_1288_TestTyresSelectorWithSeacrBySpeedIndex {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres");
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
    public void tearDown() {
        close();
    }
}
