package ATD.Selectors.QC_87_Trucks;

import ATD.*;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_83 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3,lkw_parent_category,lkw_category2,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_makers,lkw_categories_maker,lkw_main,404");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of truck selector")
    public void testChecksAvailabilityOfTruckSelector(String route) {
        openPage(route);
        new LKW_main_page_Logic().availabilityOfVerticalTruckSelector();
    }


    @DataProvider(name = "LKWCategoryCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10");
    }

    @Test(dataProvider = "LKWCategoryCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of truck selector")
    public void testChecksAvailabilityOfTruckSelectorAtCarList(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic().availabilityOfVerticalTruckSelector();
    }

    @DataProvider(name = "LKWMakerCarList", parallel = true)
    Object[] dataProviderMakerCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2,lkw_categories");
    }

    @Test(dataProvider = "LKWMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of truck selector")
    public void testChecksAvailabilityOfTruckSelectorAtMakerCarList(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().availabilityOfVerticalTruckSelectorСatalogByModel().visibilityOfSelectorInOpenConditionСatalogByModel();
    }


    @DataProvider(name = "LKWMakerCarListSecond", parallel = true)
    Object[] dataProviderMakerCarListSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11");
    }

    @Test(dataProvider = "LKWMakerCarListSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of truck selector")
    public void testChecksAvailabilityOfTruckSelectorAtMakerCarListSecond(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic().availabilityOfVerticalTruckSelectorTecDocCatalog().visibilityOfSelectorInOpenConditionTecDocCatalog();
    }

    @DataProvider(name = "LKWMakerCarListThird", parallel = true)
    Object[] dataProviderMakerCarListThird() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "LKWMakerCarListThird")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of truck selector")
    public void testChecksAvailabilityOfTruckSelectorAtMakerCarListThird(String route) {
        open(route);
        new LKW_maker_car_list_Logic().visibilityOfTooltipInVerticalSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
