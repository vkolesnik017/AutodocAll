package ATD.Tyres.QC_1317_SizeBlockOnTyresCatalog;


import Common.DataBase;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1320_TransitionToListingRouteInteractDimensionBlockOnCatalogRoute {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_type_list,tyres_type_list2,tyres_type_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Presence Sizes Block On Tyres Catalog Route")
    public void testPresenceSizesBlockOnTyresCatalogRoute(String route) {
        openPage(route);
        new TyresListing_page_Logic().clickDimensionButtonAndCheckRedirect(new TyresListing_page_Logic().dimensionLinkCatalogRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Presence Sizes Block On Tyres Catalog Route Moto")
    public void testPresenceSizesBlockOnTyresCatalogRouteMoto() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres_type_list4"));
        new TyresListing_page_Logic().clickDimensionButtonAndCheckRedirect(new TyresListing_page_Logic().dimensionLinkCatalogRouteMoto());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
