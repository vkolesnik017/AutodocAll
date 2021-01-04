package PKW.ACC.QC_1793_ChemistryListing;

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


public class QC_1816 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking presence and work buttons in pagination.")
    public void testCheckingWorkBtnInPagination(String route) {
        openPage(route);
        new Listing_chemicals_page_Logic().checkingWorkBtnInPagination()
                .checkingPresencePaginationBlockAndPageSelection();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
