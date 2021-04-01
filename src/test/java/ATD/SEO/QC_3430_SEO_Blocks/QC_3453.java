package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3453 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("DOX-0120", "DOX-0123", "DOX-0201", "DOX-0117", "DOX-0115");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product104");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category model brand  route")
    public void testChecksSeoBlockOnCategoryModelBrandRoute(String route) throws IOException {
        openPage(route);
        new Product_page_Logic().checkTitlesOfInterestArticleLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
