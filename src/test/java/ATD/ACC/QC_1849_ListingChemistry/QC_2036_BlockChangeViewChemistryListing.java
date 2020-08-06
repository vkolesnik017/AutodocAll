package ATD.ACC.QC_1849_ListingChemistry;

import ATD.Listing_chemicals_Page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2036_BlockChangeViewChemistryListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks change display of products on the list view and then grid.")
    public void testCheckingChangeDisplayProductsAsListAndGrid(String route) {
        openPage(route);
        new Listing_chemicals_Page_Logic().checkingChangeDisplayProductsAsListAndGrid();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
