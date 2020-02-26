package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Main_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class QC_414_FiltersSorting_TestPriceFilterOnLkwListingWithPagination {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter on listing LKW with pagination")
    public void checkPriceFilterLkwPagination() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        mainPage.closeCarSelectorTooltipIfVisible();
        executeJavaScript("arguments[0].value='10';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(7.00f, 33.00f, listingPage.priceOfAllProductsOnPageInTile());
        close();
    }
}
