package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import ATD.Listing_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_132_FiltersSorting_TestFurprnummerAndBySideFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Furprnummer and by side filters interaction")
    public void testFurprnummerAndSideFilterInteraction(String route) {
        openPage(route);
        listingPage.hoverOnSideFilterAndClick(listingPage.filterBySideBack())
                .waitUntilPreloaderDisappear();
        String furprnummerValue = listingPage.getTextFromElement(listingPage.furprnummerSideFilterButton());
        listingPage.hoverOnSideFilterAndClick(listingPage.furprnummerSideFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(furprnummerValue, listingPage.furprnummerProductAttributeGenericRoute(), listingPage.furprnummerProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
