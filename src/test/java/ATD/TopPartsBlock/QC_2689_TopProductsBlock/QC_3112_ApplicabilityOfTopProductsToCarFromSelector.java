package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Maker_car_list_page_Logic;
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

public class QC_3112_ApplicabilityOfTopProductsToCarFromSelector {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list19");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check applicability of TOP products to car from selector")
    public void testCheckApplicabilityOfTopProductsToCarFromSelector(String route) {
        openPage(route);
        new Maker_car_list_page_Logic().checkApplicabilityTopProductsAndSelectedCar();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
