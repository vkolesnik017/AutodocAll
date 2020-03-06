package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Listing_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_141_FiltersSorting_TestBySideAndBrandAndHoheFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list2,lkw_category_car_list9");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side, brand and Hohe filters interaction LKW route search")
    public void testSideAndBrandAndHoheFilterInteractionLKWsearch() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String hoheValue = listingPage.hoheFirstSideFilterButton().text();
        listingPage.hoheFirstSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side, brand and Hohe filters interaction LKW route model")
    public void testSideAndBrandAndHoheFilterInteractionLKWmodel(String route) {
        openPage(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String hoheValue = listingPage.hoheThirdSideFilterButton().text();
        listingPage.hoheThirdSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brandName = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
