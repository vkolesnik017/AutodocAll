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
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class QC_417 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter reset on listing LKW")
    public void checkPriceFilterResetLKW() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        String defaultMinPrice = listingPage.minPriceMapping().getText();
        String defaultMaxPrice = listingPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(17.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.resetPriceFilerButton().click();
        listingPage.minPriceMapping().shouldHave(text(defaultMinPrice));
        listingPage.maxPriceMapping().shouldHave(text(defaultMaxPrice));
        float minFloat = Float.parseFloat(defaultMinPrice);
        float maxFloat = Float.parseFloat(defaultMaxPrice);
        listingPage.checkPricesRange(minFloat, maxFloat, listingPage.priceOfAllProductsOnPageInTile());
        waitingWhileLinkBecomeExpected("https://lkwteile.autodoc.de/ersatzteile/scheibenreinigung-zubehor-200794/mercedes-benz/actros-mp2-mp3?car_id=1007730");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
