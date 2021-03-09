package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.Listing_page_Logic;
import Common.DataBase;
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
import static com.codeborne.selenide.Selenide.open;

public class QC_251 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks addToBasket buttons sorting with filter by brand")
    public void testSortingWithFilterByBrand() throws SQLException {
        open("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_car_list5"));
        listingPage.clickFilterButton(listingPage.firstBrandButtonOemListing())
                .waitUntilPreloaderDisappear()
                .checkAddToBasketButtonsSorting();
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model8,moto_category_car_list16");

    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks addToBasket buttons sorting with filter by brand")
    public void testSortingWithFilterByBrandMoto(String routes) {
        openPage(routes);
        listingPage.setBrandFilterById("2")
                .waitUntilPreloaderDisappear()
                .checkAddToBasketButtonsSorting();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
