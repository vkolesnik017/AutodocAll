package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import ATD.DataBase;
import ATD.Listing_page;
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

public class QC_143_FiltersSorting_TestBrandAndGenericAndPriceFilterInteraction {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand, mark and price filters interaction instruments listing")
    public void testBrandAndMarkAndPriceFilterInteraction() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_instruments"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        String genericName = listingPage.firstGeneric().text();
        listingPage.firstGeneric().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
        close();
    }
}
