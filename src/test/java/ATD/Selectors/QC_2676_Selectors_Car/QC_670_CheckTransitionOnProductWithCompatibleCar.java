package ATD.Selectors.QC_2676_Selectors_Car;

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

public class QC_670_CheckTransitionOnProductWithCompatibleCar {

    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product49");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Compatibility list: Checking the transition from a product to a part that is compatible with the selected vehicle")
    public void testCheckTransitionOnProductWithCompatibleCar(String route) throws SQLException {
        openPage(route);
        product_page_logic.selectCarHorizontalSelector("21", "393", "20249")
                .clickSearchBtnInHorizontalSelector()
                .visibilityOfCarMatchBlock("Dieses Produkt passt zu Ihrem CITROЁN AX 1.4 Allure");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "product49"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

