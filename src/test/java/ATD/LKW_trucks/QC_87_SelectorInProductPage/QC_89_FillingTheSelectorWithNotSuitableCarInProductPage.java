package ATD.LKW_trucks.QC_87_SelectorInProductPage;

import ATD.LKW_Product_page_Logic;
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

public class QC_89_FillingTheSelectorWithNotSuitableCarInProductPage {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks filling the selector with not suitable car in Product page")
    public void testChecksFillingSelectorWithNotSuitableCarInProductPage(String route) {
        openPage(route);
        new LKW_Product_page_Logic()
                .сomparisonOfSelectedNotSuitableCarAndProduct()
                .checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/askam-fargo-desoto/as-950?car_id=1012748");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
