package ATD.FiltersSorting.QC_174_FiltersSorting_resetLabel;


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
import static com.codeborne.selenide.Selenide.refresh;

public class QC_175_FiltersSorting_TestResetFiltersByClickResetLabel {

    private Listing_page_Logic listingPage = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,category_oen10,search2,listing_accessories3,listing_instruments3,listing_chemicals");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reset filter label")
    public void testResetFiltersByClickResetLabel(String route) {
        openPage(route);
        listingPage.clickFilterButton(listingPage.firstBrandInFilterButton())
                .checkVisibilityOfElement(listingPage.labelOfActiveFilter())
                .checkVisibilityOfElement(listingPage.resetAllFiltersButton());
        refresh();
        listingPage.checkVisibilityOfElement(listingPage.labelOfActiveFilter())
                .checkVisibilityOfElement(listingPage.resetAllFiltersButton())
                .clickFilterButton(listingPage.resetAllFiltersButton())
                .checkElementIsNotVisible(listingPage.labelOfActiveFilter())
                .checkElementIsNotVisible(listingPage.resetAllFiltersButton())
                .clickFilterButton(listingPage.firstBrandInFilterButton())
                .clickFilterButton(listingPage.closeLabelOfActiveFilter())
                .checkElementIsNotVisible(listingPage.labelOfActiveFilter())
                .checkElementIsNotVisible(listingPage.resetAllFiltersButton());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
