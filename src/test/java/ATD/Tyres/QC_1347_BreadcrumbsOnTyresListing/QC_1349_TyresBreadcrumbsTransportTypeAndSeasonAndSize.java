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

public class QC_1349_TyresBreadcrumbsTransportTypeAndSeasonAndSize {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyres_season_dimension5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Tyres Breadcrumbs Transport Type And Season And Size")
    public void testTyresBreadcrumbsTransportTypeAndSeasonAndSize(String route) throws  SQLException {
        openPage(route);
        tyresListingPageLogic.checkBreadcrumbsFirstButtonTransiton();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres_season_size6"));
        tyresListingPageLogic.checkBreadcrumbsSecondButtonTransiton("motorrad");
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "tyres_season_dimension4"));
        tyresListingPageLogic.checkBreadcrumbsThirdButtonTransiton("llkw/195-60-r16")
                            .checkBreadcrumbsLastButton("Ganzjahresreifen");
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}