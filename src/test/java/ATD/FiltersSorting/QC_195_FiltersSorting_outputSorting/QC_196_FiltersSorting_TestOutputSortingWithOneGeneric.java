package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.Listing_page;
import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_196_FiltersSorting_TestOutputSortingWithOneGeneric {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price and addToBasket buttons sorting with one generic")
    public void testSortingOneGeneric(String route) {
        openPage(route);
        do {
            listingPage.checkPriceSortingInIncreasingOrderRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkPriceSortingInIncreasingOrderNotRidex(listingPage.priceOfAllProductsOnPageInList());
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
        close();
    }
}
