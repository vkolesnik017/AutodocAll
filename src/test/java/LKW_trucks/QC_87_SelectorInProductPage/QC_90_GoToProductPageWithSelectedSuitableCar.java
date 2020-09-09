package LKW_trucks.QC_87_SelectorInProductPage;

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

public class QC_90_GoToProductPageWithSelectedSuitableCar {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list13");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks switching with selecting suitable truck to Product page")
    public void testChecksSwitchingWithSelectingSuitableTruckToProductPage(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .goToProductPageFromImageWithArticle()
                .visibilityOfMessageAboutCompatibilityTruckAndProduct("Dieses Produkt passt zu Ihrem DAF 65 CF FA 65 CF 180")
                .visibilityOfSuitableCarInCarCompatibilityBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
