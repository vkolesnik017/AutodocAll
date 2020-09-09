package PKW.ACC.QC_2294_BlockWithProductsOnAccListing;

import Common.SetUp;
import PKW.Listing_accessories_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2295_MainProductsOnACCListing {

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
    @Description(value = "Test Checks presence products listing block and number of products in listing.")
    public void testCheckingPresenceProductsListingBlockAndNumberOfProducts(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checkingPresenceProductsListingBlock()
                .checkingNumberOfProductsInListing();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
