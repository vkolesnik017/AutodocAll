package ATD.LKW_trucks.QC_2306_OilsAndLiquidsCategories;

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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2305 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list31,lkw_search10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks TecDoc and search Oil listing")
    public void testChecksTecDocAndSearchOilListing(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic().presenceOfExpectedBrandsInBlock("100570,100290").checkingApplicabilityOfProductForSelectedTruck();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
