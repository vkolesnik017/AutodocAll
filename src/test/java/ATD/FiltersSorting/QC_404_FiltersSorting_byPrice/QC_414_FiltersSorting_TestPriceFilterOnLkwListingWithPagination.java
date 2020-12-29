package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import Common.DataBase;
import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class QC_414_FiltersSorting_TestPriceFilterOnLkwListingWithPagination {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter on listing LKW with pagination")
    public void checkPriceFilterLkwPagination() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        executeJavaScript("arguments[0].value='10';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("10"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(7.00f, 33.00f, listingPage.priceOfAllProductsOnPageInTile());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
