package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import Common.DataBase;
import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_415 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter on listing ACC with pagination")
    public void checkPriceFilterAccPagination() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        executeJavaScript("arguments[0].value='5';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='14';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.secondListingPage().click();
        listingPage.minPriceMapping().shouldHave(text("5"));
        listingPage.maxPriceMapping().shouldHave(text("14"));
        listingPage.checkPricesRange(2.00f, 17.00f, listingPage.priceOfAllProductsOnPageInList());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
