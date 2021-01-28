package ATD.Catalog_Brands.QC_33_BrandPageAndBlocks;

import ATD.Supplier_brand_line_page_Logic;
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

public class QC_3273 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "supplier3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to brand line route ")
    public void testChecksTransitionToBrandLineRoute(String route) {
        openPage(route);
        new Supplier_brand_line_page_Logic().presenceAnotherBrandsBlock().transitionToAllAnotherBrands("VALEO");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
