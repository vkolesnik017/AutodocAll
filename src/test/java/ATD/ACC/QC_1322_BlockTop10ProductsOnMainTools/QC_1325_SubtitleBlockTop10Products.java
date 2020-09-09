package ATD.ACC.QC_1322_BlockTop10ProductsOnMainTools;

import ATD.Index_instruments_page_Logic;
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

public class QC_1325_SubtitleBlockTop10Products {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checking presence Subtitle top-10 products block .")
    public void testCheckPresenceSubtitleTop10Block(String route) {
        openPage(route);
        new Index_instruments_page_Logic().checkingPresenceSubtitleTop10Block();
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
