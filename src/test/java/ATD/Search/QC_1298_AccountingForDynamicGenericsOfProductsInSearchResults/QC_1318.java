package ATD.Search.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;


import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
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

public class QC_1318 {

    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list7");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Product Absence In Search By Main Generic If Dynamic Generic Fits Car")
    public void testProductAbsenceInSearchByMainGenericIfDynamicGenericFitsCar(String route) {
        openPage(route);
        new Main_page_Logic().useSearch("Ölfilter");
        listingPageLogic.checkListingWithSelectingFilterByBrand("665", "UFI")
                        .checkProductTitleOnListing("25.406.01", false, listingPageLogic.productArticlesCollection());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
