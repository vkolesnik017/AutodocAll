package ATD.ACC.QC_1340_ListingAccessories;

import ATD.Listing_accessories_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_1382_BlockChangeViewListingAccessories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","listing_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks change display of products on the list view and then grid.")
    public void testCheckingChangeDisplayProductsAsListAndGrid(String route) {
        openPage(route);
        new Listing_accessories_page_Logic().checkingChangeDisplayProductsAsListAndGrid();
    }


    @AfterMethod
    private void tearDown() {
        close();
    }
}
