package ATD.FiltersSorting.QC_174_FiltersSorting_resetLabel;


import ATD.Listing_page;
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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.refresh;

public class QC_175_FiltersSorting_TestResetFiltersByClickResetLabel {
    private Listing_page listingPage = new Listing_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,category_oen9,search2,listing_accessories3,listing_instruments3,listing_chemicals");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks reset filter label")
    public void testResetFiltersByClickResetLabel(String route) {
        openPage(route);
        listingPage.firstBrandInFilterButton().click();
        listingPage.labelOfActiveFilter().shouldBe(visible);
        listingPage.resetAllFiltersButton().shouldBe(visible);
        refresh();
        listingPage.labelOfActiveFilter().shouldBe(visible);
        listingPage.resetAllFiltersButton().shouldBe(visible);
        listingPage.resetAllFiltersButton().click();
        listingPage.labelOfActiveFilter().shouldNotBe(visible);
        listingPage.resetAllFiltersButton().shouldNotBe(visible);
        listingPage.firstBrandInFilterButton().click();
        listingPage.closeLabelOfActiveFilter().click();
        listingPage.labelOfActiveFilter().shouldNotBe(visible);
        listingPage.resetAllFiltersButton().shouldNotBe(visible);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
