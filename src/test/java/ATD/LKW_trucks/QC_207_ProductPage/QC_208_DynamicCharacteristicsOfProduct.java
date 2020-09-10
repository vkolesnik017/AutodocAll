package ATD.LKW_trucks.QC_207_ProductPage;

import ATD.LKW_Product_page_Logic;
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

public class QC_208_DynamicCharacteristicsOfProduct {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Dynamic characteristics Of product")
    public void testChecksDynamicCharacteristicsOfProduct(String route) {
        openPage(route);
        new LKW_Product_page_Logic().selectTruckInHorizontalSelector("74", "9875", "1015568")
                .visibilityOfDynamicCharacteristic();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
