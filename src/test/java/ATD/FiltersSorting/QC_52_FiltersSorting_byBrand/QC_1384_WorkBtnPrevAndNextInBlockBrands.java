package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;

import ATD.Listing_accessories_page_Logic;
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

public class QC_1384_WorkBtnPrevAndNextInBlockBrands {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories,category_car_list,category_oen,search2,category_car_list6,search19");
    }

    @DataProvider(name = "routeLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list,lkw_category_car_list2,lkw_search,lkw_search6");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks work of the buttons Previous and Next in the brand block")
    public void testCheckWorkButtonsPrevAndNextInBlockBrands(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checksWorkButtonsPrevAndNextInBlockBrands();
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks work of the buttons Previous and Next in the brand block")
    public void testCheckWorkButtonsPrevAndNextInBlockBrandsForLKW(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checksWorkButtonsPrevAndNextInBlockBrands();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
