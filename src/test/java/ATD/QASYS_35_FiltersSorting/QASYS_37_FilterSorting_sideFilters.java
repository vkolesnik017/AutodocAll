package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static ATD.CommonMethods.closeCookiesFooterMessage;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_37_FilterSorting_sideFilters {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks Produktreihe side filter")
    public void testProduktreiheFilter(String route) {
        open(route);
        String characteristic = listingPage.produktreiheFilterAttribute().text();
        listingPage.produktreiheFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.produktreiheProductAttributeGenericRoute(), listingPage.produktreiheProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks Lange side filter")
    public void testLangeFilter(String route) {
        open(route);
        String characteristic = listingPage.langeFilterAttribute().text();
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks Wischblattausfuhrung side filter")
    public void testWischblattausfuhrungFilter(String route) {
        open(route);
        String characteristic = listingPage.wischblattausfuhrungFilterAttribute().text();
        listingPage.wischblattausfuhrungFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks side filter in tile mode")
    public void testSideFilterInTileMode(String route) {
        open(route);
        String characteristic = listingPage.langeFilterAttribute().text();
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.showListingInTileModeButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingInTileMode(characteristic, listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC05 Test checks side filter with pagination")
    public void testSideFilterWithPagination(String route) {
        open(route);
        closeCookiesFooterMessage();
        String characteristic = listingPage.langeFilterAttribute().text();
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC06 Test checks side filter cancelling")
    public void testSideFilterCancelling(String route) {
        open(route);
        String characteristic = listingPage.activeSideFilter2().text();
        listingPage.activeSideFilter2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.langeProductAttributeGenericRoute().size();
        listingPage.activeSideFilter().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.langeProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC06 Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.hoheProductAttributeTecdocRoute().size();
        listingPage.activeSideFilterLkwHohe().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.hoheProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC06 Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKW2() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.langeProductAttributeTecdocRoute().size();
        listingPage.activeSideFilterLkwCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.langeProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks filter position")
    public void testFilterPosition(String route) {
        open(route);
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks filter position on LKW routes")
    public void testFilterPositionLKWroute1() throws SQLException{
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilter().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks filter position on LKW routes")
    public void testFilterPositionLKWroute2() throws SQLException{
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilterLkw().shouldHave(text(characteristic));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks filter position on Oem route")
    public void testFilterPositionOem() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen2"));
        String characteristic = listingPage.sideFilterOenAttribute().text();
        listingPage.sideFilterOenCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.sideFilterOenAttribute2().shouldHave(text(characteristic));
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC08 Test checks two filter attributes in block")
    public void testTwoFilterAttributesInBlock(String route) {
        open(route);
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
    @Description(value = "TC08 Test checks two filter attributes in block on route search with generic")
    public void testTwoFilterAttributesInBlockRouteWithGeneric() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search5"));
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
    @Description(value = "TC08 Test checks two filter attributes in block on LKW routes")
    public void testTwoFilterAttributesLKW() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
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
    @Description(value = "TC08 Test checks two filter attributes in block on LKW routes")
    public void testTwoFilterAttributesLKW2() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String characteristic = listingPage.activeSideFilterAttributeLkw2().text();
        listingPage.activeSideFilterAttributeLkw2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.activeSideFilterLkw().shouldHave(text(characteristic));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
