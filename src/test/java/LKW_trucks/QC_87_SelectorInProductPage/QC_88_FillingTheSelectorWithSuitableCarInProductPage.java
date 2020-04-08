package LKW_trucks.QC_87_SelectorInProductPage;

import ATD.LKW_Product_page_Logic;
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

public class QC_88_FillingTheSelectorWithSuitableCarInProductPage {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filling the selector with suitable car in Product page")
    public void testChecksFillingSelectorWithSuitableCarInProductPage(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .selectTruckInHorizontalSelector("24", "4177", "1004416")
                .visibilityOfMessageAboutCompatibilityTruckAndProduct("Dieses Produkt passt zu Ihrem DAF 65 CF FA 65 CF 180");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
