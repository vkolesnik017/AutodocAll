package ATD.Tyres.QC_2226_CheckingPeriodReturnTiresBlock;

import ATD.SetUp;
import ATD.TyresProduct_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2230_CheckingPeriodReturnTill14DaysInReturnBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "tyre_item");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence text about period product return")
    public void testChecksPresenceTextAboutPeriodProductReturn(String route) {
        open(route);
        new TyresProduct_page_Logic().checkingPresenceTextAboutPeriodProductReturn();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}