package PKW.Listings.QC_429_Listing_OEN;

import PKW.Product_page_Logic;
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


public class QC_431 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "product4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chebanenko")
    @Description(value = "Test checks the redirection from Product page to the OEN listing after clicking the OEN number")
    public void testChecksTheRedirectionFromProductPageToTheOenListing(String route) {
        openPage(route);
        new Product_page_Logic().checkingTheTransitionToTheOENListingPageAfterClickingTheOenTab();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

