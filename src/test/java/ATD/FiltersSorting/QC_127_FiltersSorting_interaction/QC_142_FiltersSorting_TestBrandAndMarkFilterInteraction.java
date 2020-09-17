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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_142_FiltersSorting_TestBrandAndMarkFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand and mark filters interaction Oem listing")
    public void testBrandAndMarkFilterInteractionOem() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_oen3"));
        String carBrandName = listingPage.getAtributeFromElementAndReturnsFirstWord(listingPage.carBrandFilterOem(), "data-value");
        String brandName = listingPage.getAtributeFromElementAndReturnsFirstWord(listingPage.firstBrandNameOemListing(), "alt");
        listingPage.clickFilterButton(listingPage.carBrandFilterOem())
                .waitUntilPreloaderDisappear()
                .clickFilterButton(listingPage.firstBrandButtonOemListing())
                .waitUntilPreloaderDisappear()
                .getBrandFromTitle(brandName, 4, true, listingPage.productTitleInListMode())
                .checkProductCharacteristicOnListingOem(carBrandName, listingPage.carBrandApplicabilityAttribute());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
