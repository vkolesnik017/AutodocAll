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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_131_FiltersSorting_TestBremsscheibenartAndBySideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2,search16");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list2,lkw_category_car_list9");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Bremsscheibenart and by side filters interaction")
    public void testBremsscheibenartAndSideFilterInteraction(String route) {
        openPage(route);
        listingPage.hoverOnSideFilterAndClick(listingPage.filterBySideBack())
                    .waitUntilPreloaderDisappear();
        String bremsscheibenartValue = listingPage.getTextFromElement(listingPage.bremsscheibenartSideFilterButton());
        listingPage.hoverOnSideFilterAndClick(listingPage.bremsscheibenartSideFilterButton())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                    .checkProductAttributeOnListingWithCarAndFilter(bremsscheibenartValue, listingPage.bremsscheibenartProductAttributeGenericRoute(), listingPage.bremsscheibenartProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Bremsscheibenart and by side filters interaction LKW")
    public void testBremsscheibenartAndSideFilterInteractionLKW(String route) {
        openPage(route);
        listingPage.clickFilterButton(listingPage.filterBySideBack())
                    .waitUntilPreloaderDisappear();
        String bremsscheibenartValue = listingPage.getTextFromElement(listingPage.bremsscheibenartSideFilterButton());
        listingPage.clickFilterButton(listingPage.bremsscheibenartSideFilterButton())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                    .checkProductAttributeOnListingWithCarAndFilter(bremsscheibenartValue, listingPage.bremsscheibenartProductAttributeGenericRoute(), listingPage.bremsscheibenartProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
