package ATD.Tyres.QC_1354_TyresBrandBlock;


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

public class QC_1365_GoToTyresBrandListingFromAllBrandsCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list_brands,tyres_type_list_brands2,tyres_type_list_brands3," +
                "tyres_type_list_brands4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition Tyres Brand Listing From All Brands Catalog")
    public void testGoToTyresBrandListingFromAllBrandsCatalog(String route) {
        openPage(route);
        new Tyres_page_Logic().clickMichelinLink()
                              .checkBrandListingTransition("Michelin");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
