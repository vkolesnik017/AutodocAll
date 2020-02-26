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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_126_FiltersSorting_TestTwoFilterAttributesInBlock {
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

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks two filter attributes in block on route search with generic")
    public void testTwoFilterAttributesInBlockRouteWithGeneric() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search5"));
        listingPage.langeFilterCheckbox2().click();
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
    @Description(value = "Test checks two filter attributes in block on LKW model")
    public void testTwoFilterAttributesLKWmodel() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter2().shouldBe(visible);
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
    public void testTwoFilterAttributesLKWcar() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
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
        listingPage.langeFilterCheckbox2().click();
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
