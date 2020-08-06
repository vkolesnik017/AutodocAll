package ATD.ACC.QC_2143_ToolsListing;

import ATD.Listing_instruments_page_Logic;
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

public class QC_2149_BlockPaginationOnToolsListing {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","listing_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence pagination in top and lower part page.")
    public void testCheckingPresencePaginationInTopAndLowerPartPage(String route) {
        openPage(route);
        new Listing_instruments_page_Logic().checkingPresencePaginationInTopAndLowerPartPage();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
