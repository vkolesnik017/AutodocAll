package ATD.LKW_trucks.QC_82_TruckSelectorBlock;

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

public class QC_93_VisibilityOfCarInInputFieldOfSelectorFromUrl {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker_brand,lkw_categories_maker2,lkw_category_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of car in selector from url")
    public void testChecksVisibilityOfCarInSelectorFromUrl(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic()
                .availabilityOfCarBrandInSelectorFromUrl();
    }

    @DataProvider(name = "routesCategoryCarList", parallel = true)
    Object[] dataProviderCategoryCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list10,lkw_category_car_list11");
    }

    @Test(dataProvider = "routesCategoryCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of car in selector from url")
    public void testChecksVisibilityOfCarInSelectorFromUrlCategoryCarList(String routeCategoryCarList) {
        openPage(routeCategoryCarList);
        new LKW_Category_car_list_page_Logic()
        .availabilityOfTruckInSelectorFromUrl();

    }


    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderMakerCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of car in selector from url")
    public void testChecksVisibilityOfCarInSelectorFromUrlMakerCarList(String routesMakerCarList) {
        open(routesMakerCarList);
        new LKW_maker_car_list_Logic()
                .availabilityOfTruckInSelectorFromUrl();

    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
