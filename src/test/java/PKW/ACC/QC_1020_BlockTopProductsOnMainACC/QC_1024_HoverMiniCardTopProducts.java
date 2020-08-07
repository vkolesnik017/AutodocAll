package PKW.ACC.QC_1020_BlockTopProductsOnMainACC;

import PKW.Index_accessories_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1024_HoverMiniCardTopProducts {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks open popup with details after hover on first product in block top products ")
    public void testCheckingPopupWithDetailsInBlockTopProducts(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPopupWithDetailsInBlockTopProducts();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
