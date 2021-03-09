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
import static ATD.CommonMethods.pageReload;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3212 {

    Product_page_Logic productPage = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product66");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check presence of a plate about the liter recommended for a complete oil change")
    public void testCheckPresencePlateAboutLiterRecommended(String route) {
        openPage(route);
        productPage.presenceOfGluingBlock().
                chooseBrandModelTypeInHorizontalSelector("AUDI", "6", "17421")
                .clickSearchBtnInHorizontalSelector();
        pageReload();
        productPage.presenceOfRecommendedChangeLiterIcon("Die Motorölmenge für den Ölwechsel in Ihrem Fahrzeug beträgt 3.5 L");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
