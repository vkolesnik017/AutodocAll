package ATD.LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Category_maker_brand_page_Logic;
import ATD.LKW_maker_car_list_Logic;
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

public class QC_13 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker,lkw_category_maker_brand,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of image truck in headline")
    public void testChecksVisibilityOfImageTruckInHeadLine(String route) {
        openPage(route);
        new LKW_Category_maker_brand_page_Logic().visibilityOfImageBrandInHeadLine();
    }

    @DataProvider(name = "routesMakerCarList", parallel = true)
    Object[] dataProviderForMakerCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesMakerCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of image truck in headline in Maker car list route")
    public void testChecksVisibilityOfImageTruckInHeadLineInMakerCarList(String routesMakerCarList) {
        openPage(routesMakerCarList);
        new LKW_maker_car_list_Logic().visibilityOfImageBrandInHeadLine();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
