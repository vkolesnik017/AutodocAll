package ATD.FiltersSorting.QC_2594_Filters_CriterionCharacteristics;

import ATD.Category_car_list_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import ATD.Moto_Category_car_list_page_Logic;
import ATD.Motoroil_brand_page_Logic;
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

public class QC_3402 {
    private String lampValue = "H11";
    Motoroil_brand_page_Logic motoroilPage = new Motoroil_brand_page_Logic();
    Moto_Category_car_list_page_Logic motoCategoryPage = new Moto_Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list16,moto_category_car_list_model8");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the Plinth filter")
    public void testCheckApplicationPlinthFilter(String route) {
        openPage(route);

        motoCategoryPage
                .setPlinthFilterByTitle(lampValue);
        motoroilPage.appearsOfLoader();
        motoCategoryPage.checkListingWithSelectedLampFilter(lampValue);
    }

    @DataProvider(name = "routeCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list68");
    }

    @Test(dataProvider = "routeCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the Plinth filter")
    public void testCheckApplicationPlinthFilterCarList(String route) {
        openPage(route);

        new Category_car_list_page_Logic()
                .displayLampFilterBLock()
                .setLampFilterByValue(lampValue);
        motoroilPage.appearsOfLoader();
        motoCategoryPage.checkListingWithSelectedLampFilter(lampValue);
    }

    @DataProvider(name = "routeLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list50");
    }

    @Test(dataProvider = "routeLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Application of the Plinth filter")
    public void testCheckApplicationPlinthFilterLKW(String route) {
        openPage(route);

        new Category_car_list_page_Logic()
                .displayLampFilterBLock();
        new LKW_Category_car_list_page_Logic().setLampFilterByValue(lampValue);
        motoroilPage.appearsOfLoader();
        motoCategoryPage.checkListingWithSelectedLampFilter(lampValue);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
