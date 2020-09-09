package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import Common.DataBase;
import ATD.Listing_page_Logic;
import Common.SetUp;
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

public class QC_125_FiltersSorting_TestFilterPosition {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position")
    public void testFilterPosition(String route) {
        openPage(route);
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.langeFilterAttribute3());
        listingPageLogic.scrollToElement(listingPageLogic.langeFilterAttribute3())
                        .clickFilterButton(listingPageLogic.langeFilterCheckbox3())
                        .waitUntilPreloaderDisappear()
                        .checkTextInElement(listingPageLogic.activeSideFilter3(), characteristic);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW model")
    public void testFilterPositionLKWmodel() throws SQLException{
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list7"));
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.verschleisswarnkontaktFirstButtonInSidebar());
        listingPageLogic.clickFilterButton(listingPageLogic.verschleisswarnkontaktFirstButtonInSidebar())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPageLogic.activeSideFilterLkwCheckbox(), characteristic);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW car")
    public void testFilterPositionLKWcar() throws SQLException{
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list6"));
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.verschleisswarnkontaktSecondButtonInSidebar());
        listingPageLogic.clickFilterButton(listingPageLogic. verschleisswarnkontaktSecondButtonInSidebar())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPageLogic.verschleisswarnkontaktFirstButtonInSidebar(), characteristic);
    }

    @Test(enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW search")
    public void testFilterPositionLKWsearch() throws SQLException{
        openPage(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_search"));
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.langeFilterAttribute3());
        listingPageLogic.clickFilterButton(listingPageLogic.langeFilterCheckbox3())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPageLogic.durchmesserSideFilterButtonFirstValue(), characteristic);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on Oem route")
    public void testFilterPositionOem() throws SQLException {
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "category_oen2"));
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.sideFilterOenAttribute());
        listingPageLogic.clickFilterButton(listingPageLogic.sideFilterOenCheckbox())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPageLogic.sideFilterOenAttribute2(), characteristic);
    }

    @Test(dataProvider = "routesLKW", enabled = false)
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position LKW")
    public void testFilterPositionLKW(String route) {
        openPage(route);
        String characteristic = listingPageLogic.getTextFromElement(listingPageLogic.langeFilterAttribute3());
        listingPageLogic.clickFilterButton(listingPageLogic.langeFilterCheckbox3())
                .waitUntilPreloaderDisappear()
                .checkTextInElement(listingPageLogic.activeSideFilter3(), characteristic);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
