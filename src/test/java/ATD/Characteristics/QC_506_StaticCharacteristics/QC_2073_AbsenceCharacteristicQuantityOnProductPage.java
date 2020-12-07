package ATD.Characteristics.QC_506_StaticCharacteristics;


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

public class QC_2073_AbsenceCharacteristicQuantityOnProductPage {

    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "lkw_product5,product33,product34,product35,product36");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Absence Characteristic Quantity 563 on Product Page For FEBI and SWAG")
    public void testAbsenceCharacteristicQuantity563onProductPageForFEBIandSWAG(String route) {
        openPage(route);
        productPageLogic.clickAllCharacteristicsButtonIfPresent()
                        .checkInvisibilityOfCharacteristic(productPageLogic.mengeCharacteristic());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
