package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_254_FiltersSorting_TestOutputSortingWithFilterByBrandOvercategoryRoute {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price and addToBasket buttons sorting with brand filter overcategory route")
    public void testSortingWithFilterByBrandOvercategoryRoute() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkAddToBasketButtonsSorting();
        close();
    }
}
