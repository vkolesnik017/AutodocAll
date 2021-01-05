package ATD.Characteristics.QC_506_Characteristics;

import Common.DataBase;
import ATD.Product_page_Logic;
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
import static com.codeborne.selenide.Selenide.open;


public class QC_496_DynamicCharacteristicNotDisplayedWithCarBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks absence of dynamic characteristic with choosen car brand")
    public void testDynamicCharacteristicNotDisplayedWithCarBrand(String route) throws Exception {
        openPage(route);
        open(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product25"));
        new Product_page_Logic().checkDynamicCharacteristic();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
