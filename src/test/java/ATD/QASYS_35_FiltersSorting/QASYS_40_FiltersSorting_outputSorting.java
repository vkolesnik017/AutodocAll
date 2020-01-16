package ATD.QASYS_35_FiltersSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_40_FiltersSorting_outputSorting {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,category_oen2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01, TC02 Test checks price and addToBasket buttons sorting with one generic")
    public void testSortingOneGeneric(String route) {
        open(route);
        do {
            listingPage.checkPriceSortingInIncreasingOrderRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkPriceSortingInIncreasingOrderNotRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks price and addToBasket buttons sorting with two generics")
    public void testSortingTwoGeneric() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list7"));
        do {
            listingPage.checkPriceSortingInIncreasingOrderRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkPriceSortingInIncreasingOrderNotRidex2generic(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks addToBasket buttons sorting overcategory route")
    public void testSortingOvercategoryRoute() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list6"));
        do {
            listingPage.grayButton().shouldNot(exist);
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC05 Test checks addToBasket buttons sorting with filter by side")
    public void testSortingWithFilterBySide() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        do {
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC06 Test checks addToBasket buttons sorting with filter by brand")
    public void testSortingWithFilterByBrand() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list5"));
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkAddToBasketButtonsSorting();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks addToBasket buttons sorting with car brand filter")
    public void testSortingWithFilterByCarBrandOem() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen"));
        listingPage.carBrandFilterOem().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        do {
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC08 Test checks addToBasket buttons sorting with brand filter Oem route")
    public void testSortingWithFilterByBrandOem() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkAddToBasketButtonsSorting();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC09 Test checks price and addToBasket buttons sorting with brand filter overcategory route")
    public void testSortingWithFilterByBrandOvercategoryRoute() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkAddToBasketButtonsSorting();
    }
}
