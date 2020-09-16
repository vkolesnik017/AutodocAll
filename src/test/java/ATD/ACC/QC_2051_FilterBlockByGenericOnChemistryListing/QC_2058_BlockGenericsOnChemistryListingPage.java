package ATD.ACC.QC_2051_FilterBlockByGenericOnChemistryListing;

import ATD.Listing_chemicals_Page_Logic;
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


public class QC_2058_BlockGenericsOnChemistryListingPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence generic block then work btn previous and next")
    public void testCheckingPresenceGenericsBlockThenWorkBtnPrevAndNext(String route) {
        openPage(route);
        new Listing_chemicals_Page_Logic().checkingPresenceGenericBlock()
                .checkingWorkBtnPrevAndNextInGenericBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
