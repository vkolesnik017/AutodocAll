package PKW.ACC.QC_1798_SideBarOnChemistryListing;

import PKW.Listing_chemicals_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_1802_BlockFiltersInSideBarOnChemistryListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals2");
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
    private void tearDown() {
        close();
    }
}
