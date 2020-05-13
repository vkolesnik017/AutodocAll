package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1711_ImageOfBrandInMainHeadline {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks image of brand  at main headline")
    public void testChecksImageOfBrandAtMainHeadline(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .availabilityOfImageOfBrand();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
