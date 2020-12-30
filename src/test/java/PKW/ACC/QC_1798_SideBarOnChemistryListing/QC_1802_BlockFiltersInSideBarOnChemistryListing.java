package PKW.ACC.QC_1798_SideBarOnChemistryListing;

import Common.SetUp;
import PKW.Listing_chemicals_page_Logic;
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

public class QC_1802_BlockFiltersInSideBarOnChemistryListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks sorting products by first criteria from consistence criteria block.")
    public void testCheckingSortingProductsByFirstCriteriaConsistence(String route) {
        openPage(route);
        new Listing_chemicals_page_Logic().checkingSortingProductsByFirstCriteriaConsistence();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
