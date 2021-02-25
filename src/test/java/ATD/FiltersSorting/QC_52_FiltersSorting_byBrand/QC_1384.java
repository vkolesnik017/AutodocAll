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

public class QC_1384 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories,listing_instruments,listing_chemicals,category_car_list,category_oen,search2,category_car_list6,search19,motoroil_viscosity");
    }

    @DataProvider(name = "routeLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list,lkw_category_car_list2,lkw_search,lkw_search6");
    }

    @DataProvider(name = "routeMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_search,moto_category_car_list,moto_category_car_list_model5");
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
    public void testLKW_CheckWorkButtonsPrevAndNextInBlockBrands(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checksWorkButtonsPrevAndNextInBlockBrands();
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks work of the buttons Previous and Next in the brand block")
    public void testMoto_CheckWorkButtonsPrevAndNextInBlockBrands(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checksWorkButtonsPrevAndNextInBlockBrands();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
