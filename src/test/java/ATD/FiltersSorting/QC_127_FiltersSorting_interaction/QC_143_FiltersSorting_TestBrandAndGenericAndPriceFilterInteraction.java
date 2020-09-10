package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_143_FiltersSorting_TestBrandAndGenericAndPriceFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand, mark and price filters interaction instruments listing")
    public void testBrandAndMarkAndPriceFilterInteraction() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_car_list"));
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "listing_instruments"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        String genericName = listingPage.firstGenericAboveListing().text();
        listingPage.firstGenericAboveListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        close();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
