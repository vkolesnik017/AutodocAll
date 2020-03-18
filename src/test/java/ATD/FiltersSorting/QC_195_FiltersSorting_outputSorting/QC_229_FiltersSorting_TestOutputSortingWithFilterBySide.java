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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;

public class QC_229_FiltersSorting_TestOutputSortingWithFilterBySide {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks addToBasket buttons sorting with filter by side")
    public void testSortingWithFilterBySide() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        do {
            listingPage.checkAddToBasketButtonsSorting();
            listingPage.nextPageButton().click();
        } while (listingPage.nextPageButton().is(visible));
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
