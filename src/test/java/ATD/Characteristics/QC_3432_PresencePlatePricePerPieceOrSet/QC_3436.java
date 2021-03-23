package ATD.Characteristics.QC_3432_PresencePlatePricePerPieceOrSet;

import ATD.Listing_page_Logic;
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

public class QC_3436 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false); }


    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE","main", "search51");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the display of the item (Price per kit) in the search listing")
    public void testChecksDisplayItemPricePerKitInListing(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceLeastOneElementPricePerSetOrPiece("set")
                .checkingChangeDisplayProductsAsListAndGrid()
                .checkPresenceLeastOneElementPricePerSetOrPiece("set");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
