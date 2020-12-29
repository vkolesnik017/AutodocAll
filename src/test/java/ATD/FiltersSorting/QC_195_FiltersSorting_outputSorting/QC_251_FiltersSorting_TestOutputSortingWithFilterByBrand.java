package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import Common.DataBase;
import ATD.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_251_FiltersSorting_TestOutputSortingWithFilterByBrand {
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

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
