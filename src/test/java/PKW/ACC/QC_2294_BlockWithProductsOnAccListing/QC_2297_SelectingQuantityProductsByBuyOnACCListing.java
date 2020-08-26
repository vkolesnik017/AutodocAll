package PKW.ACC.QC_2294_BlockWithProductsOnAccListing;

import PKW.Listing_accessories_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2297_SelectingQuantityProductsByBuyOnACCListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks work quantity counter in card product on listing.")
    public void testCheckingWorkQuantityCounterInCardProduct(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checkingWorkQuantityCounterOnDecreaseAndIncrease();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
