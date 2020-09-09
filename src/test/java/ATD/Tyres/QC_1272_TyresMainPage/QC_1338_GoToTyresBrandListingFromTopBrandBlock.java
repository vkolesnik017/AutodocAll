package ATD.Tyres.QC_1272_TyresMainPage;


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

public class QC_1338_GoToTyresBrandListingFromTopBrandBlock {

    private String brandName;

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
    @Description(value = "Test Checks Transition Tyres Brand Listing From Top Brand Block")
    public void testGoToTyresBrandListingFromTopBrandBlock(String route) {
        openPage(route);
        brandName = new Tyres_page_Logic().getBrandNameAndClickButtonInTopBlock();
        new TyresListing_page_Logic().checkBrandListingTransition(brandName);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
