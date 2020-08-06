package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import ATD.DataBase;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class QC_407_FiltersSorting_TestPriceFilterOnLKWlisting {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter on LKW listing")
    public void checkPriceFilterLkwListing() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
