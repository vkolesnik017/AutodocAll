package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

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

public class QC_3286 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_line3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check transition from TOP product to product page")
    public void testChecksTransitionToProductPage(String route) {
        openPage(route);
        new Supplier_brand_line_page_Logic()
                .presenceTopProductsBlock()
                .checkTransitionToProductPage(0);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
