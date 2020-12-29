package ATD.FiltersSorting.QC_179_FiltersSorting_byGeneric;

import ATD.Category_car_list_page_Logic;
import ATD.Search_page_Logic;
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

public class QC_2764_WorkOfSliderInGenericBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search44");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlock(String route) {
        openPage(route);
        new Search_page_Logic().checkWorkOfSliderInGenericBlock();
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list4,listing_chemicals3");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Work of SliderInGenericBlock ")
    public void testCheckWorkOfSliderInGenericBlockCarList(String route) {
        openPage(route);
        new Category_car_list_page_Logic().checkWorkOfSliderInGenericBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
