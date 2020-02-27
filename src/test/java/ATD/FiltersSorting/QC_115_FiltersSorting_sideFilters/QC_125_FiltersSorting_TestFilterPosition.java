package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;

public class QC_125_FiltersSorting_TestFilterPosition {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position")
    public void testFilterPosition(String route) {
        openPage(route);
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW model")
    public void testFilterPositionLKWmodel() throws SQLException{
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW car")
    public void testFilterPositionLKWcar() throws SQLException{
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilterLkw().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on LKW search")
    public void testFilterPositionLKWsearch() throws SQLException{
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.durchmesserSideFilterButtonFirstValue().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks filter position on Oem route")
    public void testFilterPositionOem() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen2"));
        String characteristic = listingPage.sideFilterOenAttribute().text();
        listingPage.sideFilterOenCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.sideFilterOenAttribute2().shouldHave(text(characteristic));
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
