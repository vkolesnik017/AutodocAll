package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import AWS.CustomerCatalog_aws;
import AWS.DisabledDangerousProducts_aws;
import AWS.ProductCard_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3202_RedirectsFromDisabledDangerousProduct {
    DisabledDangerousProducts_aws dangerousPage = new DisabledDangerousProducts_aws();
    CustomerCatalog_aws customerCatalogPage = new CustomerCatalog_aws();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking redirects from disabled dangerous product")
    public void testCheckRedirectsFromDisabledDangerousProduct(String route) throws SQLException {
        String categoryId = dangerousPage.openDisabledDangerousProductInAwsWithLogin().clickOnProductCard(0).getCategoryId();
        String idOfProduct = new ProductCard_aws().getIdOfProduct();
        customerCatalogPage.openCustomerCatalog().checkRedirectForDangerousProducts(categoryId, idOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
