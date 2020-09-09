package ATD.Tyres.QC_1272_TyresMainPage;


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

public class QC_1330_TestPopularBrandsBlockAndElementsPresence {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Popular Brands Block And Elements Presence")
    public void testPopularBrandsBlockAndElementsPresence(String route) {
        openPage(route);
        new Tyres_page_Logic().checkPopularBrandsBlockVisibility()
                                .checkPopularBrandsSliderFirstPosition()
                                .clickSecondPageInBrandSlider()
                                .checkPopularBrandsSliderSecondPosition();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
