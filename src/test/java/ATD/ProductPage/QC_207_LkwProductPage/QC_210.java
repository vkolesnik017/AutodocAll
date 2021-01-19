package ATD.ProductPage.QC_207_LkwProductPage;

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

public class QC_210 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks block of compatible truck")
    public void testChecksBlockOfCompatibleTruck(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .visibilityOfCompatibleTruckBLock()
                .checkOfSortingCompatibleTruckList()
                .visibilityOfModelsListBlock("DAF")
                .visibilityOfApplicationBlockSpecification("DAF 65 CF")
                .availabilityOfCompatibleTruckInSelector();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
