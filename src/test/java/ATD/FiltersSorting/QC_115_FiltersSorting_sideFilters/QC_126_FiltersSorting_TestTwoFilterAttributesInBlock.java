package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_126_FiltersSorting_TestTwoFilterAttributesInBlock {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search17,search18");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block")
    public void testTwoFilterAttributesInBlock(String route) {
        openPage(route);
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String characteristic = listingPage.activeSideFilter2().text();
        listingPage.activeSideFilter2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block LKW")
    public void testTwoFilterAttributesInBlockLKW(String route) {
        openPage(route);
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String characteristic = listingPage.activeSideFilter2().text();
        listingPage.activeSideFilter2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on route search with generic")
    public void testTwoFilterAttributesInBlockRouteWithGeneric() throws Exception {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search5"));
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        Thread.sleep(5000);
        String characteristic = listingPage.activeSideFilter4().text();
        listingPage.activeSideFilter4().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter4FirstPosition().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW model")
    public void testTwoFilterAttributesLKWmodel() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        listingPage.langeFilterCheckbox3().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldBe(visible);
        String characteristic = listingPage.activeSideFilter2().text();
        listingPage.activeSideFilter2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW car")
    public void testTwoFilterAttributesLKWcar() throws Exception {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        Thread.sleep(2000);
        String characteristic = listingPage.activeSideFilterAttributeLkw2().text();
        listingPage.activeSideFilterAttributeLkw2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilterLkw().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRouteLKW(), listingPage.hoheProductAttributeTecdocRouteLKW());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on LKW search")
    public void testTwoFilterAttributesLKWsearch() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        listingPage.langeFilterCheckbox3().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String characteristic = listingPage.durchmesserSideFilterButtonSecondValue().text();
        listingPage.durchmesserSideFilterButtonSecondValue().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.durchmesserSideFilterButtonFirstValue().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
