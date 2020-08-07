package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_maker_car_list_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1712_ImageOfBrandInHeadLineAtCarList {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_maker_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks image of brand  at main headline")
    public void testChecksImageOfBrandAtMainHeadline(String route) {
        openPage(route);
        new LKW_maker_car_list_Logic()
                .availabilityOfImageOfBrand();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
