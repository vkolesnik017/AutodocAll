package ATD.Tyres.QC_1347_BreadcrumbsOnTyresListing;


import ATD.DataBase;
import ATD.SetUp;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1350_TyresBreadcrumbsTransportTypeAndBrandAndDiameter {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_brand_dimension3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Tyres Breadcrumbs Transport Type And Brand And Diameter")
    public void testTyresBreadcrumbsTransportTypeAndBrandAndDiameter(String route) throws  SQLException {
        openPage(route);
        tyresListingPageLogic.checkBreadcrumbsFirstButtonTransiton();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres_brand_size2"));
        tyresListingPageLogic.checkBreadcrumbsSecondButtonTransiton("llkw")
                            .checkBreadcrumbsThirdButtonTransiton("llkw/goodyear")
                            .checkBreadcrumbsLastButton("17 Zoll");
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}