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

public class QC_3131 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product30");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideo(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("B0CBL_HVJ14");
    }

    @DataProvider(name = "routeSecond")
    Object[] dataProviderSecond() throws SQLException {

        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product15");
    }

    @Test(dataProvider = "routeSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test checking displaying of Ridex video on product page")
    public void testCheckDisplayingOfRidexVideoSecond(String route) {
        openPage(route);
        new Product_page_Logic().presenceVideoBlock()
                .checkIdOfVideoFile("XsOt7En8oYU");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
