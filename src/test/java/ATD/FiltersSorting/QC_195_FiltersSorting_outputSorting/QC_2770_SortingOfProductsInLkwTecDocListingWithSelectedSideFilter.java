package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;

import ATD.LKW_Category_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2770_SortingOfProductsInLkwTecDocListingWithSelectedSideFilter {

    private LKW_Category_car_list_page_Logic carListPage = new LKW_Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list45");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks sorting of products in LKW TecDoc listing with selected side filter")
    public void testCheckSortingOfProductsInLkwTecDocListingWithSelectedSideFilter(String route) {
        openPage(route);
        carListPage.selectInstallationSide(2);
        List<String> generics = carListPage.getGenericsFromSideBar();
        Collections.reverse(generics);
        carListPage.checkSortingOfProductsWithGreyButton(generics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
