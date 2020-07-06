package MOTO.QC_730_ProductPage;

import ATD.Moto_Product_page_Logic;
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

public class QC_731_DynamicCharacteristic {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product3");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks dynamic characteristic")
    public void testChecksDynamicCharacteristic(String route) {
        openPage(route);

        new Moto_Product_page_Logic()
                .selectMotoInHorizontalSelector("4081", "14014", "113799")
                .presenceOfMotoBrandAtInfoMessage("BMW MOTORCYCLES R")
                .visibilityOfDynamicCharacteristicBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
