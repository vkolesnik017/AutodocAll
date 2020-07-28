package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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

public class QC_129_FiltersSorting_TestOberflacheAndBySideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

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
    @Description(value = "Test checks Oberflache and by side filters interaction")
    public void testOberflacheAndSideFilterInteraction(String route) {
        openPage(route);
        listingPage.hoverOnSideFilterAndClick(listingPage.filterBySideBack())
                .waitUntilPreloaderDisappear();
        String oberflacheValue = listingPage.getTextFromElement(listingPage.oberflacheSideFilterButton());
        listingPage.hoverOnSideFilterAndClick(listingPage.oberflacheSideFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(oberflacheValue, listingPage.oberflacheProductAttributeGenericRoute(), listingPage.oberflacheProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}

