package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;

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
import static com.codeborne.selenide.Selenide.close;

public class QC_118_FiltersSorting_TestProductreiheFilterApplying {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search17,search18");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list6");
    }

    @DataProvider(name = "routesLKWsearch", parallel = true)
    Object[] dataProviderLKWsearch() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Produktreihe side filter")
    public void testProduktreiheFilter(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.produktreiheFilterAttribute());
        listingPage.clickFilterButton(listingPage.produktreiheFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.produktreiheProductAttributeGenericRoute(), listingPage.produktreiheProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Produktreihe side filter LKW")
    public void testProduktreiheFilterLkw(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.produktreiheFilterCheckboxLKW());
        listingPage.clickFilterButton(listingPage.produktreiheFilterCheckboxLKW())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.produktreiheProductAttributeGenericRouteLKW(), listingPage.produktreiheProductAttributeTecdocRouteLKW());
    }

    @Test(dataProvider = "routesLKWsearch")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Produktreihe side filter")
    public void testProduktreiheFilterLKWsearch(String route) {
        openPage(route);
        String characteristic = listingPage.getTextFromElement(listingPage.produktreiheFilterAttribute());
        listingPage.clickFilterButton(listingPage.produktreiheFilterCheckbox())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.produktreiheProductAttributeGenericRoute(), listingPage.produktreiheProductAttributeTecdocRoute());
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
