package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import ATD.DataBase;
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
    public void testSideAndBrandAndHoheFilterInteractionLKWsearch() throws Exception {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        listingPage.clickFilterBySideBack()
                    .waitUntilPreloaderDisappearAndSleep(3000);
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameInFiler(),"alt");
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButton())
                    .waitUntilPreloaderDisappear();
        String hoheValue = listingPage.getTextFromElement(listingPage.hoheFirstSideFilterButton());
        listingPage.clickFilterButton(listingPage.hoheFirstSideFilterButton())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                    .checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute())
                    .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side, brand and Hohe filters interaction LKW route model")
    public void testSideAndBrandAndHoheFilterInteractionLKWmodel(String route) throws Exception{
        openPage(route);
        listingPage.clickFilterBySideBack()
                    .waitUntilPreloaderDisappear();
        String hoheValue = listingPage.getTextFromElement(listingPage.hoheThirdSideFilterButton());
        listingPage.clickFilterButton(listingPage.hoheThirdSideFilterButton())
                    .waitUntilPreloaderDisappearAndSleep(3000);
        String brandName = listingPage.getAtributeFromElement(listingPage.firstBrandNameOemListing(),"alt");
        listingPage.clickFilterButton(listingPage.firstBrandButtonOemListing())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                    .checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute())
                    .checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
