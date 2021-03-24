package ATD.FiltersSorting.QC_179_FiltersSorting_byGeneric;

import ATD.Category_car_list_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2765 {

    private Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list4");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check lack of 5xx after reselecting vehicle with selected filter ")
    public void testCheckLackOf5xxAfterReselectingVehicleWithSelectedFilter(String route) throws IOException {
        openPage(route);
        new Category_car_list_page_Logic().setBrandFilter("194").selectCarInSelector("CHEVROLET", "30675", "112550").checkResponseOfServer(200);
    }

    @DataProvider(name = "routesCarListSearch", parallel = true)
    Object[] dataProviderCarListSearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search45");
    }

    @Test(dataProvider = "routesCarListSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check lack of 5xx after reselecting vehicle with selected filter ")
    public void testCheckLackOf5xxAfterReselectingVehicleWithSelectedFilterSearch(String route) throws IOException {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("194").selectCarInSelector("CHEVROLET", "30675", "112550").checkResponseOfServer(200);
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list43,lkw_search17");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check lack of 5xx after reselecting vehicle with selected filter ")
    public void testCheckLackOf5xxAfterReselectingVehicleWithSelectedFilterLKW(String route) throws IOException {
        openPage(route);
        carListPage.selectBrandInBlock("11091");
        new LKW_Category_car_list_page_Logic().selectTruckInSelector("2242", "8959", "1012748");
        carListPage.checkResponseOfServer(200);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
