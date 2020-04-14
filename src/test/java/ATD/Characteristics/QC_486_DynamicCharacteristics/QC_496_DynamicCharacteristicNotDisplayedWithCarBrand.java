package ATD.Characteristics.QC_486_DynamicCharacteristics;

import ATD.DataBase;
import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;


public class QC_496_DynamicCharacteristicNotDisplayedWithCarBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks absence of dynamic characteristic with choosen car brand")
    public void testDynamicCharacteristicNotDisplayedWithCarBrand(String route) throws Exception {
        openPage(route);
        open(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "product25"));
        new Product_page_Logic().checkDynamicCharacteristic();
    }
}
