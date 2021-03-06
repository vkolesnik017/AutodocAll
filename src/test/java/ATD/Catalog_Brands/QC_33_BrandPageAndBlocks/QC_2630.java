package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import Common.SetUp;
import ATD.Supplier_page_Logic;
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

public class QC_2630 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks matching all headlines and images")
    public void testChecksMatchAllHeadlinesAndImages(String route) {
        openPage(route);

        new Supplier_page_Logic().checkAllHeadlinesAndImages();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
