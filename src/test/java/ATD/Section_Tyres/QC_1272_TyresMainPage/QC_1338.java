package ATD.Section_Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_feature_page_Logic;
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

public class QC_1338 {

    private String brandName;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesTyresPage", parallel = true)
    Object[] dataProviderTyresPage() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @DataProvider(name = "routesTyresFeature", parallel = true)
    Object[] dataProviderTyresFeature() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_feature");
    }

    @Test(dataProvider = "routesTyresPage")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition Tyres Brand Listing From Top Brand Block from Tyres Page")
    public void testGoToTyresBrandListingFromTopBrandBlockFromTyresPage(String route) {
        openPage(route);
        brandName = new Tyres_page_Logic().getBrandNameAndClickButtonInTopBlock();
        new TyresListing_page_Logic().checkBrandListingTransition(brandName);
    }

    @Test(dataProvider = "routesTyresFeature")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test Checks Transition Tyres Brand Listing From Top Brand Block from Tyres feature Page")
    public void testGoToTyresBrandListingFromTopBrandBlockFromTyresFeaturePage(String route) {
        openPage(route);
        brandName = new Tyres_feature_page_Logic().getBrandNameAndClickButtonInTopBlock();
        new TyresListing_page_Logic().checkBrandListingTransition(brandName);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
