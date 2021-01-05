package PKW.OILS.QC_1217_BlockFilterByTolerance;

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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1221 {

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
    @Description(value = "Test checks transition by click on Tolerance filter")
    public void testChecksTransitionByClickOnToleranceFilter(String route) {
        openPage(route);

        new Motoroil_Release_page_Logic()
                .selectFilterByTolerance("ALLISON C4")
                .checkSelectorWithSelectedToleranceFilter("allison-c4")
                .checkListingWithSelectedToleranceFilter("ALLISON C4");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
