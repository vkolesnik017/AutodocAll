package PKW.ACC.QC_2283_BlockWithProductsOnToolsListing;

import PKW.Listing_instruments_Page_Logic;
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

public class QC_2284_MainProductsOnToolsListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments,listing_instruments2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence products listing block and number of products in listing.")
    public void testCheckingPresenceProductsListingBlockAndNumberOfProducts(String route) {
        openPage(route);
        new Listing_instruments_Page_Logic().checkingPresenceProductsListingBlock()
                .checkingNumberOfProductsInListing();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
