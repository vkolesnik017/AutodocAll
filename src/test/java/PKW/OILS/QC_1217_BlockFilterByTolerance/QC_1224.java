package PKW.OILS.QC_1217_BlockFilterByTolerance;

import Common.DataBase;
import PKW.Motoroil_Release_page_Logic;
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
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1224 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "motoroil_release");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to Search page on reset Tolerance filter")
    public void testChecksTransitionToSearchPageOnResetToleranceFilter(String route) throws SQLException {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .clickOnSelectedToleranceFilter("BMW Longlife-01");
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil_search"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
