package PKW.Listings.QC_3110_ListingSortingAndPrioritization;

import PKW.Oe_number_page_Logic;
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

public class QC_2086 {
    private Oe_number_page_Logic oenListing = new Oe_number_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "oe_number");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chebanenko")
    @Description(value = "Test checks Ridex product prioritization in OEN listing")
    public void testCheckRidexProductPrioritizationInOENListing(String route) {
        openPage(route);
        int activeRidexProducts = oenListing.getSIzeOfActiveProductsWithBrand("RIDEX");
        oenListing.checkListingBrand(activeRidexProducts, "RIDEX");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
