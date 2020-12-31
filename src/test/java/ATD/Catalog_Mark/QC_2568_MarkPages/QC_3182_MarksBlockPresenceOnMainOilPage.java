package ATD.Catalog_Mark.QC_2568_MarkPages;

import ATD.Motoroil_page_Logic;
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

public class QC_3182_MarksBlockPresenceOnMainOilPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "motoroil");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "OlhaLavrynenko")
    @Description(value = "test check presence of marks block on main oil page")
    public void testCheckPresenceOfMarksBlock(String route) {
        openPage(route);
        new Motoroil_page_Logic().checkPresenceOfMarksBlock();

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}