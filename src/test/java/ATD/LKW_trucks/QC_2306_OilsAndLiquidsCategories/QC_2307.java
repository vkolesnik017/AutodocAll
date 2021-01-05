package ATD.LKW_trucks.QC_2306_OilsAndLiquidsCategories;

import ATD.LKW_main_page_Logic;
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

public class QC_2307 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of Oil and Liquid categories in catalog, child category and transition")
    public void testChecksPresenceOgOilAndLiquidCategories(String route) throws SQLException {
        openPage(route);
        new LKW_main_page_Logic().presenceOfOilCategory()
                .checkTransitionByClickOnOilParentElements();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
