package LKW_trucks.QC_10_MainHeadlines;

import ATD.LKW_Error_page_Logic;
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

public class QC_11_AvailabilityOfHeadLineInErrorPage {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "404");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks availability of headline in error page")
    public void testChecksAvailabilityOfHeadLineInErrorPage(String route) {
        openPage(route);
        new LKW_Error_page_Logic().visibilityOfHeadLine();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
