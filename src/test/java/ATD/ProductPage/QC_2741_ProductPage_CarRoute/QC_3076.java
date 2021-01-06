package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
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

public class QC_3076 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE","main","product60");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "Going to search page from search bar and check of icon Availability of product on product page")
    public void testCheckIconAvailabilityOfProductOnProductPage(String route) {
        openPage(route);
        new Product_page_Logic().checkQuantityBlockVisibilityOnProductPage();
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}