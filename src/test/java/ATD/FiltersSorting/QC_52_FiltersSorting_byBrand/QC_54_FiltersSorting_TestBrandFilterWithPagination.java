package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


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

public class QC_54_FiltersSorting_TestBrandFilterWithPagination {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "search2,search19");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination")
    public void checkBrandFilterPagination(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                    .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                    .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination LKW")
    public void checkBrandFilterPaginationLKW(String route) {
        openPage(route);
        listingPageLogic.clickFirstBrandNameInFilter()
                .waitUntilPreloaderDisappear();
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
