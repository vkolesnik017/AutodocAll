package ATD.ProductPage.QC_2741_ProductPage_CarRoute;

import ATD.Product_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3207 {

    Product_page_Logic productPage = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product64");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Check Displacement Selection On Oil Product Page")
    public void testCheckDisplacementSelectionOnOilProductPage(String route) throws SQLException {
        openPage(route);
        String currentVolume = productPage.presenceOfGluingBlock().getCurrentVolume();
        productPage.selectVolume(0);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "product65"));
        productPage.checkAllowableVolume(0, currentVolume);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
