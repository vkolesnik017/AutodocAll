package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_227_FiltersSorting_TestOutputSortingWithTwoGenerics {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price and addToBasket buttons sorting with two generics")
    public void testSortingTwoGeneric() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list7"));
        do {
            listingPage.checkPriceSortingInIncreasingOrderRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkPriceSortingInIncreasingOrderNotRidex2generic(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
