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

public class QC_138_FiltersSorting_TestDurchmesserAndBySideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Durchmesser and by side filters interaction")
    public void testDurchmesserAndSideFilterInteraction(String route) {
        openPage(route);
        listingPage.clickFilterBySideBack()
                    .waitUntilPreloaderDisappear();
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButton());
        listingPage.hoverOnSideFilterAndClick(listingPage.durchmesserSideFilterButton())
                    .waitUntilPreloaderDisappear()
                    .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                    .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Durchmesser and by side filters interaction LKW route")
    public void testDurchmesserAndSideFilterInteractionLKW() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list9"));
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButtonSecondValue());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButtonSecondValue())
                .waitUntilPreloaderDisappear()
                .clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Durchmesser and by side filters interaction LKW route")
    public void testDurchmesserAndSideFilterInteractionLKW2() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list2"));
        String durchmesserValue = listingPage.getTextFromElement(listingPage.durchmesserSideFilterButton());
        listingPage.clickFilterButton(listingPage.durchmesserSideFilterButton())
                .waitUntilPreloaderDisappear()
                .clickFilterBySideFront()
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Vorderachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());


    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
