package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.DataBase;
import ATD.Listing_page_Logic;
import ATD.Main_page;
import ATD.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_55_FiltersSorting_TestBrandFilterWithPaginationLKW {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination (LKW listing)")
    public void checkBrandFilterPaginationLKW() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list5"));
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        String brandName1 = listingPage.firstBrandNameInFiler().attr("alt").split(" ")[0];
        String brandName2 = listingPage.secondBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName3 = listingPage.thirdBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName4 = listingPage.fourthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName5 = listingPage.fifthBrandNameInFilter().attr("alt").split(" ")[0];
        String brandName6 = listingPage.sixthBrandNameInFilter().attr("alt").split(" ")[0];
        listingPage.secondListingPage().click();
        listingPage.checkProductTitleOnListingWithSixExpectedTexts(brandName1, brandName2, brandName3, brandName4, brandName5, brandName6, true, listingPage.productTitleInListMode());
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
